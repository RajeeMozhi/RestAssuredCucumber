package com.api.automation.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.ExcelReader;
import com.api.automation.base.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SubmissionModule_DeleteSubmission_SD extends BaseClass {
	String uri;
	public RequestSpecification request;
	Response response;
	@Given("User sets request for Program module with Valid Base URL and Valid submission Id")
	public void user_sets_request_for_program_module_with_valid_base_url_and_valid_submission_id() {
		this.uri = Config.base_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("user sends Valid Base URL for submission Module");   
	}

	@When("User send DELETE request with Valid Submision ID from {string} and {int}")
	public void user_send_delete_request_with_valid_submision_id_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();
		List<Map<String,String>> getData = 
				excelReader.getData(Config.excelFilePath, SheetName); 
		
		String submissionID = getData.get(Rownumber).get("submissionID");
		
		response = this.request
				.when()
				.delete(Config.base_URL+Config.Submission_EndPoint+"/"+ submissionID)
				.then()
				.log().all().extract().response();
	}

	@Then("Request should be successfully displayed on console with status code {string} for DELETE SubmissionID")
	public void request_should_be_successfully_displayed_on_console_with_status_code_for_delete_submission_id(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(Integer.parseInt(statuscode));
		response.then().assertThat().header("Connection", "keep-alive");
		//Json Schema Validation
	//	response.then().assertThat()
	//	.body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/JsonSchemas/GetSingleProgram.json")));

		logger.info("DELETE Request to delete submissionID is successful");
	}
	
	else  {
		logger.info("DElete Request unsuccessful");
	    } 
		
	}
//@test2
	@Given("User sets request for Program module with Valid Base URL and InValid submission Id")
	public void user_sets_request_for_program_module_with_valid_base_url_and_in_valid_submission_id() {
		this.uri = Config.base_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("user sends Valid Base URL for submission Module");   
	}

	@When("User send DELETE request with InValid Submision ID from {string} and {int}")
	public void user_send_delete_request_with_in_valid_submision_id_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();
		List<Map<String,String>> getData = 
				excelReader.getData(Config.excelFilePath, SheetName); 
		
		String submissionID = getData.get(Rownumber).get("submissionID");
		
		response = this.request
				.when()
				.delete(Config.base_URL+Config.Submission_EndPoint+"/"+ submissionID)
				.then()
				.log().all().extract().response();
		
	}

	@Then("Request should be successfully displayed on with status code {string} for DELETE SubmissionID")
	public void request_should_be_successfully_displayed_on_with_status_code_for_delete_submission_id(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 404) {
		response.then().statusCode(Integer.parseInt(statuscode));
		response.then().assertThat().header("Connection", "keep-alive");
		//Json Schema Validation
	//	response.then().assertThat()
	//	.body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/JsonSchemas/GetSingleProgram.json")));

		logger.info("DELETE Request to delete submissionID is successful");
	}
	
	else  {
		logger.info("DELETE Request unsuccessful");
	    } 
	}
//		int GetAllstatuscode = response.getStatusCode();
//		if (GetAllstatuscode == 404) {
//		response.then().statusCode(GetAllstatuscode);
//		//Header Validation
//		response.then().assertThat().header("Connection", "keep-alive");
//		logger.info("put Request to fetch all batch data is successfull");
//	}
//	
//	else  {
//		logger.info("put Request unsuccessful");
//	   } 

}