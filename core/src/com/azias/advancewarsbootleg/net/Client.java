package com.azias.advancewarsbootleg.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.azias.advancewarsbootleg.Utils;

public class Client implements Runnable {
	private Socket socket = null;
	private Thread thread = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private ClientThread client = null;
	
	//Game Datas
	private ArrayList<String> commandsList = new ArrayList<String>();
	
	public Client(String serverName, int serverPort) {
		System.out.println("Establishing connection. Please wait ...");
		try {
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			start();
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}

	public void run() {
		/*try {
			streamOut.writeUTF("Msg 1");
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println("Sending error: " + ioe.getMessage());
			stop();
		}*/
		/*while (thread != null) {
			try {
				streamOut.writeUTF(console.readLine());
				streamOut.flush();
			} catch (IOException ioe) {
				System.out.println("Sending error: " + ioe.getMessage());
				stop();
			}
		}/**/
	}
	
	public void sendInput(String input) {
		try {
			//streamOut.writeUTF("Hi");
			streamOut.writeUTF(input);
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println("Sending error: " + ioe.getMessage());
			stop();
		}
	}
	
	public String getAction() {
		if(commandsList.size()>0) {
			String a = commandsList.get(0);
			//commandsList.remove(0);
			return a;
		} else {
			return null;
		}
	}

	public void handle(String msg) {
		/*if(msg.equals(".bye")) {
			System.out.println("Good bye. Press RETURN to exit ...");
			stop();
		}*/
		if(msg.charAt(0)=='/') {
			if(msg.equals("/bye")) {
				System.out.println("Server shutdown.");
				stop();
			} else {
				commandsList.add(msg.substring(1));
			}
		} else {
			System.out.println(msg);
		}
		
	}

	public void start() throws IOException {
		console = new DataInputStream(System.in);
		streamOut = new DataOutputStream(socket.getOutputStream());
		if(thread == null) {
			client = new ClientThread(this, socket);
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if(thread != null) {
			thread.stop();
			thread = null;
		}
		try {
			if(console != null)
				console.close();
			if(streamOut != null)
				streamOut.close();
			if(socket != null)
				socket.close();
		} catch(IOException ioe) {
			System.out.println("Error closing ...");
		}
		client.close();
		client.stop();
	}

	/*public static void main(String args[]) {
		Client client = null;
		if (args.length != 2)
			System.out.println("Usage: java ChatClient host port");
		else
			client = new Client(args[0], Integer.parseInt(args[1]));
	}*/

	public static void startClient(String host, String port) {
		Client client = null;
		client = new Client(host, Integer.parseInt(port));
	}
}