package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.Datas;
import com.azias.advancewarsbootleg.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiOptionsMenu extends Gui {
	protected GlyphLayout glTitle, glVolumeEffects;
	
	public GuiOptionsMenu(int id) {
		super(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.id = id;
		this.addButtons();

		this.glTitle = new GlyphLayout();
		this.glTitle.setText(Assets.font72,"Options");
		this.glVolumeEffects = new GlyphLayout();
		this.glVolumeEffects.setText(Assets.font48,String.valueOf((int)(Datas.volumeEffects*10)*10)+"%");
	}

	private void addButtons() {
		this.buttonList.add(new GuiButton(-1, "volume.effects.plus", this.size[0]/4-60/2+80, this.size[1]/2+30+60*2, 60, 60, "+"));
		this.buttonList.add(new GuiButton(-1, "volume.effects.minus", this.size[0]/4-60/2-80, this.size[1]/2+30+60*2, 60, 60, "-"));
		
		this.buttonList.add(new GuiButton(-1, "save", this.size[0]/4-200/2, this.position[1]+60, 200, 60, Utils.getTextFromLang("gui.save")));
		this.buttonList.add(new GuiButton(-1, "exit", this.size[0]/4*3-200/2, this.position[1]+60, 200, 60, Utils.getTextFromLang("gui.exit")));
	}

	@Override
	public void render(SpriteBatch batch) {
		Assets.font72.draw(batch, this.glTitle, this.size[0]/2-this.glTitle.width/2, (int)(this.size[1]-this.glTitle.height*1.1)); //this.position[1]+this.size[1]/2+this.glyphLayout.height/2
		Assets.font48.draw(batch, this.glVolumeEffects, this.size[0]/4-this.glVolumeEffects.width/2, this.size[1]/2+180+this.glVolumeEffects.height/2);
		this.renderButtons(batch);
	}
	
	@Override
	public boolean executeButtonAction(String buttonId) {
		if(buttonId.equals("volume.effects.plus")) {
			if(Datas.volumeEffects<1.0F) {
				Datas.volumeEffects+=0.1F;
				this.glVolumeEffects.setText(Assets.font48,String.valueOf((int)(Datas.volumeEffects*10)*10)+"%");
			}
			return true;
		}
		if(buttonId.equals("volume.effects.minus")) {
			if(Datas.volumeEffects>0F) {
				Datas.volumeEffects-=0.1F;
				this.glVolumeEffects.setText(Assets.font48,String.valueOf((int)(Datas.volumeEffects*10)*10)+"%");
			}
			return true;
		}
		return false;
	}
}
