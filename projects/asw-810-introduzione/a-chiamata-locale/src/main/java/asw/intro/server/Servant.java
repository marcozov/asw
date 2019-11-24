package asw.intro.server;

import asw.intro.service.Service;

public class Servant implements Service {

	@Override
	public String alpha(String arg) {
		return "Servant: This is what I got: " + arg;
	}

}
