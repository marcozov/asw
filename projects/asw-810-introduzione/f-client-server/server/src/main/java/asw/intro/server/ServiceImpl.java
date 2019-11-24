package asw.intro.server;

import java.util.logging.Logger;

import asw.intro.service.Service;

public class ServiceImpl implements Service {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	
	public ServiceImpl() {
		this.logger.info("ServiceImpl: CREATION");
	}
	
	@Override
	public String alpha(String arg) {
		String result = arg.toUpperCase();
		logger.info("ServiceImpl: executing alpha(" + arg + ") ==> " + result);
		return result;
	}

}
