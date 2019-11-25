package asw.socket.service;

// server-side exception
public class ServiceException extends Exception {
	public ServiceException(String message) {
		super(message);
	}
}
