package com.azias.advancewarsbootleg.screens;

import java.util.ArrayList;

import com.azias.advancewarsbootleg.AdvanceWarsBootleg;
import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;
import com.azias.advancewarsbootleg.gui.GuiButton;
import com.azias.advancewarsbootleg.gui.GuiChatBox;
import com.azias.advancewarsbootleg.gui.GuiLobbyPlayerList;
import com.azias.advancewarsbootleg.gui.GuiMapPreview;
import com.azias.advancewarsbootleg.gui.GuiServerConnect;
import com.azias.advancewarsbootleg.net.ClientController;
import com.azias.advancewarsbootleg.net.CommandHandler;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenLobby extends ScreenAdapter implements ApplicationListener, InputProcessor {
	AdvanceWarsBootleg game;
	private int state;
	private long lastReceivedCommandTick;

	public ScreenLobby(AdvanceWarsBootleg game) {
		this.game = game;
		this.state = -1;
		Gdx.input.setInputProcessor(this);
		Datas.coGui.createGui(1, new GuiServerConnect(1));
		this.lastReceivedCommandTick = System.currentTimeMillis();
	}

	@Override
	public void create() {
		
	}

	public void update () {
		switch(this.state) {
		case 0: //Connecting to the server
			if(System.currentTimeMillis()-this.lastReceivedCommandTick > 500) {
				ArrayList<String> a = Datas.coClient.getCommandsList();
				for(int i=0; i<a.size(); i++) {
					if(a.get(i).contains("server#_#connected")) {
						i=a.size(); //Potential info loss.
						this.state = 1;
						Datas.loadJSScript("initGuiLobby");
						Datas.coGui.killAll();
						Datas.coGui.createGui(0, new GuiLobbyPlayerList(0));
						Datas.coGui.createGui(1, new GuiMapPreview(1, 20+Datas.coGui.getGui(0).getSize()[0], Gdx.graphics.getHeight()-10-(Gdx.graphics.getHeight()-30-60), Gdx.graphics.getWidth()-30-Datas.coGui.getGui(0).getSize()[0], Gdx.graphics.getHeight()-30-60));
						Datas.coGui.createGui(2, new GuiChatBox(2, 10, 10, Datas.coGui.getGui(0).getSize()[0], Gdx.graphics.getHeight()-30-Datas.coGui.getGui(0).getSize()[1]));
						Datas.coGui.createGui(3, new GuiButton(3, "lobby.switchpanel", 20+Datas.coGui.getGui(0).getSize()[0], 10, 200, 60, Utils.getTextFromLang("gui.switchpanel")));
						Datas.coGui.createGui(4, new GuiButton(4, "lobby.exit", Datas.coGui.getGui(0).getSize()[0]+(Gdx.graphics.getWidth()-Datas.coGui.getGui(0).getSize()[0])/2-100, 10, 200, 60, Utils.getTextFromLang("gui.exit")));
						Datas.coGui.createGui(5, new GuiButton(5, "lobby.ready", Gdx.graphics.getWidth()-10-200, 10, 200, 60, Utils.getTextFromLang("gui.ready")));
						Datas.coClient.sendInput("!set#_#name#_#MyName");
						Datas.coClient.sendInput("!set#_#sk#_#"+Datas.sessionKey);
						Datas.coClient.sendInput("!getlobbyinfo");
					} else if(a.get(i).contains("error.something")) {
						//TODO: Show an error message
						System.out.println("You shouldn't see this message.");
					}
				}
				this.lastReceivedCommandTick = System.currentTimeMillis();
			}
			break;
		case 1: //In the lobby
			if(System.currentTimeMillis()-this.lastReceivedCommandTick > 500) {
				Datas.coGui.tick();
				this.lastReceivedCommandTick = System.currentTimeMillis();
				CommandHandler.handleCommandsList(Datas.coClient.getCommandsList());
			}
			break;
		default:
			
		}
	}

	@Override
	public void render() {
		game.batch.begin();
		//Background
		game.batch.draw(Assets.background,Gdx.graphics.getWidth()/2-Assets.background.getWidth()/2,Gdx.graphics.getHeight()/2-Assets.background.getHeight()/2);
		
		Datas.coGui.render(game.batch);
		game.batch.end();
	}

	@Override
	public void render(float delta) {
		update();
		render();
	}

	@Override
	public void pause() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return Datas.coGui.processKeyboardInput(character);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		String actionID = Datas.coGui.processMouseClick(screenX, Gdx.graphics.getHeight()-screenY);
		if(actionID != null) {
			return this.actionPerformed(actionID);
		}
		return false;
	}
	
	private boolean actionPerformed(String actionID) {
		if(actionID.equals("connect.cancel")) {
			Datas.coGui.killAll();
			this.game.setScreen(new ScreenMainMenu(this.game));
			return true;
		}
		if(actionID.equals("connect.msgtest")) {
			Datas.coClient.sendInput(Datas.coGui.getTextFieldInput(1));
			//Datas.coGui.killAll();
			//Datas.coGui.createGui(2, new GuiLobbyMain(2));
			return true;
		}
		if(actionID.equals("connect.go")) {
			Datas.coClient = new ClientController();
			Datas.coClient.createClient(Datas.coGui.getTextFieldInput(0), "27030");
			this.state = 0;
			return true;
		}
		if(actionID.equals("lobby.ready")) {
			Datas.coClient.sendInput("!set#_#ready");
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
