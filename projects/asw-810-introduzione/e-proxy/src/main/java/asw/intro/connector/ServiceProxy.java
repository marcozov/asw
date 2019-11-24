package asw.intro.connector;

import java.util.logging.Logger;

import asw.intro.service.Service;

public class ServiceProxy implements Service {
	private Service service;
	private Logger logger = Logger.getLogger("asw.intro.connector");
	
	public ServiceProxy(Service service) {
		logger.info("ServiceProxy: CREATION");
		this.service = service;
	}
	
	@Override
	public String alpha(String arg) {
		logger.info("Proxy: calling alpha(" + arg + ")");
		String result = this.service.alpha(arg);
		logger.info("Proxy: alpha(" + arg + ") ==> " + result);
		return result;
	}

}
