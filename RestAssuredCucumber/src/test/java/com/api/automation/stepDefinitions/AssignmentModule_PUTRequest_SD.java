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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssignmentModule_PUTRequest_SD extends BaseClass {
	
	public String Assignment_uri;
	public RequestSpecification Assignment_request;
	public Response assignment_response;
	
	@Given("User sets request for Assignment module with valid endpoint and mandatory fields")
	public void user_sets_request_for_assignment_module_with_valid_endpoint_and_mandatory_fields() {
		this.Assignment_uri = Config.assignment_PUT_URL;
		this.Assignment_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Assignment module with valid base URL and valid data");
	}

	@When("User send PUT request with data for Assignment module from {string} and {int}")
	public void user_send_put_request_with_data_for_assignment_module_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		createPutRequest(SheetName, Rownumber);
		logger.info("Post Request sent with valid request body");
	}

	

	@Then("User is not successful creating assignment with status code {int}")
	public void user_unsuccessful_creating_assignment_with_status_code(Integer statusCode) {
		if (this.assignment_response == null) {
	        logger.error("response is null. Please ensure the Put request is executed correctly.");
	        return;
	    }
	
	int Poststatuscode = this.assignment_response.getStatusCode();
	 if (Poststatuscode == 404) {
		this.assignment_response.then().statusCode(statusCode);
		logger.info("Put Request UnSuccessful");
	}
	else if (Poststatuscode == 400) {
		this.assignment_response.then().statusCode(statusCode);
		logger.info("Bad Request");
	}
	}
	
	@Then("User successful created assignment with status code {int}")
	public void user_successful_created_assignment_with_status_code(Integer statusCode) {
		if (this.assignment_response == null) {
	        logger.error("response is null. Please ensure the Put request is executed correctly.");
	        return;
	    }
	
	int Poststatuscode = this.assignment_response.getStatusCode();
	if (Poststatuscode == 200) {
		this.assignment_response.then().statusCode(statusCode);
		logger.info("Put Request Successful");

	}
	}

	@Given("User sets request for Assignment module with invalid endpoint")
	public void user_sets_request_for_assignment_module_with_invalid_endpoint() {
		this.Assignment_uri = Config.assignment_PUT_Invalid_URL;
		this.Assignment_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Assignment module with Invalid base URL and valid data");
	    
	}

	@Given("User sets request for Assignment module with valid endpoint")
	public void user_sets_request_for_assignment_module_with_valid_endpoint() {
		this.Assignment_uri = Config.assignment_PUT_URL;
		this.Assignment_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Assignment module with valid base URL and missing data");
	}

private void createPutRequest(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
	
	
	String  assignmentDescription = getDataFromExcel(SheetName, Rownumber).get("assignmentDescription");
	String assignmentId = getDataFromExcel(SheetName, Rownumber).get("assignmentId");
	  String assignmentName = getDataFromExcel(SheetName, Rownumber).get("assignmentName") ;
	   String  batchId = getDataFromExcel(SheetName, Rownumber).get("batchId");
	   String comments =getDataFromExcel(SheetName, Rownumber).get("comments");
	   String createdBy = getDataFromExcel(SheetName, Rownumber).get("createdBy");
	 String   dueDate = getDataFromExcel(SheetName, Rownumber).get("dueDate");
	   String graderId = getDataFromExcel(SheetName, Rownumber).get("graderId");
	   String pathAttachment1 =getDataFromExcel(SheetName, Rownumber).get("pathAttachment1") ;
	   String pathAttachment2 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment2");
	   String pathAttachment3 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment3");
	   String  pathAttachment4 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment4");
	  String  pathAttachment5 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment5");
	  JSONObject assignment_body = new JSONObject();
	  assignment_body.put("pathAttachment5", pathAttachment5);
	  assignment_body.put("pathAttachment4", pathAttachment4);
	  assignment_body.put("pathAttachment3", pathAttachment3);
	  assignment_body.put("pathAttachment2", pathAttachment2);
	  assignment_body.put("pathAttachment1", pathAttachment1);
	  assignment_body.put("graderId", graderId);
	  assignment_body.put("dueDate", dueDate);
	  assignment_body.put("createdBy", createdBy);
	  assignment_body.put("comments", comments);
	  assignment_body.put("batchId", batchId);
	  assignment_body.put("assignmentName", assignmentName);
	  assignment_body.put("assignmentId", assignmentId);
	  assignment_body.put("assignmentDescription", assignmentDescription);
	  assignment_response = this.Assignment_request.body(assignment_body.toJSONString()).when().put(this.Assignment_uri).then().log().all().
			  extract().response();
	  
	 
	  
	  
	  
		
	}

}
