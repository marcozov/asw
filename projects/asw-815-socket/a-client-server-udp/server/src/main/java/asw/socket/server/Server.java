package asw.socket.server;

import java.util.logging.Logger;

import asw.socket.server.connector.ServiceServerUDPProxy;
import asw.socket.service.Service;
import asw.socket.service.impl.ServiceImpl;

public class Server {
	private static int SERVER_PORT=6789;
	private static Logger logger = Logger.getLogger(Server.class.getPackageName());
	
	public static void main(String[] args) {
		Service service = new ServiceImpl();
		logger.info("Server: starting...");
		ServiceServerUDPProxy server = new ServiceServerUDPProxy(service, SERVER_PORT);
		server.run();
		logger.info("Server: stopped...");
	}

}
