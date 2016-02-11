package com.azias.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The <tt>ContentIndexer</tt> is a simple utility used to index every files
 * in a specific folder and his subfolders.<br>
 * The output is then parsed into a new file called "<i>ContentIndex.json</i>".
 * 
 * @version 1.2
 * 
 * @author Azias
 */
public class ContentIndexer {
	public static RessourceIndex ressourceIndex;
	
	public static void main(String[] args) {
		try {
			System.out.println("Current directory: "+new java.io.File("").getAbsolutePath());
			File f = new File("ContentIndex.json");
			if(f.exists() && !f.isDirectory()) { 
				if(f.delete()){
	    			System.out.println(f.getName() + " has been deleted !");
	    		} else {
	    			System.out.println(f.getName() + " couldn't be deleted !");
	    			System.out.println("You should try to delete it manually !");
	    			throw new Exception();
	    		}
			}
			System.out.println("- - - - -");
			
			ressourceIndex = new RessourceIndex();
			listFiles(new java.io.File("").getAbsolutePath());
			System.out.println("- - - - -");
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(ressourceIndex);
			
			PrintWriter out = new PrintWriter(f.getName());
			out.write(json);
			out.close();
			System.out.println(f.getName()+" has been created !");
			System.out.println(ressourceIndex.getIndexSize()+" file(s) have been indexed !");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listFiles(String dir) {
		File directory = new File(dir);
		
		File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	        	System.out.println("Indexing: "+file.getPath().substring(new java.io.File("").getAbsolutePath().length()+1));
	        	ressourceIndex.add(file.getPath().substring(new java.io.File("").getAbsolutePath().length()+1), checkSumApacheCommons(file.getPath()));
	        } else if (file.isDirectory()) {
	        	listFiles(file.getAbsolutePath());
	        }
	    }
	}
	
	public static String checkSumApacheCommons(String file) {
        String checksum = null;
        try {  
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return checksum;
    }
}

class RessourceIndex {
	private String downloadUrl;
	private Map<String, String> index;
	
	public RessourceIndex() {
		this.downloadUrl = "http://awberessources.olympe.in/datas/";
		this.index = new Hashtable<String, String>();
	}
	
	public void add(String path, String md5) {
		this.index.put(path, md5);
	}
	
	public int getIndexSize() {
		return this.index.size();
	}
	
	public String getDownloadLink() {
		return this.downloadUrl;
	}
	
	@Override
	public String toString() {
	   return "DataObject [downloadUrl="+this.downloadUrl+",index="+this.index+"]";
	}
}
