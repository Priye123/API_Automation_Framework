package com.TeamRCB.utils;

import java.util.List;

//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.TeamRCB.Reports.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

//import com.TeamRCB.Reports.ExtentTestManager;
//import com.relevantcodes.extentreports.LogStatus;

//import com.Email365.Library.LibraryRetarus;

import io.restassured.path.json.JsonPath;

public class LibraryRCB {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryRCB.class);

	public static int getForeignPlayersList(List<String> listname) {
		int countForeignCountries = 0;
		for (int i = 0; i < listname.size(); i++) {
			String status = listname.get(i);
			if (!(status.equalsIgnoreCase("India"))) {
				countForeignCountries += 1;
			}
		}

		LOGGER.info("No. of foreign players::" + countForeignCountries);
		return countForeignCountries;
	}

	private static int getWicketKeeperList(List<String> listname) {
		int countWicketKeepers = 0;
		for (int i = 0; i < listname.size(); i++) {
			String status = listname.get(i);
			if (status.equalsIgnoreCase("Wicket-keeper")) {
				countWicketKeepers += 1;
			}
		}
		
		LOGGER.info("No. of wicket keepers::" + countWicketKeepers);
		return countWicketKeepers;
	}

	public static int validateForeignPlayersFromJSON(JSONObject requestBody) throws Exception {

		JsonPath jpath = new JsonPath(requestBody.toString());
		List<String> playersSet = jpath.getList("player.country");
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"<html><b><font color=\"black\">Players Belong to these Countries:</font></b></html> " + playersSet);
		return LibraryRCB.getForeignPlayersList(playersSet);
	}

	public static int validateWicketKeeperFromJSON(JSONObject requestBody) {
		JsonPath jpath = new JsonPath(requestBody.toString());
		List<String> wicketKeepersSet = jpath.getList("player.role");
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"<html><b><font color=\"black\">Players Belong to these Roles:</font></b></html> " + wicketKeepersSet);
		return LibraryRCB.getWicketKeeperList(wicketKeepersSet);
	}

}
