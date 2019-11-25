package asw.socket.main;

import asw.socket.client.Client;
import asw.socket.context.ApplicationContext;

public class Main {

	public static void main(String[] args) {
		Client client = null;
		if (args.length > 0) {
			client = ApplicationContext.getInstance().getClient(args[0]);
		} else {
			client = ApplicationContext.getInstance().getClient();
		}
		
		client.run();
	}

}
