package com.api.automation.stepDefinitions;

import java.io.File;

import org.json.simple.JSONObject;

import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.CurrentDate;
import com.api.automation.base.BaseClass;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BatchModule_PUTBatch_SD extends BaseClass
{
String uri;
CurrentDate cd;
RequestSpecification request;
Response response;
String batchId="6497";
String programId ="11465";
//<======================Scenario: Verify put request for batch by providing invalid URL=======>
	@SuppressWarnings("unchecked")
	@Given("User write request with Valid Request Body for Batch")
	public void user_write_request_with_valid_request_body_for_batch() {
	JSONObject body = new JSONObject();
		body.put("batchId", "71");
		body.put("batchName", "July23-AssuredNinjas-SDET-SDET116-003");
		body.put("batchDescription","Selenium");
		body.put("batchStatus", "Inactive");
		body.put("batchNoOfClasses", 40);
		body.put("programId","11465");
		body.put("programName","batchUpdated");
		System.out.println(body);

	
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	//	this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("user sends Valid request body for batch Module");
	    
	}

	@When("User send put request with Invalid URL")
	public void user_send_put_request_with_invalid_url() {
		this.uri = Config.BASE_Invalid_URL+ Config.Invalid_BatchPut;
		response = this.request.when().put(this.uri);
		response.then().log().all(); 
	}

	@Then("Server unavailable error message should be displayed with {int} status code")
	public void server_unavailable_error_message_should_be_displayed_with_status_code(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 503) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request to fetch all batch data is successfull");
	}
	
	else  {
		logger.info("put Request unsuccessful");
	   } 
}
	

	@SuppressWarnings("unchecked")
	@Given("User write request with valid Payload")
	public void user_write_request_with_valid_payload() {
		JSONObject body = new JSONObject();
		body.put("batchId", "71");
		body.put("batchName", "July23-AssuredNinjas-SDET-SDET116-003");
		body.put("batchDescription","Selenium");
		body.put("batchStatus", "Inactive");
		body.put("batchNoOfClasses", 40);
		body.put("programId","11465");
		body.put("programName","batchUpdated");
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	
		logger.info("user sends Valid payload body for batch Module");
	    
	   
	}

	@When("User send put request with Invalid Endpoint for Batch")
	public void user_send_put_request_with_invalid_endpoint_for_batch() {
		this.uri = Config.base_URL+ Config.Invalid_Batch;
		response = this.request.when().put(this.uri);
		response.then().log().all(); 
	}

	@Then("Not found validation error message should be displayed with {int} status code")
	public void not_found_validation_error_message_should_be_displayed_with_status_code(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 404) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request to fetch all batch data is successfull");
	}
	
	else  {
		logger.info("put Request unsuccessful");
	   } 
	}

	@Given("User set put request with empty request body")
	public void user_set_put_request_with_empty_request_body() {
		JSONObject body = new JSONObject();
		body.put("batchName", "");
		body.put("batchDescription", "");
		body.put("batchStatus", "");
		body.put("batchNoOfClasses", null);
		body.put("programId", null);

		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	}

	@When("User send put request with Valid URL")
	public void user_send_put_request_with_valid_url() {
		this.uri = Config.base_URL+ Config.Valid_BatchPut;
		response = this.request.when().put(this.uri);
		response.then().log().all(); 
	}

	@Then("Bad request error message should be displayed with {int} status code for batch")
	public void bad_request_error_message_should_be_displayed_with_status_code_for_batch(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 400) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request with empty payload for batch successfull");
	}
	
	else  {
		logger.info("put Request with empty payload unsuccessful");
	   }   
	}

	@SuppressWarnings("unchecked")
	@Given("User set put request with valid Payload for Existing data")
	public void user_set_put_request_with_valid_payload_for_existing_data() {
		JSONObject body = new JSONObject();
		body.put("batchId", "71");
		body.put("batchName", "July23-AssuredNinjas-SDET-SDET116-001");
		body.put("batchDescription","Selenium");
		body.put("batchStatus", "Active");
		body.put("batchNoOfClasses",10);
		body.put("programId","11465");
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	}

	@When("User send put request for batch")
	public void user_send_put_request_for_batch() {
		this.uri = Config.base_URL+ Config.Valid_BatchPut6495;
		response = this.request.when().put(this.uri);
		response.then().log().all(); 
	}

	@Then("Request should be successfull with status code {int} for batch")
	public void request_should_be_successfull_with_status_code_for_batch(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request with empty payload for batch successfull");
	}
	
	else  {
		logger.info("put Request with empty payload unsuccessful");
	   }   
	}

	@SuppressWarnings("unchecked")
	@Given("User set put request with invalid Payload")
	public void user_set_put_request_with_invalid_payload() {
		JSONObject body = new JSONObject();
	
		body.put("batchName", "July23-AssuredNinjas-SDET-SDET116-002");
		body.put("batchDescription","ApiTesting");
		body.put("batchStatus", "Active");
		body.put("batchNoOfClasses",5);
		body.put("programId","123");
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	    
		
	}

	@When("User send request with Valid batch URL")
	public void user_send_request_with_valid_batch_url() {
		this.uri = Config.base_URL+ Config.Valid_BatchPut;
		response = this.request.when().put(this.uri);
		response.then().log().all(); 
	}
	@Then("batch with id not found error message should be displayed with {int} status code")
	public void batch_with_id_not_found_error_message_should_be_displayed_with_status_code(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 404) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request with empty payload for batch successfull");
	}
	
	else  {
		logger.info("put Request with empty payload unsuccessful");
	   }     
	}
	
//@testF
	@SuppressWarnings("unchecked")
	@Given("User set put request without batch description")
	public void user_set_put_request_without_batch_description() {
		JSONObject body = new JSONObject();
		
		body.put("batchName", "July23-AssuredNinjas-SDET-SDET116-002");
		body.put("batchDescription","");
		body.put("batchStatus", "Active");
		body.put("batchNoOfClasses",5);
		body.put("programId","11465");
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	    
	}

	@When("User requested put, with Valid URL")
	public void user_requested_put_with_valid_url() {
		this.uri = Config.base_URL+"/"+"batches/6497";
		response = this.request.when().put(this.uri);
		response.then().log().all(); 
	}

	@Then("Batch should be created with null value for program description with status code {int}")
	public void batch_should_be_created_with_null_value_for_program_description_with_status_code(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request with empty payload for batch successfull");
	}
	
	else  {
		logger.info("put Request with empty payload unsuccessful");
	   }     
	}


	
//@testJ
	@Given("User set put request with valid payload except batch no of classes")
	public void user_set_put_request_with_valid_payload_except_batch_no_of_classes() {
		
		JSONObject body = new JSONObject();
		body.put("batchName", "July23-AssuredNinjas-SDET-SDET116-002");
		body.put("batchDescription","ApiTesting");
		body.put("batchStatus", "Active");
		body.put("batchNoOfClasses",null);
		body.put("programId","123");
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
	    
	}

	@When("User send put request to check without no of classes")
	public void user_send_put_request_to_check_without_no_of_classes() {
		this.uri = Config.base_URL+"/"+"batches/6497";
		response = this.request.when().put(this.uri);
		response.then().log().all();  
	}

	@Then("Validation error  No of Classes is needed; It should be a positive number  should be displayed with {int} bad request")
	public void validation_error_no_of_classes_is_needed_it_should_be_a_positive_number_should_be_displayed_with_bad_request(Integer statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 400) {
		response.then().statusCode(GetAllstatuscode);
		//Header Validation
		response.then().assertThat().header("Connection", "keep-alive");
		logger.info("put Request with empty payload for batch successfull");
	}
	
	else  {
		logger.info("put Request with empty payload unsuccessful");
	   }     
	}

}