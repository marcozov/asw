package asw.socket.service.impl;

import java.util.logging.Logger;

import asw.socket.service.CounterService;
import asw.socket.service.RemoteException;

public class CounterServant implements CounterService {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	private CounterApplicationState appState;
	private CounterSessionState sessionState;
	
	public CounterServant() {
		this.appState = CounterApplicationState.getInstance();
		this.sessionState = new CounterSessionState();
	}

	@Override
	public synchronized void incrementCounter() throws RemoteException {
		logger.info("CounterServant: executing incrementCounter()");
		this.appState.incrementGlobalCounter();
		this.sessionState.incrementSessinCounter();
	}

	@Override
	public int getGlobalCounter() throws RemoteException {
		int result = this.appState.getGlobalCounter();
		logger.info("CounterServant: executing getGlobalCounter() ==> " + result);
		return result;
	}

	@Override
	public int getSessionCounter() throws RemoteException {
		int result = this.sessionState.getSessionCounter();
		logger.info("CounterServant: executing getSessionCounter() ==> " + result);
		return result;
	}

}
