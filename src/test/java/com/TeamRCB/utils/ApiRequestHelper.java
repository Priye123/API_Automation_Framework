package com.TeamRCB.utils;

import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.TeamRCB.Reports.BaseClass;

public class ApiRequestHelper extends BaseClass {

	private static ApiRequestHelper apiHelper;

	// Singleton Design Pattern
	private ApiRequestHelper() {

	}

	public static synchronized ApiRequestHelper getInstance() {
		if (apiHelper == null)
			apiHelper = new ApiRequestHelper();

		return apiHelper;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestHelper.class);
	private static final TestPropertyLoader PROPERTIES = TestPropertyLoader.getInstance();

	@SuppressWarnings("unused") // compiler warnings should be suppressed if return null is not used
	public JSONObject getBodyFromJsonFile(String testIdentifier, String actionType) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		LOGGER.info(PROPERTIES.getFilePath() + "_" + testIdentifier + ".json");

		// Reading JSON
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				this.getClass().getResourceAsStream(PROPERTIES.getFilePath() + "_" + testIdentifier + ".json")));
		if ((reader != null)) {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			// fetch value for key request_body:- It will return the desired JSON
			JSONObject data = (JSONObject) jsonObject.get(actionType);

			LOGGER.info("DATA: " + data);
			return data;
		}
		return null;
	}

}
