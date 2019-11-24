package asw.intro.client.context;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import asw.intro.client.Client;
import asw.intro.client.connector.ServiceClientProxy;
import asw.intro.service.Service;

public class ApplicationContext {
	private Logger logger = Logger.getLogger("asw.intro.client.context");
	private static final String DEFAULT_SERVER_ADDRESS = "localhost";
	private static int SERVER_PORT = 6789;
	
	private static ApplicationContext instance = null;
//	private Service service = null;
	
	private ApplicationContext() {
		this.logger.info("ApplicationContext: CREATION");
	}
	
	public static synchronized ApplicationContext getInstance() {
        if (instance==null) {
        	instance = new ApplicationContext();
        }
        return instance;
    }
	
	public synchronized Service getService() {
		return this.getService(DEFAULT_SERVER_ADDRESS);
	}
	
	public synchronized Service getService(String serverHost) {
		Service proxy = null;
		try {
			int port = SERVER_PORT;
			InetAddress address = InetAddress.getByName(serverHost);
			proxy = new ServiceClientProxy(address, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return proxy;
	}
	
	public Client getClient() {
		this.logger.info("ApplicationContext: getClient()");
		Client client = new Client();
		client.setService(this.getService());
		return client;
	}
	
	public Client getClient(String serverHost) {
		this.logger.info("ApplicationContext: getClient(" + serverHost + ")");
		Client client = new Client();
		client.setService(this.getService(serverHost));
		return client;
	}
}
