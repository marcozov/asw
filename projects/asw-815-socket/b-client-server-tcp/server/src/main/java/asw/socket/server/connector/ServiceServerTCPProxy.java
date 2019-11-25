package asw.socket.server.connector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import asw.socket.service.Service;


public class ServiceServerTCPProxy {
	private Service service;
	private int port;
	private Logger logger = Logger.getLogger(this.getClass().getPackageName());

	public ServiceServerTCPProxy(Service service, int port) {
		logger.info("[" + this.getClass().getPackageName() + "] ServiceServerTCPProxy: Created a new Remote Proxy for Service listening on port " + port);
        this.service = service;
        this.port = port;
	}
	
	public void run() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(this.port);
			socket.setSoTimeout(0);
			
			while(true) {
				Socket clientSocket = socket.accept();
				ServantThread thread = new ServantThread(clientSocket, this.service);
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
