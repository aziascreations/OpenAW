package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.enums.EnumCommandingOfficer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiLobbyPlayerList extends Gui {
	protected String[] playerNames;
	protected GlyphLayout[] glyphLayouts;
	protected EnumCommandingOfficer[] cos;
	protected String readyList;
	
	public GuiLobbyPlayerList(int id) {
		super(10, Gdx.graphics.getHeight()-10-310, 300, 310);
		this.id = id;
		this.playerNames = new String[4];
		for(int i=0; i<this.playerNames.length; i++) {
			this.playerNames[i] = "null";
		}
		this.glyphLayouts = new GlyphLayout[4];
		for(int i=0; i<this.glyphLayouts.length; i++) {
			this.glyphLayouts[i] = new GlyphLayout(Assets.font36, this.playerNames[i]);
		}
		this.cos = new EnumCommandingOfficer[4];
		for(int i=0; i<this.cos.length; i++) {
			this.cos[i] = null;
		}
		this.readyList = "2222";
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], this.size[1]);
		int borderWidth = 3;
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+this.size[0]-borderWidth, this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1], this.size[0]-borderWidth*2, borderWidth);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);
		
		int renderOffsetY = 5;
		for(int i=0; i<playerNames.length; i++) {
			Assets.font36.draw(batch, this.glyphLayouts[i], this.position[0]+7, this.position[1]+this.size[1]-renderOffsetY);
			renderOffsetY += this.glyphLayouts[i].height+2;
			if(this.cos[i]!=null) {
				batch.draw(Assets.cosHeads[this.cos[i].getID()], this.position[0]+7, this.position[1]+this.size[1]-renderOffsetY-Assets.cosHeads[this.cos[i].getID()].getRegionHeight());
			} else {
				batch.draw(Assets.cosNull[0], this.position[0]+7, this.position[1]+this.size[1]-renderOffsetY-Assets.cosNull[0].getRegionHeight());
			}
			if(this.readyList.charAt(i)!='2') {
				if(this.readyList.charAt(i)=='0') {
					//batch.draw(Assets.guiIcons16[6][0], this.position[0]+7+48+4, this.position[1]+this.size[1]-renderOffsetY-16);
					batch.draw(Assets.guiIcons32[1][0], this.position[0]+7+48+4, this.position[1]+this.size[1]-renderOffsetY-32-4);
				} else {
					//batch.draw(Assets.guiIcons16[7][0], this.position[0]+7+48+4, this.position[1]+this.size[1]-renderOffsetY-16);
					batch.draw(Assets.guiIcons32[0][0], this.position[0]+7+48+4, this.position[1]+this.size[1]-renderOffsetY-32-4);
				}
			}
			renderOffsetY += Assets.cosHeads[0].getRegionHeight()+6;
		}
	}
	
	@Override
	public void tick(long millis) {
		this.playerNames = ((String)(Datas.getJSResult("getLobbyUsernames"))).split("\\.");
		for(int i=0; i<this.glyphLayouts.length; i++) {
			this.glyphLayouts[i] = new GlyphLayout(Assets.font36, this.playerNames[i]);
		}
		
		String[] selectedCOs = ((String)(Datas.getJSResult("getLobbyCOs"))).split("\\.");
		for(int i=0; i<this.cos.length; i++) {
			this.cos[i] = EnumCommandingOfficer.getCOByAssetsName(selectedCOs[i]);
		}
		
		this.readyList = (String)(Datas.getJSResult("getLobbyReadyList"));
	}
}
