package com.api.automation.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;

import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.ExcelReader;
import com.api.auotmation.utilities.TestUtil;
import com.api.automation.base.BaseClass;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramModule_DELETEProgram_SD extends BaseClass {
	
	String uri;
	public RequestSpecification request;
	Response response;
	String basePathDelByProgramId = "/deletebyprogid";
	String basePathDelByProgramName = "/deletebyprogname";
	String programId;
	String programName;
	String jsonAsString;
	String expectedMessage = null;
	
	@Given("User creates DELETE Request for the LMS API endpoint  and  valid program ID")
	public void User_creates_DELETE_Request_for_the_LMS_API_endpoint_and_valid_program_ID() {
		response = TestUtil.createAndSendPostRequestForProgram();
		setDeleteProgramId();
	    
	}

	@When("User sends HTTPS Request to delete valid program ID from {string} and {int}")
	public void User_sends_HTTPS_Request_to_delete_valid_program_ID(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		String programId = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, SheetName, Rownumber).get("programId");
		
		if (response != null) {
			if (response.getStatusCode() == 201) {
				programId = response.getBody().jsonPath().get("programId").toString();
			}
		}
		System.out.println(" programId created for delete is: "+programId);
		sendDeleteProgramId(programId);
		this.programId = programId;
	    
	}

	@Then("Request should be successfull with status code {string} and get the message {string}")	
	public void request_should_be_successfull_with_status_code_and_get_the_message(String statusCode, String message) {
		statusCode200Validation(statusCode, 
				"Delete Request to delete single program by id is successful", 
				"Delete program by id request unsuccessful");	
		jsonAsString = response.asString();
		
		System.out.println("Message === "+message);
		System.out.println("jsonAsString === "+jsonAsString);
		
		message = message.replace("{programId}", programId);
		System.out.println("Modified message === "+message);
		Assert.assertEquals(true, jsonAsString.contains(message));
		logger.info("Delete Request to delete program by id is successful");  
		
	}


	public void setDeleteProgramId() {
		this.uri = Config.base_URL + this.basePathDelByProgramId; 
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}
	public void setDeleteProgramName() {
		this.uri = Config.base_URL + this.basePathDelByProgramName; 
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	
	public void sendDeleteProgramId(String programId) throws InvalidFormatException, IOException {
		response = this.request
				.when()
				.delete(this.uri + "/" + programId)
				.then()
				.log().all().extract().response();
	}
	
	public void sendDeleteProgramName(String programName) throws InvalidFormatException, IOException {
		response = this.request
				.when()
				.delete(this.uri + "/" + programName)
				.then()
				.log().all().extract().response();
	}
	public void statusCode200Validation (String statusCode, String logSuccess, String logFail) {
		int statusCd = response.getStatusCode();
		if (statusCd == 200) {
			response.then().statusCode(Integer.parseInt(statusCode));
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info(logSuccess);
		}
		else {
			logger.info(logFail);
		}
	}
	

	@When("User sets GET request with programID from {string} and {int}")
	public void user_sets_get_request_with_program_id_from_and(String SheetName, Integer Rownumber) {
		this.uri = Config.base_URL + "/Programs"; 
		this.request = RestAssured.given().header("Content-Type", "application/json");
		
		String programId = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, SheetName, Rownumber).get("programId");
		response = this.request
				.when()
				.get(this.uri + "/" + programId)
				.then()
				.log().all().extract().response();
	}

	@Then("Program Bad request error message should be displayed with status code {string} for GET single program for delete")
	public void program_bad_request_error_message_should_be_displayed_with_status_code_for_get_single_program_for_delete(String statuscode) {
		int GetPrgstatuscode = response.getStatusCode();
		if (GetPrgstatuscode == 400) {
		response.then().statusCode(Integer.parseInt(statuscode));
		logger.info("Status code 400 received for GET single program with invalid program ID");
	}
	else 
		logger.info("Get Request unsuccessful");
	}
	


	@Given("User creats request for Program module with invalid programID")
	public void user_creats_request_for_Program_module_with_invalid_programID(){
		setDeleteProgramId();
	}

	@When("User sends DELETE request with invalid programId from {string} and {int}")
	public void user_sends_delete_request_with_invalid_program_id_from(String sheetName, Integer rowNumber) throws Exception {
		Map<String, String> rowData = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, sheetName, rowNumber);
		String temProgramId = rowData.get("programId");
		expectedMessage = rowData.get("message");
		sendDeleteProgramId(temProgramId);
	}

	@Then("Program Bad request error message should be displayed with status code {string}")
	public void program_bad_request_error_message_should_be_displayed_with_status_code(String statuscode) {
		response.then().statusCode(Integer.parseInt(statuscode));
		String message = response.getBody().jsonPath().get("message");
		message = message.trim();
		System.out.println("message = "+message);
		System.out.println("Expected Message = "+expectedMessage);
		Assert.assertEquals(true, expectedMessage.contains(message));

	}

	@Given("User creates request to delete Program module with valid programName")
	public void user_creates_request_to_delete_Program_module_with_valid_programName() {
		response = TestUtil.createAndSendPostRequestForProgram();
		setDeleteProgramName();
	     
	}

	@When("User sets DELETE request with programName from {string} and {int}")
	public void user_sets_delete_request_with_program_name_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		String programName = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, SheetName, Rownumber).get("programName");
		
		if (response != null) {
			if (response.getStatusCode() == 201) {
				programName = response.getBody().jsonPath().get("programName").toString();
			}
		}
		System.out.println(" programName created for delete is: "+programName);
		sendDeleteProgramName(programName);
		this.programName = programName;
	    
	}

	@Then("Success message with status code {string} and receive message {string}")
	public void success_message_with_status_code_and_receive_message(String statusCode, String message) {
		statusCode200Validation(statusCode, 
				"Delete Request to delete single program by name is successful", 
				"Delete program by name request unsuccessful");	
		jsonAsString = response.asString();
		
		System.out.println("Message === "+message);
		System.out.println("jsonAsString === "+jsonAsString);
		
		message = message.replace("{programName}", programName);
		System.out.println("Modified message === "+message);
		Assert.assertEquals(true, jsonAsString.contains(message));
		logger.info("Delete Request to delete program by name is successful");  
		
	}
	
	@Given("User creates request for Program module with invalid programName")
	public void user_creates_request_for_program_module_with_invalid_program_name() {
		setDeleteProgramName();
	}

	@When("User sets DELETE request with invalid programName from {string} and {int}")
	public void user_sets_delete_request_with_invalid_program_name_from_and(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		Map<String, String> rowData = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, sheetName, rowNumber);
		String temProgramName = rowData.get("programName");
		expectedMessage = rowData.get("message");
		sendDeleteProgramName(temProgramName);
	}

	@Then("Bad request error message should display with status code {string}")
	public void bad_request_error_message_should_display_with_status_code(String statuscode) {
		response.then().statusCode(Integer.parseInt(statuscode));
		String message = response.getBody().jsonPath().get("message");
		message = message.trim();
		System.out.println("message = "+message);
		System.out.println("Expected Message = "+expectedMessage);
		Assert.assertEquals(true, expectedMessage.contains(message));

	}
	
	
	@Given("User creates request for Program module with blank programID")
	public void user_creates_request_for_program_module_with_blank_program_id() {
		setDeleteProgramId();
	}

	@When("User sends DELETE request with blank programID")
	public void user_sends_delete_request_with_blank_program_id() throws InvalidFormatException, IOException {
		sendDeleteProgramId("");
	}

	@Then("Program Not found error message should be displayed with status code {string}")
	public void program_not_found_error_message_should_be_display_with_status_code(String statuscode) {
		String message = response.getBody().jsonPath().get("error");
		System.out.println("message = "+message);
		response.then().statusCode(Integer.parseInt(statuscode));

	}
	
	@Given("User sends request for Program module with blank programName")
	public void user_sends_request_for_program_module_with_blank_program_name() {
		setDeleteProgramName();
	}

	@When("User sets DELETE request with blank programName")
	public void user_sets_delete_request_with_blank_program_name() throws InvalidFormatException, IOException {
		sendDeleteProgramName("");
	}

	@Then("Program Not found error message should display on console with status code {string}")
	public void program_not_found_error_message_should_display_on_console_with_status_code(String statuscode) {
		String message = response.getBody().jsonPath().get("error");
		System.out.println("message = "+message);
		response.then().statusCode(Integer.parseInt(statuscode));

	}
	

}