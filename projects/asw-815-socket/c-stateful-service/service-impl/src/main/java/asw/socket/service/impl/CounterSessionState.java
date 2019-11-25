package asw.socket.service.impl;

public class CounterSessionState {
	private int sessionCounter;
	
	public CounterSessionState() {
		this.sessionCounter = 0;
	}
	
	public void incrementSessinCounter() {
		this.sessionCounter++;
	}
	
	public int getSessionCounter() {
		return this.sessionCounter;
	}
}
