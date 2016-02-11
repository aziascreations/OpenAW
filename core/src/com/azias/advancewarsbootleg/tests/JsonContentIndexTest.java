package com.azias.advancewarsbootleg.tests;

import java.util.Hashtable;
import java.util.Map;

import com.google.gson.Gson;

public class JsonContentIndexTest {
	
	public static void main(String[] args) {
		RessourceIndex rscIndex = new RessourceIndex();
		rscIndex.add("myPath", "myMd5");
		rscIndex.add("mySecondPath", "mySecondMd5");
		
		Gson gson = new Gson();
		String json = gson.toJson(rscIndex);
		System.out.println(json);
		
	}
}

class RessourceIndex {
	protected Map<String, String> index;
	
	public RessourceIndex() {
		this.index = new Hashtable<String, String>();
	}
	
	public void add(String path, String md5) {
		this.index.put(path, md5);
	}
	
	/*public String getPath() {
		return this.path;
	}
	
	public String getMd5() {
		return this.md5;
	}*/
	
	@Override
	public String toString() {
	   return "DataObject [index="+this.index+"]";
	}
}