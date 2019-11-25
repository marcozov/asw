package asw.socket.service.impl;

import java.util.logging.Logger;

import asw.socket.service.RemoteException;
import asw.socket.service.Service;
import asw.socket.service.ServiceException;

public class ServiceImpl implements Service {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	
	@Override
	public String alpha(String arg) throws ServiceException {
		if (arg == null || arg.length() < 4) {
			String errorMessage = "ServiceImpl: executing alpha: ServiceException: argument is null or too short.";
			logger.info(errorMessage);
			throw new ServiceException(errorMessage);
		}
		
		String result = arg.toUpperCase();
		this.logger.info("ServiceImpl: executing alpha(" + arg + ") ==> " + result);
		return result;
	}

	@Override
	public String beta(String arg) throws ServiceException {
		if (arg == null) {
			String errorMessage = "ServiceImpl: executing beta: ServiceException: argument is null";
			logger.info(errorMessage);
			throw new ServiceException(errorMessage);
		}
		
		String result = arg.toLowerCase();
		this.logger.info("ServiceImpl: executing beta(" + arg + ") ==> " + result);
		sleep(arg.length()*100);
		return result;
	}

	private void sleep(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			
		}
	}

}
