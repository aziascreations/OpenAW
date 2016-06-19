package com.azias.awbe;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {

	public static String getFormatedTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	/**
	 * List all the files and folders from a directory
	 * @param directoryName to be listed
	 */
	public static ArrayList<File> listFilesAndFolders(String directoryName) {
		ArrayList<File> files = new ArrayList<File>();
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for(File file : fList) {
			//System.out.println(file.getName());
			files.add(file);
		}
		return files;
	}
	
	/**
	 * List all the files under a directory
	 * @param directoryName to be listed
	 */
	public static ArrayList<File> listFiles(String directoryName ){
		ArrayList<File> files = new ArrayList<File>();
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for(File file : fList) {
			if (file.isFile()) {
				files.add(file);
			}
		}
		return files;
	}
	
	/**
	 * List all the folder under a directory
	 * @param directoryName to be listed
	 */
	public static ArrayList<File> listFolders(String directoryName) {
		ArrayList<File> files = new ArrayList<File>();
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for(File file : fList) {
			if (file.isDirectory()) {
				files.add(file);
			}
		}
		return files;
	}
	
	/**
	 * List all files from a directory and its subdirectories
	 * @param directoryName to be listed
	 */
	public void listFilesAndFilesSubDirectories(String directoryName) {
		File directory = new File(directoryName);
		//get all the files from a directory
		File[] fList = directory.listFiles();
		for(File file : fList) {
			if (file.isFile()) {
				System.out.println(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}
	}
	
	public static byte[] fileToByteArray(String path) throws IOException {
		return Files.readAllBytes(Paths.get(path));
	}
	
	public static String fileToString(String path, Charset encoding) throws IOException  {
		return new String(fileToByteArray(path), encoding);
	}
	
	public static String fileToString(String path) throws IOException  {
		return fileToString(path, StandardCharsets.UTF_8);
	}

	public static String fileToString2(String path) throws IOException  {
		return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
	}
}
