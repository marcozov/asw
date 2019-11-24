package asw.intro.connector;

import asw.intro.server.Servant;
import asw.intro.service.Service;

import java.util.logging.Logger;

public class ServiceFactory {
	private Logger logger = Logger.getLogger("asw.intro.connector");
	private static ServiceFactory instance = null;
	private Service service = null;
	private ServiceFactory() {
		this.logger.info("Creating ServiceFactory");
	}
	
	public static synchronized ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		
		return instance;
	}
	
	public synchronized Service getService() {
		this.logger.info("ServiceFactory: getService()");
		if (this.service == null) {
			this.service = new Servant();
		}
		
		return this.service;
	}
}
