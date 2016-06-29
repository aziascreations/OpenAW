package com.azias.awbe.units;

import com.azias.openaw.map.Unit;

public class Units {
	public static void registerUnits() {
		Unit.registerUnit("infantry", new Infantry());
	}
}
