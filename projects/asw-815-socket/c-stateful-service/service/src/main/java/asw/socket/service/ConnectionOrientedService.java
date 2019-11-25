package asw.socket.service;

public interface ConnectionOrientedService {
	public void connect() throws RemoteException;
	public void disconnect() throws RemoteException;
}