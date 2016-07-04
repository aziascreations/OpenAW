package com.azias.openaw.tests;

import com.azias.openaw.map.Map;
import com.google.gson.Gson;

public class MapExportTest {

	public static void test() {
		Map map = new Map();
		map.test();
		map.prepareExport();
		Gson gson = new Gson();
		String a = gson.toJson(map);
		System.out.println(a);
	}

}
