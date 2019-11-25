package asw.socket.server.connector;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

import asw.socket.service.RemoteException;
import asw.socket.service.Service;
import asw.socket.service.ServiceException;

public class ServantThread extends Thread {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	
	private Service service;
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;

	private static int MAX_SERVANT_THREAD_ID = 0;
	private int servantThreadId;

	public ServantThread(Socket clientSocket, Service service) {
		try {
			this.clientSocket = clientSocket;
			this.service = service;
			this.servantThreadId = MAX_SERVANT_THREAD_ID++;
			/* potrebbero anche andare all'inizio del metodo run */ 
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			logger.info("Server Proxy: IO Exception: " + e.getMessage());
		}
	}
	
	public void run() {
		this.logger.info("Server Proxy: opening connection [" + this.servantThreadId + "]");
		try {
			String request = in.readUTF();
			String op = this.getOp(request);
			String arg = this.getParam(request);
			String reply = null;
			
			try {
				String result = executeOperation(op, arg);
				reply = "#" + result;
			} catch (ServiceException e) {
				// some error occurred during the computation. Check the argument
				reply = "@" + e.getMessage();
			} catch (RemoteException e) {
				// should NOT happen. But it happens if the format of request is wrong
				reply = "@" + e.getMessage();
			}
			
			logger.info("Server Proxy: connection [" + servantThreadId + "]: sending reply: " + reply);
			out.writeUTF(reply);
			
		} catch (EOFException e) {
			this.logger.info("Server Proxy: connection [" + servantThreadId + "]: EOFException: " + e.getMessage());
		} catch (IOException e) {
			this.logger.info("Server Proxy: connection [" + servantThreadId + "]: IOException: " + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				this.logger.info("Server Proxy: connection [" + servantThreadId + "]: IOException: " + e.getMessage());
			}
		}
		this.logger.info("Server Proxy: closing connection [" + servantThreadId + "]");
	}
	
	private String getOp(String request) {
		int sep = request.indexOf("$"); // request: option#parameter
		String arg = request.substring(0, sep);
		return arg;
	}

	private String getParam(String request) {
		int sep = request.indexOf("$"); // request: option#parameter
		String arg = request.substring(sep+1);
		return arg;
	}
	
	private String executeOperation(String op, String arg) throws ServiceException, RemoteException {
		String reply = null;
		
		logger.info("ServiceServerTCPProxy: calling " + op + "(" + arg + ")");
		if (op.equals("alpha")) {
			reply = this.service.alpha(arg);
		} else if (op.equals("beta")) {
			reply = this.service.beta(arg);
		} else {
			throw new RemoteException("Operation "+ op + " not supported");
		}
		
		logger.info("ServiceServerTCPProxy: " + op + "(" + arg + ") ==> " + reply);
		return reply;
	}
}
