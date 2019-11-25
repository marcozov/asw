package asw.socket.service.impl;

public class CounterApplicationState {
	private static CounterApplicationState instance = null;
	private int globalCounter = 0;
	
	private CounterApplicationState() {
		
	}
	 
	public static synchronized CounterApplicationState getInstance() {
		if (instance == null) {
			instance = new CounterApplicationState();
		}
		
		return instance;
	}
	
	public void incrementGlobalCounter() {
		this.globalCounter++;
	}
	
	public int getGlobalCounter() {
		return this.globalCounter;
	}
}
