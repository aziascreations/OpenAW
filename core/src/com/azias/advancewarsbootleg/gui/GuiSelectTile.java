package com.azias.advancewarsbootleg.gui;

import java.util.ArrayList;

import com.azias.advancewarsbootleg.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiSelectTile extends Gui {
	protected int selectedMenu;
	
	@Deprecated
	public GuiSelectTile() {
		this(0,0);
	}
	
	public GuiSelectTile(int id) {
		this(id,0);
	}
	
	public GuiSelectTile(int id, int par2) {
		super(0,0,Gdx.graphics.getWidth(),102);
		this.selectedMenu = par2;
		this.addButtons(this.selectedMenu);
		this.isLockingMouseClick = true;
		this.id = id;
	}
	
	protected void addButtons(int par1) {
		this.buttonList = new ArrayList<GuiButton>();
		this.buttonList.add(new GuiButton(-1, "tabs.terrain", 16, this.size[1], 32*2, 32*2, new TextureRegion(Assets.tileTabs,0,0,32,32)));
		this.buttonList.add(new GuiButton(-1, "tabs.buildings", 16*2+32*2, this.size[1], 32*2, 32*2, new TextureRegion(Assets.tileTabs,0,32,32,32)));
		this.buttonList.add(new GuiButton(-1, "tabs.units", 16*3+32*4, this.size[1], 32*2, 32*2, new TextureRegion(Assets.tileTabs,0,64,32,32)));
		if(par1==0) {
			int b = 64;
			int a = (this.size[0]-11*b)/12;
			for(int i=0; i<Assets.editorSelectTileImages.length; i++) {
				this.buttonList.add(new GuiButton(-1, "tile."+i, a+i*b+i*a, this.size[1]/2-b/2, b, b, Assets.editorSelectTileImages[i]));
			}
		} else if(par1==1) {
			//Buildings
		} else {
			//Units
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
		return false;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.arrowFiller, this.position[0], this.position[1], this.size[0], this.size[1]);
		batch.draw(Assets.arrowFiller, this.position[0], this.position[1], this.size[0], 1);
		this.renderButtons(batch);
	}
}
