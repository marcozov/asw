package asw.intro.client;

import asw.intro.connector.ServiceFactory;
import asw.intro.service.Service;

import java.util.logging.*;

public class Client {
	private Logger logger = Logger.getLogger("asw.intro.client");
	private Service service;
	
	public Client() {
		this.logger.info("New client creation");
		this.service = ServiceFactory.getInstance().getService();
	}
	
	public void run() {
		this.logger.info("Client: using a service to convert all a string to UPPERCASE");
		String[] data = new String[] {"hello", "Test", "END"};
		
		for (String arg : data) {
			logger.info("Client: calling alpha(" + arg + ")");
			String result = this.service.alpha(arg);
			logger.info("Client: alpha(" + arg + ") ==> " + result);
		}
	}
}
