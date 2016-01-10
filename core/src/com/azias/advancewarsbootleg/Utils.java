package com.azias.advancewarsbootleg;

import java.io.IOException;
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

public class Utils {
	private static SecureRandom random = new SecureRandom();
	
	public static String getFormatedTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	public static String readFile(String path, Charset encoding) throws IOException  {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static String nextSessionId() {
		return new BigInteger(130, random).toString(32);
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
	
	/*public boolean isValidMD5(String md5) {
	    return md5.matches("[a-fA-F0-9]{32}");
	}*/
	
	public static void showErrorMessageBox(String text) {
		JOptionPane.showMessageDialog(null, text, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
