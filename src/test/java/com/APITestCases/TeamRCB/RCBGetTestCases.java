package com.APITestCases.TeamRCB;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.TeamRCB.Reports.*;
import com.TeamRCB.utils.*;
import com.relevantcodes.extentreports.LogStatus;

public class RCBGetTestCases extends BaseClass {

	private static final LibraryRCB PROPERTIES = LibraryRCB.getInstance();

	@Test(enabled = true, groups = "Regression", description = "To validate the RCB team has only 4 foreign players")
	public void api_01_validateForeignPlayersCount() throws Exception {
		int testNumber = 1;
		String testIdentifier = "ValidateForeignPlayersCount";
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC " + testNumber + "_" + testIdentifier);

		// Fetch JSON file from specified file location
		String baseURI = PropertiesOperations.getPropertyValueByKey("baseURI");
		String endPoint = PropertiesOperations.getPropertyValueByKey("endPoint");

		ExtentTestManager.getTest().log(LogStatus.INFO, " <html><b><font color=\"red\">TeamRCB.json</font></b></html> :"
				+ LibraryRCB.getResponse(baseURI, endPoint).asPrettyString());

		// return count of ForeignPlayers
		int noOfForeignPlayers = PROPERTIES.validateForeignPlayersFromJSON(baseURI, endPoint);

		// asserting if count is 4, otherwise throw custom exception(more than 4 are not allowed)
		try {
			Assert.assertTrue(noOfForeignPlayers == 4, "Count of foreign players is not exactly 4");
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"<html><b><font color=\"green\">Response contains exactly</font></b></html>: " + noOfForeignPlayers
							+ " foreign players");
		} catch (AssertionError e) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"<html><b><font color=\"red\">Response doesn't contains exactly 4 foreign players</font></b></html>.It contains "
							+ noOfForeignPlayers + " foreign players");
			throw new Exception("Assertion Exception " + noOfForeignPlayers
					+ "  foreign players found(more than 4 are not allowed)", e);
		}

	}

	@Test(enabled = true, groups = "Regression", description = "To validate the team has at least 1 wicket keeper")
	public void api_02_validateWicketKeepersCount() throws Exception {
		int testNumber = 2;
		String testIdentifier = "ValidateWicketKeepersCount";
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC " + testNumber + "_" + testIdentifier);

		// Fetching JSON file from specified file location
		String baseURI = PropertiesOperations.getPropertyValueByKey("baseURI");
		String endPoint = PropertiesOperations.getPropertyValueByKey("endPoint");

		ExtentTestManager.getTest().log(LogStatus.INFO, " <html><b><font color=\"red\">TeamRCB.json</font></b></html> :"
				+ LibraryRCB.getResponse(baseURI, endPoint).asPrettyString());

		// return count of WicketKeepers
		int noOfWicketKeepers = PROPERTIES.validateWicketKeeperFromJSON(baseURI, endPoint);

		// asserting if count is less than 1, otherwise throw custom exception(less than 1 is not allowed)
		try {
			Assert.assertTrue(noOfWicketKeepers >= 1, "Count of Wicket Keepers is not atleast 1");
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"<html><b><font color=\"green\">Response contains atleast</font></b></html>: " + noOfWicketKeepers
							+ " wicket keeper");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.INFO,
					"<html><b><font color=\"red\">Response doesn't contains atleast 1  wicket keeper</font></b></html>.It contains "
							+ noOfWicketKeepers + " wicket keeper");
			throw new Exception(
					"Assertion Exception " + noOfWicketKeepers + "  wicket keeper found(less than 1 is not allowed)",
					e);
		}
	}
}
