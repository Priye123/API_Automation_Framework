package com.TeamRCB.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.TeamRCB.Reports.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;

//import com.TeamRCB.Reports.ExtentTestManager;
//import com.relevantcodes.extentreports.LogStatus;

//import com.Email365.Library.LibraryRetarus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LibraryRCB {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryRCB.class);
	public static final String APPLICATION_JSON = "application/json";
	private static LibraryRCB libraryRCB;

	// Singleton Design Pattern
	public LibraryRCB() {

	}

	public static synchronized LibraryRCB getInstance() {
		if (libraryRCB == null)
			libraryRCB = new LibraryRCB();

		return libraryRCB;
	}

	public int getForeignPlayersList(List<String> listname) {
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

	private int getWicketKeeperList(List<String> listname) {
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

	public static Response getResponse(String baseURI, String endPoint) throws UnsupportedEncodingException {

		// base URL
		RestAssured.baseURI = baseURI;

		Response r = RestAssured.given().contentType(LibraryRCB.APPLICATION_JSON).when()
				// get request
				.get(endPoint)

				// get response as string
				.then().extract().response();

		LOGGER.info(r.asPrettyString());
		return r;
	}

	public int validateForeignPlayersFromJSON(String baseURI, String endPoint) throws Exception {

		Response response = LibraryRCB.getResponse(baseURI, endPoint);
		JsonPath jpath = response.jsonPath();
		List<String> playersSet = jpath.getList("player.country");
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"<html><b><font color=\"black\">Players Belong to these Countries:</font></b></html> " + playersSet);
		return getForeignPlayersList(playersSet);
	}

	public int validateWicketKeeperFromJSON(String baseURI, String endPoint) throws Exception {

		Response response = LibraryRCB.getResponse(baseURI, endPoint);
		JsonPath jpath = response.jsonPath();
		List<String> wicketKeepersSet = jpath.getList("player.role");
		ExtentTestManager.getTest().log(LogStatus.INFO,
				"<html><b><font color=\"black\">Players Belong to these Roles:</font></b></html> " + wicketKeepersSet);
		return getWicketKeeperList(wicketKeepersSet);
	}

}
