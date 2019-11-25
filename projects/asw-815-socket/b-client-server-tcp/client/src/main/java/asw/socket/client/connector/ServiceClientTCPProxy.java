package asw.socket.client.connector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import asw.socket.service.RemoteException;
import asw.socket.service.Service;
import asw.socket.service.ServiceException;

public class ServiceClientTCPProxy implements Service {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	private int port = 6789;
	private InetAddress address;
	
	public ServiceClientTCPProxy(InetAddress address, int port) {
		this.logger.info("[" + this.getClass().getPackageName() +"] Created a new Remote Proxy for Service "  + 
																address.toString() + ":" + port);
		this.address = address;
		this.port = port;
	}
	
	
	@Override
	public String alpha(String arg) throws ServiceException, RemoteException {
		return sendRequestAndGetReply("alpha", arg);
	}

	@Override
	public String beta(String arg) throws ServiceException, RemoteException {
		return sendRequestAndGetReply("beta", arg);
	}

	
	private String sendRequestAndGetReply(String op, String arg) throws ServiceException, RemoteException {
		String result = null;
		Socket socket = null;
		
		try {
			String request = op + '$' + arg;
			
			socket = new Socket(address, port);
			socket.setSoTimeout(1000);
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
	 		logger.info("ServiceClientTCPProxy: calling " + op + "(" + arg + ")");
	 		logger.info("ServiceClientTCPProxy: sending request: " + request);
			out.writeUTF(request); // non-blocking
			
			String reply = in.readUTF();
			logger.info("ServiceClientTCPProxy: received reply: " + reply);
     		logger.info("ServiceClientTCPProxy: " + op + "(" + arg + ") ==> " + reply);
     		
     		if (reply.startsWith("#")) {
        		/* Operation done successfully */
        		result = reply.substring(1);
        	} else if (reply.startsWith("@")) {
        		/* ServiceException */
        		String message = reply.substring(1);
        		throw new ServiceException(message);
        	} else {
        		/* Bad syntax for reply: RemoteException */
        		throw new RemoteException("Malformed reply: " + reply);
        	}
     		
			socket.close();
		} catch (UnknownHostException e) {
			logger.info("Client Proxy: UnknownHostException: " + e.getMessage());
			throw new RemoteException("UnknownHostException: " + e.getMessage());
		} catch (EOFException e) {
			logger.info("Client Proxy: EOFException: " + e.getMessage());
			throw new RemoteException("EOFException: " + e.getMessage());
		} catch (IOException e) {
			logger.info("Client Proxy: IOException: " + e.getMessage());
			throw new RemoteException("IOException: " + e.getMessage());
		} finally {
			if (socket!=null)
				try { socket.close(); }
				catch (IOException e) {
					logger.info("Client Proxy: IOException: " + e.getMessage());
					throw new RemoteException("IOException: " + e.getMessage());
				}
		}
		
		return result;
	}
}
