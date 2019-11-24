package asw.intro.client;

import asw.intro.server.Servant;
import asw.intro.service.Service;

public class Client {
	private Service service;
	
	public Client() {
		this.service = new Servant();
	}
	
	public void run() {
		System.out.println(this.service.alpha("Dry run"));
	}
}
