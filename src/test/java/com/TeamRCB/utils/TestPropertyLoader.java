package com.TeamRCB.utils;

public class TestPropertyLoader {

	public static final String REQUEST_BODY = "request_body";
	private static TestPropertyLoader testPropertyLoader;
	private String filePath = "/json/API";

	// Singleton Design Pattern
	private TestPropertyLoader() {

	}

	public static synchronized TestPropertyLoader getInstance() {
		if (testPropertyLoader == null)
			testPropertyLoader = new TestPropertyLoader();

		return testPropertyLoader;
	}

	// return file location of JSON
	public String getFilePath() {
		return filePath;
	}
}