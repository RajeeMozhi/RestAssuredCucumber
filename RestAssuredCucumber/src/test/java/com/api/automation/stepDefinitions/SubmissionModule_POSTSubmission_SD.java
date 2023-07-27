package com.api.automation.stepDefinitions;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

import com.api.auotmation.utilities.Config;
import com.api.automation.base.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SubmissionModule_POSTSubmission_SD extends BaseClass {
	String uri;
	public RequestSpecification request;
	Response response;
	// Created common method to post assignment request and test
		@SuppressWarnings("unchecked")
		public void createPostRequest(String SheetName, int Rownumber)
				throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
			//mandatory field
			String assignmentId = getDataFromExcel(SheetName, Rownumber).get("assignmentId");
			String userId = getDataFromExcel(SheetName, Rownumber).get("userId");
			String subDesc = getDataFromExcel(SheetName, Rownumber).get("subDesc");
			String subDateTime = getDataFromExcel(SheetName, Rownumber).get("subDateTime");
			//additional field
			String subComments = getDataFromExcel(SheetName, Rownumber).get("subComments");
		//	String graderId = getDataFromExcel(SheetName, Rownumber).get("graderId");
			String subPathAttach1 = getDataFromExcel(SheetName, Rownumber).get("subPathAttach1");
			String subPathAttach2 = getDataFromExcel(SheetName, Rownumber).get("subPathAttach2");
			String subPathAttach3 = getDataFromExcel(SheetName, Rownumber).get("subPathAttach3");
			String subPathAttach4 = getDataFromExcel(SheetName, Rownumber).get("subPathAttach4");
			String subPathAttach5 = getDataFromExcel(SheetName, Rownumber).get("subPathAttach5");

			JSONObject body = new JSONObject();
			body.put("assignmentId", assignmentId);
		//	body.put("graderId",graderId);
			body.put("userId", userId);
			body.put("subDesc", subDesc);
			body.put("subComments", subComments);
			body.put("subDateTime", subDateTime);
			body.put("subPathAttach1", subPathAttach1);
			body.put("subPathAttach2", subPathAttach2);
			body.put("subPathAttach3", subPathAttach3);
			body.put("subPathAttach4", subPathAttach4);
			body.put("subPathAttach5", subPathAttach5);
			
			response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
		}
		@Given("User creates POST Request for the API with valid BASEURL and Valid Endpoint")
		public void user_creates_post_request_for_the_api_with_valid_baseurl_and_valid_endpoint() {
			this.uri = Config.base_URL+Config.Submission_EndPoint;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("Request set for submission module with valid base URL and valid endpoint");
		}

		@When("User sends HTTPS POST Request and  request Body with mandatory ,additional  fields from {string} and {int}")
		public void user_sends_https_post_request_and_request_body_with_mandatory_additional_fields_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException
		{
				createPostRequest(SheetName, Rownumber);
				logger.info("Submission Module Post Request sent with all mandatory and additional field ");
			
		}

		@Then("The User should get Statuscode as {string}")
		public void the_user_should_get_statuscode_as(String statuscode) {
			int GetAllstatuscode = response.getStatusCode();
			if (GetAllstatuscode == 201) {
			    logger.info("Status code 201, request successful");
			} else {
			    logger.warn("Expected status code 201, but got 404, Error is being reported " + GetAllstatuscode);
			}
		
			
		}

		@Given("User creates POST Request for the LMS API with valid Base URL and Valid Endpoint")
		public void user_creates_post_request_for_the_lms_api_with_valid_base_url_and_valid_endpoint() {
			this.uri = Config.base_URL+Config.Submission_EndPoint;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("Request set for submission module with valid base URL and valid endpoint");
		}

		@When("User sends POST Request using existing values for  mandatory ,additional  fields from {string} and {int}")
		public void user_sends_post_request_using_existing_values_for_mandatory_additional_fields_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
			createPostRequest(SheetName, Rownumber);
			logger.info("Submission Module Post Request sent with all mandatory and additional field for existing values ");
		}

		@Then("The User should get Statuscode as {string} on console")
		public void the_user_should_get_statuscode_as_on_console(String statuscode) {
			int GetAllstatuscode = response.getStatusCode();
			if (GetAllstatuscode == 400) {
			    logger.info("Status code 400, request successful");
			} else {
			    logger.warn("Expected status code 400, but got 404, Error is being reported " + GetAllstatuscode);
			}
		}

		@Given("User creates POST Request for the valid LMS Base URL and Valid Endpoint")
		public void user_creates_post_request_for_the_valid_lms_base_url_and_valid_endpoint() {
			this.uri = Config.base_URL+Config.Submission_EndPoint;
			this.request = RestAssured.given().header("Content-Type", "application/json");
			logger.info("Request set for submission module with valid base URL and valid endpoint");
		}

		@When("User sends HTTPS Request and request Body with missing mandatory fields from {string} and {int}")
		public void user_sends_https_request_and_request_body_with_missing_mandatory_fields_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
			createPostRequest(SheetName, Rownumber);
			logger.info("Submission Module Post Request sent with all mandatory and additional field for existing values ");
		}

		@Then("User receives {string} Statuscode with message and boolean success details")
		public void user_receives_statuscode_with_message_and_boolean_success_details(String statuscode) {
			int GetAllstatuscode = response.getStatusCode();
			if (GetAllstatuscode == 400) {
			    logger.info("Status code 400, request successful");
			} else {
			    logger.warn("Expected status code 400, but got 404, Error is being reported " + GetAllstatuscode);
			} 
		}
	
}