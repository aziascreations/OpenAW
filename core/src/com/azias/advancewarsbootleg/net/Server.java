package com.azias.advancewarsbootleg.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;

import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;

public class Server implements Runnable {
	private ServerThread clients[] = new ServerThread[4];
	private ServerSocket server = null;
	private Thread thread = null;
	private int clientCount = 0;
	
	//Game Datas
	private static String adminPassword;
	private int adminUserId;
	private Map<Integer, String> usernames;

	public Server(int port) {
		try {
			System.out.println(Utils.getFormatedTime()+": Binding to port " + port + ", please wait...");
			//System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println(Utils.getFormatedTime()+": Server started: " + server);
			//System.out.println("Server started: " + server);
			usernames = new Hashtable<Integer, String>();
			start();
		} catch (IOException e) {
			System.err.println(Utils.getFormatedTime()+": Can not bind to port " + port);
			e.printStackTrace();
			//System.exit(1);
			//System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
		}
	}

	public void run() {
		//Load things here.
		while (thread != null) {
			try {
				System.out.println("Waiting for a client ...");
				addThread(server.accept());
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
				stop();
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	private int findClient(int ID) {
		for (int i = 0; i < clientCount; i++)
			if (clients[i].getID() == ID)
				return i;
		return -1;
	}
	
	public synchronized void handle(int id, String input) {
		System.out.println(Utils.getFormatedTime()+": "+id+" - "+input);
		if(input.charAt(0)=='!') {
			System.out.println("Command detected.");
			String[] command = input.substring(1).split("\\.");
			try {
				if(command[0].equals("login")) {
					if(command[1].equals(adminPassword)) {
						adminUserId = id;
					}
				} else if(command[0].equals("setname")) {
					if(usernames.containsKey(id)) {
						usernames.remove(id);
					}
					usernames.put(id, command[1]);
				} else if(command[0].equals("getname")) {
					int a = findClient(id);
					if(a != -1) {
						System.out.println("Sent username to "+id);
						clients[a].send(usernames.get(id));
					} else {
						System.out.println("Username not found");
					}
				} else if(command[0].equals("gettestcommand")) {
					int a = findClient(id);
					if(a != -1) {
						clients[a].send("/test."+System.currentTimeMillis());
					}
				} else if(command[0].equals("help")) {
					
				} else {
					int a = findClient(id);
					if(a != -1) {
						clients[a].send("Command not found");
					}
				}
				/*} else if(command[0].equals("admin")) {
					if(adminUserId==id) {
						
					}
				}*/
			} catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		} else if(input.charAt(0)=='/') {
			System.out.println("Internal command detected.");
			
		} else {
			for(int i=0; i<clientCount; i++) {
				clients[i].send(id+": "+input);
			}
		}	
	}
	
	public synchronized void remove(int ID) {
		int pos = findClient(ID);
		if (pos >= 0) {
			ServerThread toTerminate = clients[pos];
			System.out.println("Removing client thread " + ID + " at " + pos);
			if (pos < clientCount - 1)
				for (int i = pos + 1; i < clientCount; i++)
					clients[i - 1] = clients[i];
			clientCount--;
			try {
				toTerminate.close();
			} catch (IOException ioe) {
				System.out.println("Error closing thread: " + ioe);
			}
			toTerminate.stop();
		}
	}

	private void addThread(Socket socket) {
		if (clientCount < clients.length) {
			System.out.println("Client accepted: " + socket);
			clients[clientCount] = new ServerThread(this, socket);
			try {
				clients[clientCount].open();
				clients[clientCount].start();
				clientCount++;
			} catch (IOException ioe) {
				System.out.println("Error opening thread: " + ioe);
			}
		} else
			System.out.println("Client refused: maximum " + clients.length + " reached.");
	}

	/*public static void main(String args[]) {
		Server server = null;
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		else
			server = new Server(Integer.parseInt(args[0]));
	}*/

	public static void startServer(int port, int maxPlayers, String password) {
		adminPassword = password;
		Server server = null;
		server = new Server(port);
	}
}