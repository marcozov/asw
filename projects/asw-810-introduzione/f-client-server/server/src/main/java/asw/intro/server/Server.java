package asw.intro.server;

import java.util.logging.Logger;

import asw.intro.server.connector.ServiceServerProxy;
import asw.intro.service.Service;

public class Server {
	private static Logger logger = Logger.getLogger(Server.class.getPackageName());
	private static final int SERVER_PORT = 6789;

	public static void main(String[] args) {
		logger.info("[" + Server.class.getPackageName() +"] Server...");
		Service service = new ServiceImpl();
		
		int port = SERVER_PORT;
		logger.info("Server: starting...");
		
		ServiceServerProxy server = new ServiceServerProxy(service, port);
		server.run();
		
		logger.info("Server: stopped");
	}

}
