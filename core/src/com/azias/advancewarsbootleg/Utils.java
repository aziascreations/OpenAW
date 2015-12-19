package com.azias.advancewarsbootleg;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
