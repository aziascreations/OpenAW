package com.azias.awbe.units;

import com.azias.awbe.map.Unit;

public class Units {
	public static void registerUnits() {
		Unit.registerUnit("infantry", new Infantry());
	}
}
