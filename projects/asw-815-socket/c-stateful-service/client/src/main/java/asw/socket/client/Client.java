package asw.socket.client;

import java.util.logging.Logger;

import asw.socket.service.RemoteCounterService;
import asw.socket.service.RemoteException;

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
		try {
			this.logger.info("********* [CLIENT] Using Service *********");
			
			this.logger.info("Client: service.connect()");
			this.service.connect();			
			
			for (int i=0; i < 3; i++) {
				this.logger.info("Client: service.getGlobaCounter() ==> " + this.service.getGlobalCounter());
				this.logger.info("Client: service.getSessionCounter() ==> " + this.service.getSessionCounter());
				
				this.logger.info("Client: service.incrementCounter()");
				this.service.incrementCounter();
				
				randomSleep(200, 400);
			}
			
			this.logger.info("Client: service.disconnect()");
			this.service.disconnect();
			
			this.logger.info("");
			this.logger.info("********* [CLIENT] Used Service *********");
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void randomSleep(int minDelay, int maxDelay) {
    	int delay = (int)(minDelay + Math.random()*(maxDelay-minDelay));
    	sleep(delay);
	}
	
	public static void sleep(int delay) {
        try {
        	// int delay = (int)(Math.random()*maxDelay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}
}
