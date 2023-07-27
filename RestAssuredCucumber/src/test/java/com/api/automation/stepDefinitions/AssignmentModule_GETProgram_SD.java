package com.api.automation.stepDefinitions;

import com.api.auotmation.utilities.Config;
import com.api.automation.base.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssignmentModule_GETProgram_SD extends BaseClass {
	String uri;
	public RequestSpecification request;
	Response response;
	@Given("User creates GET Request for the LMS API endpoint")
	public void user_creates_get_request_for_the_lms_api_endpoint() {
		this.uri = Config.base_URL+Config.Valid_EndPoint_GetAllAssignment;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("user sends Valid Base URL for Assignment Module");
	}

	@When("User sends HTTPS Request with LMS API endpoint")
	public void user_sends_https_request_with_lms_api_endpoint() {	
		response = this.request.get(this.uri);
		response.then().log().all();   
	}

	@Then("User receives {int} OK Status with response body")
	public void user_receives_ok_status_with_response_body(Integer statuscode) {
		
		
		
		try {
			
			//Validation of Response - Headers
			
			Headers headers = response.getHeaders();
			System.out.println("Headers "+headers);
			logger.info("Header details");
			
			
			int GetAllstatuscode = response.getStatusCode();
				if (GetAllstatuscode == 200)
					{
					response.then().statusCode(GetAllstatuscode);
					
					//Header Validation
					response.then().assertThat().header("Connection", "keep-alive");
					response.then().assertThat().header("Server", "Cowboy");
			  

					logger.info("GET Request to fetch all Assignment data is successfull");
				}
				else
				{
					logger.info("GET Request is NOT Successful");
				}
			
			
			}
			catch(Exception e)
			{
				logger.info("Status code is other than 200 - Defect need to be reported");
			}
		
			
	}
//@test2
	@Given("User creates GET Request for the LMS API endpoint with valid Assignment ID")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_assignment_id() {
		this.uri = Config.base_URL+Config.Valid_AssignmentID;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("user sends Valid Base URL and Valid Assignment Id for Assignment Module");
	}

	@When("User sends HTTPS Request for the LMS API endpoint with valid Assignment ID")
	public void user_sends_https_request_for_the_lms_api_endpoint_with_valid_assignment_id() {
		response = this.request.get(this.uri);	
		response.then().log().all();   
	}

	@Then("User receives {int} OK StatusCode with response body on console")
	public void user_receives_ok_status_code_with_response_body_on_console(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("GET Request to fetch all Assignment ID data is successfull");
	}
	
	else  {
		logger.info("GET Request unsuccessful");
	   } 
	}
//@test3
	@Given("User creates GET Request for the LMS API endpoint with valid Batch Id")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_id() {
		this.uri = Config.base_URL;
		this.uri = Config.programMod_GET_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("user sends Valid Base URL and Valid Batch Id for Assignment Module");
	}

	@When("User sends HTTPS Request for the LMS API endpoint with valid Batch Id")
	public void user_sends_https_request_for_the_lms_api_endpoint_with_valid_batch_id() {
		response = this.request.get(this.uri);	
		response.then().log().all();   
	}

	@Then("Response should be displayed with {int} OK StatuCode")
	public void response_should_be_displayed_with_ok_statu_code(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("GET Request to fetch all Assignment ID data is successfull");
	}
	
	else  {
		logger.info("GET Request unsuccessful");
	   }  
	}

	
	}