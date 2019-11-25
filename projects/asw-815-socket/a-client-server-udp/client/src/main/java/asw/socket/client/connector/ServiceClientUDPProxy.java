package asw.socket.client.connector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Logger;

import asw.socket.service.RemoteException;
import asw.socket.service.Service;
import asw.socket.service.ServiceException;

public class ServiceClientUDPProxy implements Service {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	private int port = 6789;
	private InetAddress address;
	
	public ServiceClientUDPProxy(InetAddress address, int port) {
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
		DatagramSocket socket = null;
		
		try {
			String request = op + '$' + arg;
			byte[] requestMessage = request.getBytes();
			DatagramPacket requestPacket = new DatagramPacket(requestMessage, requestMessage.length,
					 this.address, this.port);
			
			socket = new DatagramSocket();
			socket.setSoTimeout(5000);
			
	 		logger.info("ServiceClientUDPProxy: calling " + op + "(" + arg + ")");
	 		logger.info("ServiceClientUDPProxy: sending message: " + request);
			socket.send(requestPacket); // non-blocking
			
			
			byte[] buffer = new byte[1000];
			DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);
			socket.receive(replyPacket); // blocking
			
			String reply = new String(replyPacket.getData(), replyPacket.getOffset(), replyPacket.getLength());
			logger.info("ServiceClientUDPProxy: received message: " + reply);
     		logger.info("ServiceClientUDPProxy: " + op + "(" + arg + ") ==> " + reply);
     		
     		if (reply.startsWith("#")) {
        		/* e' un risultato */
        		result = reply.substring(1);
        	} else if (reply.startsWith("@")) {
        		/* si e' verificata una ServiceException */
        		String message = reply.substring(1);
        		throw new ServiceException(message);
        	} else {
        		/* risposta malformata, solleva una RemoteException */
        		throw new RemoteException("Malformed reply: " + reply);
        	}
     		
			socket.close();
		} catch (SocketException e) {
			String message = "SocketException: " + e.getMessage();
			this.logger.info("Client Proxy: " + message);
			throw new RemoteException(message);
		} catch (IOException e) {
			String message = "IOException: " + e.getMessage();
			this.logger.info("Client Proxy: " + message);
			throw new RemoteException(message);
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
		
		return result;
	}
}
