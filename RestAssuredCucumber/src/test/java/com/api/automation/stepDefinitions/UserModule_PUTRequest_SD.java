package com.api.automation.stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.junit.Assert;

import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.CurrentDate;
import com.api.automation.base.BaseClass;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserModule_PUTRequest_SD extends BaseClass {

	public RequestSpecification request;
	public Response response;
	public String uri;
	public static CurrentDate cd;
	public static String userId;
	//public static int statusCode;

	// Scenario 1
	@Given("User sets valid URL with mandatory Payload for user role creation")
	public void user_sets_valid_url_with_mandatory_payload_for_user_role_creation() {
		this.uri = Config.userMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("POST request for User module is sent with all fields  for role Admin");
	}

	@When("User sends POST request with valid URL for user role from {string} and {int}")
	public void user_sends_post_request_with_valid_url_for_user_role_from_and(String SheetName, Integer RowNumber)
			throws InvalidFormatException, IOException {
		createPOSTRequest_UserModule_allFields(SheetName, RowNumber);

		logger.info("User send POST request with valid URL for Role");
	}

	@Then("Request should be successful with status code 201 and creation of user Role")
	public void successful_user_role_creation() {

		int statusCode = this.response.getStatusCode();
		//statusCode = this.response.getStatusCode();
		System.out.println("Post Status code " + statusCode);

	}

	// Created common method to post request and test each case
	@SuppressWarnings("unchecked")
	public void createPOSTRequest_UserModule_allFields(String SheetName, int Rownumber)
			throws InvalidFormatException, IOException {
		String userComments = getDataFromExcel(SheetName, Rownumber).get("userComments");
		String userEduPg = getDataFromExcel(SheetName, Rownumber).get("userEduPg");
		String userEduUg = getDataFromExcel(SheetName, Rownumber).get("userEduUg");
		String userFirstName = getDataFromExcel(SheetName, Rownumber).get("userFirstName");
		String userLastName = getDataFromExcel(SheetName, Rownumber).get("userLastName");
		String userLinkedinUrl = getDataFromExcel(SheetName, Rownumber).get("userLinkedinUrl");
		String userLocation = getDataFromExcel(SheetName, Rownumber).get("userLocation");
		String userMiddleName = getDataFromExcel(SheetName, Rownumber).get("userMiddleName");
		String userPhoneNumber = getDataFromExcel(SheetName, Rownumber).get("userPhoneNumber");

		String roleId = getDataFromExcel(SheetName, Rownumber).get("roleId");
		String userRoleStatus = getDataFromExcel(SheetName, Rownumber).get("userRoleStatus");

		String userTimeZone = getDataFromExcel(SheetName, Rownumber).get("userTimeZone");
		String userVisaStatus = getDataFromExcel(SheetName, Rownumber).get("userVisaStatus");

		JSONObject body = new JSONObject();
		body.put("userComments", userComments);
		body.put("userEduPg", userEduPg);
		body.put("userEduUg", userEduUg);
		body.put("userFirstName", userFirstName);
		body.put("userLastName", userLastName);
		body.put("userLinkedinUrl", userLinkedinUrl);
		body.put("userLocation", userLocation);
		body.put("userMiddleName", userMiddleName);
		body.put("userPhoneNumber", userPhoneNumber);

		HashMap<String, String> userRoleMaps = new HashMap<>();
		userRoleMaps.put("roleId", roleId);
		userRoleMaps.put("userRoleStatus", userRoleStatus);

		ArrayList<HashMap<String, String>> jsonArray = new ArrayList<>();
		jsonArray.add(userRoleMaps);

		body.put("userRoleMaps", jsonArray);

		body.put("userTimeZone", userTimeZone);
		body.put("userVisaStatus", userVisaStatus);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
		response.prettyPrint();

		JsonPath js = response.jsonPath();
		userId = js.getString("userId");
		System.out.println("User id   =  " + userId);
		js.prettyPrint();

	}

	// Scenario 2

	@Given("User sets valid URL with mandatory Payload for user to update a user")
	public void mandatoryPayLoad() {
	
		System.out.println("User id" + userId);
		
		//this.uri = Config.userMod_PUT_URL + Config.userMod_PUT_byUserId_URL + userId;
		System.out.println(uri);
		this.request = RestAssured.given().header("Content-Type", "application/json");

	}

	@When("User sends HTTPS PUT Request and  request Body with mandatory fields from {string} and {int}")
	public void user_sends_https_put_request_and_request_body_with_mandatory_fields_from_and(String SheetName,
			Integer Rownumber) throws InvalidFormatException, IOException {
		{
			this.uri = Config.userMod_PUT_URL + Config.userMod_PUT_byUserId_URL + userId;
			
			String roleId = getDataFromExcel(SheetName, Rownumber).get("roleId");
			String userRoleStatus = getDataFromExcel(SheetName, Rownumber).get("userRoleStatus");
			String userComments = getDataFromExcel(SheetName, Rownumber).get("userComments");
			String userEduPg = getDataFromExcel(SheetName, Rownumber).get("userEduPg");
			String userEduUg = getDataFromExcel(SheetName, Rownumber).get("userEduUg");
			String userFirstName = getDataFromExcel(SheetName, Rownumber).get("userFirstName");
			String userLastName = getDataFromExcel(SheetName, Rownumber).get("userLastName");
			String userLinkedinUrl = getDataFromExcel(SheetName, Rownumber).get("userLinkedinUrl");
			String userLocation = getDataFromExcel(SheetName, Rownumber).get("userLocation");
			String userMiddleName = getDataFromExcel(SheetName, Rownumber).get("userMiddleName");
			String userPhoneNumber = getDataFromExcel(SheetName, Rownumber).get("userPhoneNumber");
			String userTimeZone = getDataFromExcel(SheetName, Rownumber).get("userTimeZone");
			String userVisaStatus = getDataFromExcel(SheetName, Rownumber).get("userVisaStatus");

			JSONObject body = new JSONObject();
			body.put("userId", userId);
			//body.put("roleId", roleId);
//			body.put("userRoleStatus", userRoleStatus);

			body.put("userComments", userComments);
			body.put("userEduPg", userEduPg);
			body.put("userEduUg", userEduUg);
			body.put("userFirstName", userFirstName);
			System.out.println(userFirstName);
			body.put("userLastName", userLastName);
			body.put("userLinkedinUrl", userLinkedinUrl);
			body.put("userLocation", userLocation);
			body.put("userMiddleName", userMiddleName);
			body.put("userPhoneNumber", userPhoneNumber);

//			HashMap<String, String> userRoleMaps = new HashMap<>();
//			userRoleMaps.put("roleId", roleId);
//			userRoleMaps.put("userRoleStatus", userRoleStatus);
//
//			ArrayList<HashMap<String, String>> jsonArray = new ArrayList<>();
//			jsonArray.add(userRoleMaps);

			//body.put("userRoleMaps", jsonArray);

			body.put("userTimeZone", userTimeZone);
			body.put("userVisaStatus", userVisaStatus);

			System.out.println(body);

			logger.info("User send POST request with valid URL for Role");

			response = this.request.when().put(this.uri);
			logger.info("User sends PUT request with valid URL");
			
			response.prettyPrint();

		}
	}



	@Then("Request should be successful with status code <{int}> and updation of user")
	public void request_should_be_successful_with_status_code_and_updation_of_user(Integer int1) {
		int statusCode = this.response.getStatusCode();
		System.out.println("Status code :"+statusCode);
		Assert.assertEquals(200, statusCode);
		
		
		
		
		/*statusCode = this.response.getStatusCode();
		//Assert.assertEquals(200, statusCode);
		Assert.assertEquals
		/*int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(GetAllstatuscode);
			
		System.out.println("Status Code " +GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");*/
		logger.info("put Request is successful");
		
		
		response.prettyPrint();
		//logger.info("Request is successfull with status code  " + GetAllstatuscode);
	

	}
	
	//Scenario 3---------------------------
	
	@When("User sends HTTPS PUT Request with invalid  user id and  request Body with mandatory fields from {string} and {int}")
	public void user_sends_https_put_request_with_invalid_user_id_and_request_body_with_mandatory_fields_from_and(String SheetName, Integer Rownumber) 
			throws InvalidFormatException, IOException {
		
		this.uri = Config.userMod_PUT_URL + Config.userMod_PUT_byUserId_invalid_URL;
		
		String roleId = getDataFromExcel(SheetName, Rownumber).get("roleId");
		String userRoleStatus = getDataFromExcel(SheetName, Rownumber).get("userRoleStatus");
		String userComments = getDataFromExcel(SheetName, Rownumber).get("userComments");
		String userEduPg = getDataFromExcel(SheetName, Rownumber).get("userEduPg");
		String userEduUg = getDataFromExcel(SheetName, Rownumber).get("userEduUg");
		String userFirstName = getDataFromExcel(SheetName, Rownumber).get("userFirstName");
		String userLastName = getDataFromExcel(SheetName, Rownumber).get("userLastName");
		String userLinkedinUrl = getDataFromExcel(SheetName, Rownumber).get("userLinkedinUrl");
		String userLocation = getDataFromExcel(SheetName, Rownumber).get("userLocation");
		String userMiddleName = getDataFromExcel(SheetName, Rownumber).get("userMiddleName");
		String userPhoneNumber = getDataFromExcel(SheetName, Rownumber).get("userPhoneNumber");
		String userTimeZone = getDataFromExcel(SheetName, Rownumber).get("userTimeZone");
		String userVisaStatus = getDataFromExcel(SheetName, Rownumber).get("userVisaStatus");

		JSONObject body = new JSONObject();
		body.put("userId", "U907878787878");
		body.put("roleId", roleId);
        body.put("userRoleStatus", userRoleStatus);

		body.put("userComments", userComments);
		body.put("userEduPg", userEduPg);
		body.put("userEduUg", userEduUg);
		body.put("userFirstName", userFirstName);
		System.out.println(userFirstName);
		body.put("userLastName", userLastName);
		body.put("userLinkedinUrl", userLinkedinUrl);
		body.put("userLocation", userLocation);
		body.put("userMiddleName", userMiddleName);
		body.put("userPhoneNumber", userPhoneNumber);

		HashMap<String, String> userRoleMaps = new HashMap<>();
		userRoleMaps.put("roleId", roleId);
		userRoleMaps.put("userRoleStatus", userRoleStatus);

		ArrayList<HashMap<String, String>> jsonArray = new ArrayList<>();
		jsonArray.add(userRoleMaps);

		body.put("userRoleMaps", jsonArray);

		body.put("userTimeZone", userTimeZone);
		body.put("userVisaStatus", userVisaStatus);

		System.out.println(body);

		logger.info("User send POST request with valid URL for Role");

		response = this.request.when().put(this.uri);
		logger.info("User sends PUT request with valid URL");
		
		response.prettyPrint();

	}


	

	@Then("Request should get error message with status code <{int}> Not Found")
	public void request_should_get_error_message_with_status_code_not_found(Integer int1) {
	  
		
		int statusCode = this.response.getStatusCode();
		System.out.println("Status code :"+statusCode);
		//Assert.assertEquals(200, statusCode);
		if(statusCode == 404)
				logger.info("Status code is 404, Bad Request");
		else
			logger.warn("Expected status code is 404, but got " + statusCode);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
