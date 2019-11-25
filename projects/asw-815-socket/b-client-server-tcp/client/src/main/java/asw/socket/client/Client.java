package asw.socket.client;

import java.util.logging.Logger;

import asw.socket.service.RemoteException;
import asw.socket.service.Service;
import asw.socket.service.ServiceException;

public class Client {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	private Service service;
	
	public Client() {
		this.logger.info("[" + this.getClass().getPackageName() +"] Client: creation...");
	}
	
	public void setService(Service service) {
		this.logger.info("Client: setService()");
		this.service = service;
	}
	
	public void run() {
		this.logger.info("********* [CLIENT] Using Service *********");
		
		this.logger.info("");
		this.logger.info("Client: callAlpha(\"Alphabet\") should be \"ALPHABET\"");
		this.callAlpha("Alphabet");
		
		this.logger.info("");
		this.logger.info("Client: callAlpha(\"Az\") should raise \"ServiceException\"");
		this.callAlpha("Az");
		

		this.logger.info("");
		this.logger.info("Client: callBeta(\"Alpha\") should be \"alpha\"");
		this.callBeta("alpha");
		
		this.logger.info("");
		this.logger.info("Client: callBeta(\"AbcDefGhiLmnOpqRstUvz\") should raise \"RemoteException\"");
		this.callBeta("AbcDefGhiLmnOpqRstUvz");
		
		this.logger.info("");
		this.logger.info("********* [CLIENT] Used Service *********");
	}

	private void callAlpha(String arg) {
		try {
			this.logger.info("Client: calling alpha(" + arg + ")");
			String result = this.service.alpha(arg);
			this.logger.info("Client: alpha(" + arg +") ==> " + result);
		} catch (ServiceException e) {
			logger.info("Client: ServiceException while executing alpha(" + arg + ") ==> " + e.getMessage());
		} catch (RemoteException e) {
			logger.info("Client: RemoteException while executing alpha(" + arg + ") ==> " + e.getMessage());
		}
	};
	
	private void callBeta(String arg) {
		try {
			logger.info("Client: calling beta(" + arg + ")");
			String result = this.service.beta(arg);
			logger.info("Client: beta(" + arg + ") ==> " + result);
		} catch (ServiceException e) {
			logger.info("Client: ServiceException while executing beta(" + arg + ") ==> " + e.getMessage());
		} catch (RemoteException e) {
			logger.info("Client: RemoteException while executing beta(" + arg + ") ==> " + e.getMessage());
		}
	}
}
