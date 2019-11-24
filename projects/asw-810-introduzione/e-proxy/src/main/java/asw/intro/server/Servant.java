package asw.intro.server;

import java.util.logging.Logger;

import asw.intro.service.Service;

public class Servant implements Service {
	Logger logger = Logger.getLogger(this.getClass().getPackageName());
	
	public Servant() {
		this.logger.info("[" + this.getClass().getPackageName() + "] Creating Servant");
	}

	@Override
	public String alpha(String arg) {
		String result = arg.toUpperCase();
		logger.info("Servant: executing alpha(" + arg + ") ==> " + result);
		return result;
	}

}
