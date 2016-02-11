package com.azias.advancewarsbootleg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.xerial.snappy.Snappy;

import com.badlogic.gdx.Gdx;

public class Utils {
	private static SecureRandom random = new SecureRandom();
	
	public static String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
	
	public static String getFormatedTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	public static String getTextFromLang(String key) {
		String text = Datas.lang.get(key);
		if(text!=null) {
			return text;
		} else {
			return key;
		}
	}
	
	public String getStringMD5(String par1) {
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5");
			mdEnc.update(par1.getBytes(), 0, par1.length());
			return new BigInteger(1, mdEnc.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void showErrorMessageBox(String text) {
		JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static byte[] fileToByteArray(String path) throws IOException {
		return Files.readAllBytes(Paths.get(path));
	}
	
	public static String fileToString(String path, Charset encoding) throws IOException  {
		return new String(fileToByteArray(path), encoding);
	}
	
	public static byte[] uncompressByteArray(byte[] input) throws UnsupportedEncodingException, IOException {
		return Snappy.uncompress(input);
	}
	
	public static byte[] compressString(String input) throws UnsupportedEncodingException, IOException {
		return Snappy.compress(input.getBytes("UTF-8"));
	}
	
	public static byte[] compressFile(String path) throws IOException {
		return Snappy.compress(fileToByteArray(path));
	}
	
	public static boolean saveByteArray(String path, byte[] input, boolean overwrite) {
		try {
			File f = new File(path);
			if(!f.isDirectory()) {
				if(f.exists() && !overwrite) {
					Gdx.app.log(getFormatedTime(), "Error: A file named \""+f.getName()+"\" alredy exists.");
					return false;
				} else if(f.exists() && overwrite) {
					if(!f.delete()) {
						Gdx.app.log(getFormatedTime(), "Error: Unable to delete the previous file.");
						return false;
					}
				}
				FileOutputStream fos = new FileOutputStream(path);
				fos.write(input);
				fos.close();
				return true;
			} else {
				Gdx.app.log(getFormatedTime(), "Error: Unable to save the file as a directory.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
