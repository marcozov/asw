package asw.intro.context;

import java.util.logging.Logger;

import asw.intro.client.Client;
import asw.intro.connector.ServiceProxy;
import asw.intro.server.Servant;
import asw.intro.service.Service;

public class ApplicationContext {
	private Logger logger = Logger.getLogger("asw.intro.context");
	private static ApplicationContext instance = null;
	private Service service = null;
	
	private ApplicationContext() {
		this.logger.info("ApplicatioContext: CREATION");
	}
	
	public static synchronized ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}
		
		return instance;
	}
	
	public synchronized Service getService() {
		this.logger.info("ApplicationContext: getService()");
		if (this.service == null) {
			this.service = new Servant();
		}
		
		Service proxy = new ServiceProxy(this.service);
		return proxy;
	}
	
	public Client getClient() {
		this.logger.info("ApplicationContext: getClient()");
		Client client = new Client();
		client.setService(this.getService());
		
		return client;
	}
}
