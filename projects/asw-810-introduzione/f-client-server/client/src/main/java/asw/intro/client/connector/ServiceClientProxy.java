package asw.intro.client.connector;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

import asw.intro.service.Service;

public class ServiceClientProxy implements Service {
	private InetAddress address;
	private int port;
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	
	public ServiceClientProxy(InetAddress address, int port) {
		this.logger.info("[" + this.getClass().getPackageName() + 
				"] ServiceClientProxy: Created a new Remote Proxy for Service " + 
				address.toString() + ":" + port);
		this.address = address;
		this.port = port;
	}
	
	@Override
	public String alpha(String arg) {
		String result = null;
		try {
			String request = "alpha#" + arg;
			byte[] requestMessage = request.getBytes();
			DatagramPacket requestPacket = new DatagramPacket(requestMessage, requestMessage.length,
																this.address, this.port);
			
			DatagramSocket socket = new DatagramSocket();
			socket.setSoTimeout(5000);
     		logger.info("ServiceClientProxy: calling alpha(" + arg + ")");
     		logger.info("ServiceClientProxy: sending message: " + request);
			socket.send(requestPacket); // non-blocking
			
			byte[] buffer = new byte[1000];
			DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);
			socket.receive(replyPacket); // blocking
			
			result = new String(replyPacket.getData(), replyPacket.getOffset(), replyPacket.getLength());
			logger.info("ServiceClientProxy: received message: " + result);
     		logger.info("ServiceClientProxy: alpha(" + arg + ") ==> " + result);
     		socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
