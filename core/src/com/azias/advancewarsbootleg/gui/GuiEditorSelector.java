package com.azias.advancewarsbootleg.gui;

import java.util.ArrayList;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiEditorSelector extends Gui {
	protected int selectedMenu;
	protected int selectedTeam;
	
	public GuiEditorSelector(int id) {
		this(id,0,0);
	}
	
	public GuiEditorSelector(int id, int par2, int par3) {
		super(0,0,Gdx.graphics.getWidth(),102);
		this.selectedMenu = par2;
		this.selectedTeam = par3;
		this.addButtons(this.selectedMenu);
		this.isLockingMouseClick = true;
		this.id = id;
	}
	
	protected void addButtons(int par1) {
		this.buttonList = new ArrayList<GuiButton>();
		this.buttonList.add(new GuiButton(-1, "tabs.terrain", 16, this.size[1], 32*2, 32*2, new TextureRegion(Assets.guiEditorSelectTileTabs,0,0,32,32)));
		this.buttonList.add(new GuiButton(-1, "tabs.buildings", 16*2+32*2, this.size[1], 32*2, 32*2, new TextureRegion(Assets.guiEditorSelectTileTabs,0,32,32,32)));
		this.buttonList.add(new GuiButton(-1, "tabs.units", 16*3+32*4, this.size[1], 32*2, 32*2, new TextureRegion(Assets.guiEditorSelectTileTabs,0,64,32,32)));
		this.buttonList.add(new GuiButton(-1, "tabs.special", 16*4+32*6, this.size[1], 32*2, 32*2, new TextureRegion(Assets.guiEditorSelectTileTabs,0,96,32,32)));
		if(par1==0) {
			int b = 64;
			int a = (this.size[0]-11*b)/12;
			for(int i=0; i<Assets.guiEditorSelectTileImages.length; i++) {
				this.buttonList.add(new GuiButton(-1, "tile."+i, a+i*b+i*a, this.size[1]/2-b/2, b, b, Assets.guiEditorSelectTileImages[i]));
			}
		} else if(par1==1) {
			int c = (this.size[1]-(3*32))/5;
			this.buttonList.add(new GuiButton(-1, "team.os", c, 3*c+2*32, 32, 32, Assets.guiIcons[0][0]));
			this.buttonList.add(new GuiButton(-1, "team.bm", c, 2*c+32, 32, 32, Assets.guiIcons[1][0]));
			this.buttonList.add(new GuiButton(-1, "team.yc", c, c, 32, 32, Assets.guiIcons[2][0]));
			this.buttonList.add(new GuiButton(-1, "team.gh", 2*c+32, 3*c+2*32, 32, 32, Assets.guiIcons[3][0]));
			this.buttonList.add(new GuiButton(-1, "team.bh", 2*c+32, 2*c+32, 32, 32, Assets.guiIcons[4][0]));
			this.buttonList.add(new GuiButton(-1, "team.nl", 2*c+32, c, 32, 32, Assets.guiIcons[5][0]));
			
			int b = 64;
			int a = ((this.size[0]-3*c-2*32)-6*b)/7;
			for(int i=0; i<6; i++) {
				this.buttonList.add(new GuiButton(-1, "building."+i+"."+this.selectedTeam, a+i*b+i*a+(3*c+2*32), this.size[1]/2-b/2, b, b, Assets.guiEditorSelectBuildingImages[i][this.selectedTeam]));
			}
		} else if(par1==2) {
			int c = (this.size[1]-(3*32))/5;
			this.buttonList.add(new GuiButton(-1, "team.os", c, 3*c+2*32, 32, 32, Assets.guiIcons[0][0]));
			this.buttonList.add(new GuiButton(-1, "team.bm", c, 2*c+32, 32, 32, Assets.guiIcons[1][0]));
			this.buttonList.add(new GuiButton(-1, "team.yc", c, c, 32, 32, Assets.guiIcons[2][0]));
			this.buttonList.add(new GuiButton(-1, "team.gh", 2*c+32, 3*c+2*32, 32, 32, Assets.guiIcons[3][0]));
			this.buttonList.add(new GuiButton(-1, "team.bh", 2*c+32, 2*c+32, 32, 32, Assets.guiIcons[4][0]));
			this.buttonList.add(new GuiButton(-1, "team.nl", 2*c+32, c, 32, 32, Assets.guiIcons[5][0]));
			//Units
		} else {
			//Special
		}
	}
	
	@Override
	public boolean executeButtonAction(String buttonId) {
		if(buttonId.equals("tabs.terrain")) {
			this.selectedMenu=0;
			this.addButtons(this.selectedMenu);
			return true;
		}
		if(buttonId.equals("tabs.buildings")) {
			this.selectedMenu=1;
			this.addButtons(this.selectedMenu);
			return true;
		}
		if(buttonId.equals("tabs.units")) {
			this.selectedMenu=2;
			this.addButtons(this.selectedMenu);
			return true;
		}
		if(buttonId.equals("tabs.special")) {
			this.selectedMenu=3;
			this.addButtons(this.selectedMenu);
			return true;
		}
		
		if(buttonId.contains("team")) {
			String chosenTeam = buttonId.split("\\.")[1];
			switch(chosenTeam) {
			case "os":
				this.selectedTeam = 0;
				break;
			case "bm":
				this.selectedTeam = 1;
				break;
			case "yc":
				this.selectedTeam = 2;
				break;
			case "gh":
				this.selectedTeam = 3;
				break;
			case "bh":
				this.selectedTeam = 4;
				break;
			case "nl":
				this.selectedTeam = 5;
				break;
			}
			this.addButtons(this.selectedMenu);
			return true;
		}
		return false;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], 1);
		this.renderButtons(batch);
	}
}
