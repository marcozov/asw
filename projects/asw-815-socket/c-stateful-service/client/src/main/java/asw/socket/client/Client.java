package asw.socket.client;

import java.util.logging.Logger;

import asw.socket.service.RemoteCounterService;

public class Client {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	private RemoteCounterService service;
	
	public Client() {
		this.logger.info("[" + this.getClass().getPackageName() +"] Client: creation...");
	}
	
	public void setService(RemoteCounterService service) {
		this.logger.info("Client: setService()");
		this.service = service;
	}
	
	public void run() {
		this.logger.info("********* [CLIENT] Using Service *********");
		
		
		this.logger.info("");
		this.logger.info("********* [CLIENT] Used Service *********");
	}
}
