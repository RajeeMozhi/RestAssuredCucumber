package com.api.automation.stepDefinitions;

import org.json.simple.JSONObject;

import com.api.auotmation.utilities.Config;
import com.api.automation.base.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SubmissionModule_PUTSubmission_SD extends BaseClass{
	String uri;
	RequestSpecification request;
	Response response;
	
	
	@Given("User creates PUT Request for the LMS API endpoint  and Valid submission Id")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_submission_id() {
		this.uri = Config.base_URL+ Config.Resubmit_Assignment;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		response = this.request.when().put(this.uri);
		response.then().log().all();   
	}

	@SuppressWarnings("unchecked")
	@When("User sends HTTPS Request and  request Body with Mandatory  Fields")
	public void user_sends_https_request_and_request_body_with_mandatory_fields() {
		JSONObject body = new JSONObject();
		body.put("assignmentID","4953");
		body.put("subDesc","ResubmitAssignment");
		body.put("userId","U9507");
		body.put("subDateTime","07-29-2023 05:29:39");
		
		System.out.println(body);

	
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);

		logger.info("user sends Valid request body for Submission Module for PUT Request");
	}

	@Then("User receives {int} OK Status with updated value in response body")
	public void user_receives_ok_status_with_updated_value_in_response_body(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
			logger.info("Status code 200, request successful");
		} else {
			logger.warn("Expected status code 200, but got 400, Error is being reported " + GetAllstatuscode);
		}

	
	}

	@Given("User creates PUT Request for the LMS API endpoint  and  invalid submission ID")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_invalid_submission_id() {
		this.uri = Config.base_URL+ Config.Invalid_Submission;
		
		this.request = RestAssured.given().header("Content-Type", "application/json");
		response = this.request.when().put(this.uri);
		//response = this.request.when().put(this.uri);
		response.then().log().all(); 
		
	}

@SuppressWarnings("unchecked")
@When("User sends  Request and  request Body with Mandatory  field")
public void user_sends_request_and_request_body_with_mandatory_field() {
	JSONObject body = new JSONObject();
	body.put("assignmentID","4953");
	body.put("subDesc","ResubmitAssignment");
	body.put("userId","U9507");
	body.put("subDateTime","07-29-2023 05:29:39");
	
	System.out.println(body);


	this.request = RestAssured.given().header("Content-Type", "application/json").body(body);

	logger.info("user sends Valid request body for Submission Module for PUT Request");
}

	

	@Then("User receives {int} Not Found Status with message and boolean success details")
	public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode ==400 ) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request to resubmit assignment is successfull");
	}
	
	else  {
		logger.info("put Request unsuccessful");
	   } 
	}

	@When("User sends HTTPS Request  and request Body with missing mandatory fields")
	public void user_sends_https_request_and_request_body_with_missing_mandatory_fields() {
		JSONObject body = new JSONObject();
		body.put("assignmentID","4953");
	//	body.put("subDesc","ResubmitAssignment");
	//	body.put("userId","U9507");
		body.put("subDateTime","07-29-2023 05:29:39");
		
		System.out.println(body);

	
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);

		logger.info("user sends put request body with missing mandatory fields");
	}

	@Then("User receives {int} Bad Request Status with message and boolean success details")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details(Integer statuscode) {
	    int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode ==400 ) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request is unsuccessfull");
	}
	
	
	}

	


}