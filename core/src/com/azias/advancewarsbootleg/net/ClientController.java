package com.azias.advancewarsbootleg.net;

public class ClientController extends Object{
	private Client client;
	
	public ClientController() {
		
	}
	
	public void createClient(String host, String port) {
		this.client = new Client(host, Integer.parseInt(port));
	}
	
	public void sendInput(String input) {
		this.client.sendInput(input);
	}
	
	public void stop(String input) {
		this.client.stop();
	}
}
