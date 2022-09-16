package com.TeamRCB.Reports;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {

	static Map<Object,Object> extentHashMap = new HashMap<>();
	static ExtentReports extent = ExtentManager.getReporter();
	
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentHashMap.get((Thread.currentThread().getId()));
	}
	
	public static synchronized void endTest() {
		extent.endTest((ExtentTest) extentHashMap.get((Thread.currentThread().getId())));
	}
	
	public static synchronized ExtentTest startTest(String testName) {
		return startTest(testName, "");
	}
	
	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.startTest(testName, desc);
		extentHashMap.put(Thread.currentThread().getId(), test);
		
		return test;
	}
}
