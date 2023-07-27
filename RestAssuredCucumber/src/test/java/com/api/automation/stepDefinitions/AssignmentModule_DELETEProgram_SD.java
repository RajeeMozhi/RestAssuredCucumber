package com.api.automation.stepDefinitions;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;

import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.TestUtil;
import com.api.automation.base.BaseClass;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssignmentModule_DELETEProgram_SD extends BaseClass{
	String uri;
	public RequestSpecification request;
	Response response;
	String basePathDelByAssignmentId = "/assignments";
	String assignmentId;
	String jsonAsString;
	String expectedMessage = null;
	
	@Given("User creates DELETE Request for the LMS API Endpoint  and  valid Assignment ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_assignment_id() {
		response = TestUtil.createAndSendPostRequestForAssignment();
		setDeleteAssignmentId();
	     
	}

	@When("User sends HTTPS Request to delete valid Assignment ID from {string} and {int}")
	public void user_sends_https_request_to_delete_valid_assignment_id_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		String assignmentId = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, SheetName, Rownumber).get("assignmentId");
		
		if (response != null) {
			if (response.getStatusCode() == 201) {
				assignmentId = response.getBody().jsonPath().get("assignmentId").toString();
			}
		}
		System.out.println(" assignmentId created to delete is: "+assignmentId);
		sendDeleteAssignmentId(assignmentId);
		this.assignmentId = assignmentId;
	    
	}

	@Then("Request should be successfull with status code {string} the message {string}")
	public void request_should_be_successfull_with_status_code_the_message(String statusCode, String message) {
		statusCode200Validation(statusCode, 
				"Delete Request to delete single program by id is successful", 
				"Delete program by id request unsuccessful");	
		jsonAsString = response.asString();
		
		System.out.println("Message === "+message);
		System.out.println("jsonAsString === "+jsonAsString);
		
		Assert.assertEquals(true, jsonAsString.contains(message));
		logger.info("Delete Request to delete assignment by id is successful");  
		
	}

	@Given("User creates DELETE Request for the LMS API Endpoint  and  invalid Assignment ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_assignment_id() {
		setDeleteAssignmentId();
	}

	@When("User sends HTTPS Request to delete invalid Assignment ID from {string} and {int}")
	public void user_sends_https_request_to_delete_invalid_assignment_id_from_and(String sheetName, Integer rowNumber) throws Exception {
		Map<String, String> rowData = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, sheetName, rowNumber);
		String assignmentId = rowData.get("assignmentId");
		expectedMessage = rowData.get("message");
		sendDeleteAssignmentId(assignmentId);

	}

	@Then("Assigment Bad request error message should be displayed with status code {string}")
	public void assigment_bad_request_error_message_should_be_displayed_with_status_code(String statuscode) {
		response.then().statusCode(Integer.parseInt(statuscode));
		String message = response.getBody().jsonPath().get("message");
		message = message.trim();
		System.out.println("message = "+message);
		System.out.println("Expected Message = "+expectedMessage);
		Assert.assertEquals(true, expectedMessage.contains(message));

	}

	@Given("User creates DELETE Request for the empty assignment Id to delete assignment Endpoint")
	public void user_creates_delete_request_for_the_empty_assignment_id() {
		setDeleteAssignmentId();
	}

	@When("User sends HTTPS Request to delete a submission with empty assignment Id")
	public void user_sends_https_request() throws InvalidFormatException, IOException {
		
		sendDeleteAssignmentId("");
		
	}
	@Then("The User must receieve error message and with status code {string}")
	public void the_user_must_receieve_error_message_and_with_status_code(String statusCode) {
		response.then().statusCode(Integer.parseInt(statusCode));
		String actualMessage = response.getBody().jsonPath().get("error");
		System.out.println("actualMessage = "+actualMessage);
		String expectedErrorMessage="Method Not Allowed";
		//response.then().statusCode(Integer.parseInt(statuscode));
		assertEquals(expectedErrorMessage, actualMessage);
	}
	
	public void sendDeleteAssignmentId(String assignmentId) throws InvalidFormatException, IOException {
		response = this.request
				.when()
				.delete(this.uri + "/" + assignmentId)
				.then()
				.log().all().extract().response();
	}
	
	public void setDeleteAssignmentId() {
		this.uri = Config.base_URL + this.basePathDelByAssignmentId; 
		this.request = RestAssured.given().header("Content-Type", "application/json");
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
}
