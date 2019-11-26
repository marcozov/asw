package asw.socket.server.connector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import asw.socket.service.impl.CounterServant;


public class CounterServiceServerTCPProxy {
	private int port;
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());

	public CounterServiceServerTCPProxy(int port) {
		logger.info("[" + this.getClass().getPackageName() + "] ServiceServerTCPProxy: Created a new Remote Proxy for Service listening on port " + port);
        this.port = port;
	}
	
	public void run() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(this.port);
			socket.setSoTimeout(0);
			
			while(true) {
				this.logger.info("ServiceServerTCPProxy: creating new client socket (and thread)..");
				Socket clientSocket = socket.accept();
				this.logger.info("ServiceServerTCPProxy: created new client socket (and thread)..");
				ServantThread thread = new ServantThread(clientSocket, new CounterServant());
				thread.start();
			}
		} catch (IOException e) {
			this.logger.info("Server Proxy: IO Exception: " + e.getMessage());
		} finally {
			if (socket!=null) {
				try {
					socket.close();
				} catch (IOException e) {
					this.logger.info("Server Proxy: IOException when closing ServerSocket: " + e.getMessage());
				}
			}
		}

	}
}
