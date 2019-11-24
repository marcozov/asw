package asw.intro.server.connector;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Logger;

import asw.intro.service.Service;

public class ServiceServerProxy {
	private Service service;
	private int port;
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());

	public ServiceServerProxy(Service service, int port) {
		logger.info("[" + this.getClass().getPackageName() + "] ServiceServerProxy: Created a new Remote Proxy for Service listening on port " + port);
        this.service = service;
        this.port = port;
	}

	public void run() {
		logger.info("ServiceServerProxy: Server started");
		try {
			DatagramSocket socket = new DatagramSocket(this.port);
			socket.setSoTimeout(0);
			byte[] buffer = new byte[1000];
			
			while(true) {
				DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
				socket.receive(requestPacket);
				
				String request = new String(requestPacket.getData(),
											requestPacket.getOffset(),
											requestPacket.getLength());
				this.logger.info("ServiceServerProxy: received request: " + request);
				
				String op = this.getOp(request);
				String arg = this.getParam(request);
				String result = this.executeOperation(op, arg);
				
				this.logger.info("ServiceServerProxy: sending reply: " + result);
				byte[] replyMessage = result.getBytes();
				DatagramPacket replyPacket = new DatagramPacket(replyMessage, replyMessage.length,
																requestPacket.getAddress(),
																requestPacket.getPort());
				socket.send(replyPacket);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getOp(String request) {
		int sep = request.indexOf("#"); // request: option#parameter
		String arg = request.substring(0, sep);
		return arg;
	}

	private String getParam(String request) {
		int sep = request.indexOf("#"); // request: option#parameter
		String arg = request.substring(sep+1);
		return arg;
	}
	
	private String executeOperation(String op, String arg) {
		String reply = null;
		
		logger.info("ServiceServerProxy: calling alpha(" + arg + ")");
		if (op.equals("alpha")) {
			reply = this.service.alpha(arg);
		}
		
		logger.info("ServiceServerProxy: alpha(" + arg + ") ==> " + reply);
		return reply;
	}
}
