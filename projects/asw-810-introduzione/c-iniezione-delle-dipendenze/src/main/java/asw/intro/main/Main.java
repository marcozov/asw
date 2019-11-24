package asw.intro.main;

import asw.intro.client.Client;
import asw.intro.connector.ServiceFactory;
import asw.intro.service.Service;

public class Main {

	public static void main(String[] args) {
		Service service = ServiceFactory.getInstance().getService();
		Client client = new Client();
		
		// dependency injection
		client.setService(service);
		client.run();
	}

}
