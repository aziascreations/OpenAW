package com.azias.advancewarsbootleg.map;

import com.azias.advancewarsbootleg.enums.EnumBuildingType;

public class BuildingHQ extends Building{

	protected BuildingHQ(int posX, int posY, int teamId) {
		super(EnumBuildingType.HQ, posX, posY, teamId);
	}

}
