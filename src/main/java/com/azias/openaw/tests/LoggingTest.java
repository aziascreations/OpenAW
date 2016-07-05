package com.azias.openaw.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest {
	private final static Logger logger = LoggerFactory.getLogger(LoggingTest.class);
	
	public static void main(String[] args) {
		logger.info("This is an information message");
		logger.error("this is a error message");
		logger.warn("this is a warning message");
	}
}