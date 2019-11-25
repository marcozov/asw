package asw.socket.server.connector;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Logger;

import asw.socket.service.RemoteException;
import asw.socket.service.Service;
import asw.socket.service.ServiceException;


public class ServiceServerUDPProxy {
	private Service service;
	private int port;
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());

	public ServiceServerUDPProxy(Service service, int port) {
		logger.info("[" + this.getClass().getPackageName() + "] ServiceServerUDPProxy: Created a new Remote Proxy for Service listening on port " + port);
        this.service = service;
        this.port = port;
	}
	
	public void run() {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(this.port);
			socket.setSoTimeout(0);
			byte[] buffer = new byte[1000];
			while(true) {
				getRequestAndSendReply(socket, buffer);
			}
		} catch (SocketException e) {
			logger.info("Server Proxy: Socket Exception: " + e.getMessage());
		} finally {
			if (socket!=null) {
				socket.close();
			}
		}

	}
	
	private void getRequestAndSendReply(DatagramSocket socket, byte[] buffer) {
		DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(requestPacket); // blocking
		
			String request = new String(
					requestPacket.getData(),
					requestPacket.getOffset(),
					requestPacket.getLength()
					);
			logger.info("ServiceServerUDPProxy: received request: " + request);
			
			String op = getOp(request);
			String arg = getParam(request);
			String reply = null;
			
			try {
				String result = executeOperation(op, arg);
				reply = "#" + result;
			} catch (ServiceException e) {
				// some error occurred during the computation. Check the argument
				reply = "@" + e.getMessage();
			} catch (RemoteException e) {
				// should NOT happend
				reply = "@" + e.getMessage();
			}
			
			byte[] replyMessage = reply.getBytes();
			DatagramPacket replyPacket = new DatagramPacket(replyMessage, replyMessage.length,
															requestPacket.getAddress(),
															requestPacket.getPort());
			socket.send(replyPacket);
		} catch (IOException e) {
			logger.info("ServiceServerUDPProxy: IO Exception: " + e.getMessage());
		}
	}
	
	private String getOp(String request) {
		int sep = request.indexOf("$"); // request: option#parameter
		String arg = request.substring(0, sep);
		return arg;
	}

	private String getParam(String request) {
		int sep = request.indexOf("$"); // request: option#parameter
		String arg = request.substring(sep+1);
		return arg;
	}
	
	private String executeOperation(String op, String arg) throws ServiceException, RemoteException {
		String reply = null;
		
		logger.info("ServiceServerUDPProxy: calling " + op + "(" + arg + ")");
		if (op.equals("alpha")) {
			reply = this.service.alpha(arg);
		} else if (op.equals("beta")) {
			reply = this.service.beta(arg);
		} else {
			throw new RemoteException("Operation "+ op + " not supported");
		}
		
		logger.info("ServiceServerUDPProxy: " + op + "(" + arg + ") ==> " + reply);
		return reply;
	}
}
