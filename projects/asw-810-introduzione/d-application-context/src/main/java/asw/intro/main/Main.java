package asw.intro.main;

import asw.intro.client.Client;
import asw.intro.context.ApplicationContext;

public class Main {

	public static void main(String[] args) {
		Client client = ApplicationContext.getInstance().getClient();
		client.run();
	}

}
