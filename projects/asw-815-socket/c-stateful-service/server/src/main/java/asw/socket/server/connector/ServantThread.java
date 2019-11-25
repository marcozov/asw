package asw.socket.server.connector;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

import asw.socket.service.CounterService;
import asw.socket.service.RemoteException;
import asw.socket.service.ServiceException;

public class ServantThread extends Thread {
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());
	
	private CounterService service;
	
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;
	
	boolean done = false;

	private static int MAX_SERVANT_THREAD_ID = 0;
	private int servantThreadId;

	public ServantThread(Socket clientSocket, CounterService service) {
		try {
			this.clientSocket = clientSocket;
			this.service = service;
			this.servantThreadId = MAX_SERVANT_THREAD_ID++;

			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			logger.info("Server Proxy: IO Exception: " + e.getMessage());
		}
	}
	
	public void run() {
		this.logger.info("Server Proxy: opening connection [" + this.servantThreadId + "]");
		try {
			while(!done) {
				String request = in.readUTF();
				logger.info("Server Proxy: connection [" + servantThreadId + "]: received request: " + request);
				String op = this.getOp(request);
				String arg = this.getParam(request);
				String reply = null;
				
				try {
					reply = executeOperation(op, arg);
				} catch (RemoteException e) {
					// should NOT happen. But it happens if the format of request is wrong
					reply = "";
				}
				
				logger.info("Server Proxy: connection [" + servantThreadId + "]: sending reply: " + reply);
				out.writeUTF(reply);
			}
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
	
	private String executeOperation(String op, String arg) throws RemoteException {
		String reply = null;
		String message = "";
		
		if (op.equals("CONNECT")) {
			this.done = false;
			reply = "ACK";
			message = "Server Proxy: connection [" + servantThreadId + "]: connect";
		} else if(op.equals("DISCONNECT")) {
			this.done = true;
			reply = "ACK";
			message = "Server Proxy: connection [" + servantThreadId + "]: disconnect";
		} else if ( op.equals("getGlobalCounter") ) {
			reply = String.valueOf(this.service.getGlobalCounter());
			message = "Server Proxy: connection [" + servantThreadId + "]: " +
    				"service.getGlobalCounter()" + " --> " + reply;
		} else if ( op.equals("getSessionCounter") ) {
			reply = String.valueOf(this.service.getSessionCounter());
			message = "Server Proxy: connection [" + servantThreadId + "]: " +
    				"service.getSessionCounter()" + " --> " + reply;
		} else if ( op.equals("incrementCounter") ) {
			this.service.incrementCounter();
			reply = "";
			message = "Server Proxy: connection [" + servantThreadId + "]: " +
    				"service.incrementCounter()";
		} else {
            throw new RemoteException("Operation " + op + " is not supported");
        }

		this.logger.info(message);
		return reply;
	}
}
