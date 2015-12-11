package com.azias.advancewarsbootleg.map;

import com.azias.advancewarsbootleg.enums.EnumBuildingType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

@Deprecated
public class BuildingFactory extends Building {

	public BuildingFactory(int posX, int posY, int teamId) {
		super(EnumBuildingType.Factory, posX, posY, teamId);
	}
	
	@Override
	public void render(SpriteBatch batch, int x, int y) {
		/*batch.draw(
				((TextureRegion[])Assets.buildingsGraphics[this.buildingType.getId()])[this.faction.getId()],
				x,
				y,
				Assets.tileRenderSize[Assets.tileRenderSizeIndex],
				Assets.tileRenderSize[Assets.tileRenderSizeIndex]*2);*/
	}

}
