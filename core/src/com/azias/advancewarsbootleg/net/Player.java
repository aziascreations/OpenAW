package com.azias.advancewarsbootleg.net;

import com.azias.advancewarsbootleg.enums.EnumCommandingOfficer;

public class Player extends Object {
	protected String sessionKey;
	protected String username;
	protected int id;
	protected boolean isReady = false;
	protected EnumCommandingOfficer co;
	
	public Player(int id) {
		this.id = id;
		System.out.println("Created user "+this.id);
		this.co = null;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getSessionKey() {
		return this.sessionKey;
	}
	
	public int getId() {
		return this.id;
	}
}
