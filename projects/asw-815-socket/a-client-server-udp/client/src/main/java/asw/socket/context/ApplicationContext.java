package asw.socket.context;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import asw.socket.client.connector.ServiceClientUDPProxy;
import asw.socket.client.Client;
import asw.socket.service.Service;

public class ApplicationContext {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	private static final String DEFAULT_SERVER_ADDRESS = "localhost";
	private static int SERVER_PORT = 6789;
	
	private static ApplicationContext instance = null;
	
	private ApplicationContext() {
		this.logger.info("[" + this.getClass().getPackageName() +"] ApplicationContext: creation...");
	}
	
	public static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}
		return instance;
	}
	
	public Service getService() {
		return getService(DEFAULT_SERVER_ADDRESS);
	}
	
	public Service getService(String serverHost) {
		Service proxy = null;
		try {
			int port = SERVER_PORT;
			InetAddress address = InetAddress.getByName(serverHost);
			proxy = new ServiceClientUDPProxy(address, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return proxy;
	}
	
	public Client getClient() {
		return getClient(DEFAULT_SERVER_ADDRESS);
	}
	
	public Client getClient(String serverHost) {
		logger.info("ApplicationContext: getClient(" + serverHost + ")");
		Client client = new Client();
		Service service = this.getService(serverHost);
		client.setService(service);
		return client;
	}
}
