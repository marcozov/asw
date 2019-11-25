package asw.socket.server;

import java.util.logging.Logger;

import asw.socket.server.connector.CounterServiceServerTCPProxy;
import asw.socket.service.Service;
import asw.socket.service.impl.ServiceImpl;

public class Server {
	private static int SERVER_PORT=7869;
	private static Logger logger = Logger.getLogger(Server.class.getPackageName());
	
	public static void main(String[] args) {
		logger.info("Server: starting...");
		CounterServiceServerTCPProxy server = new CounterServiceServerTCPProxy(SERVER_PORT);
		server.run();
		logger.info("Server: stopped...");
	}

}
