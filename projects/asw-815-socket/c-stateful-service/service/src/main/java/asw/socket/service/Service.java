package asw.socket.service;

public interface Service {
	/***
	 *  converts arg to uppercase. arg must be composed by at least 4 characters.
	 * @param arg
	 * @return UPPERCASE(arg)
	 */
	public String alpha(String arg) throws ServiceException, RemoteException;
	
	/***
	 *  converts arg to lowercase. arg must not be null.
	 * @param arg
	 * @return lowercase(arg)
	 */
	public String beta(String arg) throws ServiceException, RemoteException;
}
