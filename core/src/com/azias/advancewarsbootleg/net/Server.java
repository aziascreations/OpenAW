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
	private Player[] players = new Player[4];
	
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
		if(input.charAt(0)=='/') {
			System.out.println("Command detected.");
			String[] command = input.substring(1).split("#_#");
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
		} else if(input.charAt(0)=='!') {
			System.out.println("Internal command detected.");
			String[] command = input.substring(1).split("#_#");
			try {
				if(command[0].equals("getlobbyinfo")) {
					//Returns the lobby's infos to the user.
					int a = findClient(id);
					if(a != -1) {
						String[] names = {"Not Connected","Not Connected","Not Connected","Not Connected"};
						for(int i=0; i<clientCount; i++) {
							names[i] = players[i].getUsername();
						}
						clients[a].send("!lobby#_#names#_#"+names[0]+"#_#"+names[1]+"#_#"+names[2]+"#_#"+names[3]);
						//Args: lobby.map.[map's folder].[map's file name] - Use "null" if not map is chosen yet.
						//clients[a].send("!lobby#_#map#_#null#_#null");
						
						String[] cosList = {"null", "null", "null", "null"};
						for(int i=0; i<clientCount; i++) {
							if(players[i].co!=null) {
								cosList[i]=players[i].co.getAssetsName();
							}
						}
						clients[a].send("!lobby#_#cos#_#"+cosList[0]+"#_#"+cosList[1]+"#_#"+cosList[2]+"#_#"+cosList[3]);
						
						char[] readyList = {'2','2','2','2'};
						for(int i=0; i<clientCount; i++) {
							if(players[i].isReady) {
								readyList[i]='1';
							} else {
								readyList[i]='0';
							}
						}
						clients[a].send("!lobby#_#ready#_#"+String.valueOf(readyList));
						
						clients[a].send("!chat#_#add#_#You got the lobby infos.");
					}
				} else if(command[0].equals("set")) {
					//Setters
					if(command[1].equals("name")) {
						int a = findClient(id);
						if(a != -1) {
							players[a].setUsername(command[2]);
						}
					} else if(command[1].equals("sk")) {
						int a = findClient(id);
						if(a != -1) {
							players[a].setSessionKey(command[2]);
						}
					} else if(command[1].equals("co")) {
						
					} else if(command[1].equals("ready")) {
						int a = findClient(id);
						if(a != -1) {
							boolean hasChanged = false;
							if(players[a].isReady) {
								players[a].isReady = false;
								hasChanged = true;
							} else {
								if(players[a].co!=null && !players[a].username.equals("null")) {
									players[a].isReady = true;
									hasChanged = true;
								} else {
									clients[a].send("!chat#_#add#_#You can't get ready now.");
								}
							}
							if(hasChanged) {
								char[] readyList = {'2','2','2','2'};
								for(int i=0; i<clientCount; i++) {
									if(players[i].isReady) {
										readyList[i]='1';
									} else {
										readyList[i]='0';
									}
								}
								for(int i=0; i<clientCount; i++) {
									clients[i].send("!lobby#_#ready#_#"+String.valueOf(readyList));
								}
							}
						}
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
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
				for (int i = pos + 1; i < clientCount; i++) {
					clients[i-1] = clients[i];
					players[i-1] = players[i];
				}
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
			players[clientCount] = new Player(clients[clientCount].getID());
			try {
				clients[clientCount].open();
				clients[clientCount].start();
				clientCount++;
				for(int i=0; i<clientCount; i++) {
					clients[i].send("/server#_#connected#_#"+clients[clientCount-1].getID());
				}
			} catch (IOException ioe) {
				System.out.println("Error opening thread: " + ioe);
			}
		} else {
			System.out.println("Client refused: maximum " + clients.length + " reached.");
		}
	}

	public static void startServer(int port, int maxPlayers, String password) {
		adminPassword = password;
		Server server = null;
		server = new Server(port);
	}
}