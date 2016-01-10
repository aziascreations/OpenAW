package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiServerConnect extends Gui {
	private GlyphLayout glTitle, glIp, GlPort;
	private int state = 0;
	
	public GuiServerConnect(int id) {
		super(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		this.id = id;
		this.addButtons();
		this.glTitle = new GlyphLayout();
		this.glTitle.setText(Assets.font72,"Connect to a server");
		this.glIp = new GlyphLayout();
		this.glIp.setText(Assets.font48,"Ip adress:");
		this.GlPort = new GlyphLayout();
		this.GlPort.setText(Assets.font48,"Port:");
	}
	
	private void addButtons() {
		if(state==0) {
			this.buttonList.add(new GuiButton(-1, "connect.cancel", this.size[0]/4-200/2, this.position[1]+60, 200, 60, Utils.getTextFromLang("gui.exit")));
			this.buttonList.add(new GuiButton(-1, "connect.msgtest", this.size[0]/2-200/2, this.position[1]+60, 200, 60, Utils.getTextFromLang("gui.msgtest")));
			this.buttonList.add(new GuiButton(-1, "connect.go", this.size[0]/4*3-200/2, this.position[1]+60, 200, 60, Utils.getTextFromLang("gui.connect")));
			this.buttonList.add(new GuiButton(-1, "connect.bypass", this.size[0]/4*3-200/2, this.position[1]+120+30, 200, 60, Utils.getTextFromLang("gui.bypass")));
			this.textFieldList.add(new GuiTextField(0, Gdx.graphics.getWidth()/2-300/2, Gdx.graphics.getHeight()/2-40/2+60, 300, 40));
			this.textFieldList.add(new GuiTextField(1, Gdx.graphics.getWidth()/2-300/2, Gdx.graphics.getHeight()/2-40/2-60, 300, 40));
		}
	}
	
	@Override
	public boolean executeButtonAction(String buttonId) {
		if(buttonId.equals("connect.go")) {
			return false;
		}
		return false;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Assets.font72.draw(batch, this.glTitle, this.size[0]/2-this.glTitle.width/2, (int)(this.size[1]-this.glTitle.height*1.1));
		Assets.font48.draw(batch, this.glIp, this.size[0]/2-this.glIp.width/2, this.size[1]/2-this.glIp.height/2+120+10);
		Assets.font48.draw(batch, this.GlPort, this.size[0]/2-this.GlPort.width/2, this.size[1]/2-this.GlPort.height/2+10);
		
		this.renderButtons(batch);
		this.renderTextFields(batch);
	}

	@Override
	public String processMouseClick(int posX, int posY) {
		for(int i=0; i<this.buttonList.size(); i++) {
			if(this.buttonList.get(i).isClicked(posX, posY)) {
				if(!this.executeButtonAction(this.buttonList.get(i).getActionId())) {
					return this.buttonList.get(i).getActionId();
				}
			}
		}
		for(int i=0; i<this.textFieldList.size(); i++) {
			this.textFieldList.get(i).isClicked(posX, posY);
		}
		return null;
	}
}
