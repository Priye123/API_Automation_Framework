package com.TeamRCB.Reports;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.TeamRCB.utils.Mailing;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	public static List<String> exceptionList = new ArrayList<>();

	@BeforeMethod(alwaysRun = true)
	public void beforeStart(Method method) {
		ExtentTestManager.startTest(method.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void afterTest(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP,
					result.getMethod().getMethodName() + " Test Skipped" + result.getThrowable());

		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, result.getMethod().getMethodName() + " Test Passed");
		}
		if (result.getStatus() != ITestResult.SUCCESS) {
			exceptionList.add(getExceptionInfo(result));
		}
		ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
		ExtentManager.getReporter().flush();

	}
	
	public static String getExceptionInfo(ITestResult result) {
		StringBuffer sb = new StringBuffer(500);
		sb.append(result.getThrowable().getClass().getName());
		//System.out.println(sb.toString());
		return sb.toString();
	}

	protected String getStackTrace(Throwable t) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		t.printStackTrace(printWriter);
		return writer.toString();
	}
	
	@AfterSuite
	public void endSetup() throws AddressException, IOException, MessagingException {

		Runtime r = Runtime.getRuntime();
		r.addShutdownHook(new Thread() {

			public void run() {
				Mailing sm = new Mailing();
				try {
					sm.mail();
					System.out.println("Report has been sent");
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			//System.out.println(e);
		}
	}

	
}
