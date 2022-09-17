package com.TeamRCB.Reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	static ExtentReports extent;
	public synchronized static ExtentReports getReporter() {
		final String filePath = fetchFileName();
		if (extent == null) 
			extent = new ExtentReports(filePath, true);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\ReportConfig.xml"));
		return extent;
	}
	
	public static String fetchFileName()  {
		
		String company="TestVagrant";
		String filename = "[RCB_Team_Validation]["+ company+"]_TestReport"+ dateFormat() +"_Extent.html" ;
		
		System.out.println(filename);
		return "./test-output/html/"+filename;
		
	}
	
	public static String dateFormat()
	{
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		return date;
	}
}
