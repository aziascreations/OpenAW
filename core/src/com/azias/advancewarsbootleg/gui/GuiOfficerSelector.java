package com.azias.advancewarsbootleg.gui;

import com.azias.advancewarsbootleg.Assets;
import com.azias.advancewarsbootleg.enums.EnumCommandingOfficer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuiOfficerSelector extends Gui {

	public GuiOfficerSelector(int id, int posX, int posY, int sizeX, int sizeY) {
		super(posX, posY, sizeX, sizeY);
		this.id = id;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], this.size[0], this.size[1]);
		int borderWidth = 3;
		batch.draw(Assets.guiDefaultBack, this.position[0], this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+this.size[0]-borderWidth, this.position[1], borderWidth, this.size[1]);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1], this.size[0]-borderWidth*2, borderWidth);
		batch.draw(Assets.guiDefaultBack, this.position[0]+borderWidth, this.position[1]+this.size[1]-borderWidth, this.size[0]-borderWidth*2, borderWidth);
		
		EnumCommandingOfficer[] cos = EnumCommandingOfficer.values();
		int nbrX = (int)Math.floor((this.size[0]-borderWidth*2-10)/Assets.cosHeads[0].getRegionWidth());
		for(int i=0; i<cos.length/nbrX; i++) {
			for(int j=0; j<nbrX; j++) {
				batch.draw(Assets.cosHeads[i*(cos.length/nbrX)+j],0,Assets.cosHeads[0].getRegionHeight()*i);
			}
		}
	}
}
