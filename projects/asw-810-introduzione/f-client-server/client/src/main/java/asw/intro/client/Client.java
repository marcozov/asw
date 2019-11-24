package asw.intro.client;

import asw.intro.service.Service;

import java.util.logging.*;

public class Client {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	private Service service;
	
	public Client() {
		this.logger.info("[" + this.getClass().getPackageName() + "] Client: CREATION");
	}
	
	public void setService(Service service) {
		this.logger.info("Client: setService()");
		this.service = service;
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
