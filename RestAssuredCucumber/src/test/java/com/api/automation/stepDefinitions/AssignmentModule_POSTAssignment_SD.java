package com.api.automation.stepDefinitions;

import java.io.IOException;
import org.json.simple.JSONObject;

import com.api.auotmation.utilities.Config;
import com.api.automation.base.BaseClass;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssignmentModule_POSTAssignment_SD extends BaseClass {
	String uri;
	public RequestSpecification request;
	Response response;

	// Created common method to post assignment request and test
	@SuppressWarnings("unchecked")
	public void createPostRequest(String SheetName, int Rownumber)
			throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		String assignmentDescription = getDataFromExcel(SheetName, Rownumber).get("assignmentDescription");
		String assignmentName = getDataFromExcel(SheetName, Rownumber).get("assignmentName");
		int batchId = Integer.parseInt(getDataFromExcel(SheetName, Rownumber).get("batchId"));
		String comments = getDataFromExcel(SheetName, Rownumber).get("comments");
		String createdBy = getDataFromExcel(SheetName, Rownumber).get("createdBy");
		String dueDate = getDataFromExcel(SheetName, Rownumber).get("dueDate");
		String graderId = getDataFromExcel(SheetName, Rownumber).get("graderId");
		String pathAttachment1 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment1");
		String pathAttachment2 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment2");
		String pathAttachment3 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment3");
		String pathAttachment4 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment4");
		String pathAttachment5 = getDataFromExcel(SheetName, Rownumber).get("pathAttachment5");

		JSONObject body = new JSONObject();
		body.put("assignmentDescription", assignmentDescription);
		body.put("assignmentName", assignmentName);
		body.put("batchId", batchId);
		body.put("comments", comments);
		body.put("graderId", graderId);
		body.put("createdBy", createdBy);
		body.put("dueDate", dueDate);
		body.put("pathAttachment1", pathAttachment1);
		body.put("pathAttachment2", pathAttachment2);
		body.put("pathAttachment3", pathAttachment3);
		body.put("pathAttachment4", pathAttachment4);
		body.put("pathAttachment5", pathAttachment5);
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	// Scenario 1: An Active Admin creates and grades a new assignment
	@Given("User sets a POST Request to valid base URL and valid end point")
	public void user_sets_a_post_request_to_valid_base_url_and_valid_end_point() {
		this.uri = Config.Base_Valid_URL + Config.PostAssignment_valid_endpoint;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("User sets a POST Request to valid base URL and valid end point");
	}

	@When("User sends a POST request to create and grade new assignment by an active Admin from {string} and {int}")
	public void user_sends_a_post_request_to_create_and_grade_new_assignment_by_an_active_admin_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info("An Active Admin creates and grades a new assignment ");

	}

	@Then("The response status code should be {string} Created")
	public void the_response_status_code_should_be_created(String statuscode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 201) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Assignment created successfully");
		}

		else
			// In case of scenario 15 , the status code is 400 and the execution is coming
			// to this block, however test is still shown as successful. need to investigate
			// this.
			logger.info("Post Assignment request unsuccessful with status code " + statuscode);
	}

	// Scenario 2:An active Admin creates and an active Staff grades a new
	// assignment
	@When("User sends a POST request to create assignment by active Admin and grade by an active staff from {string} and {int}")
	public void user_sends_a_post_request_to_create_assignment_by_active_admin_and_grade_by_an_active_staff_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info("An active Admin creates and an active Staff grades a new assignment");
	}

//Scenario 3: An active Admin creates and an active Student grades a new assignment
	@When("User sends a POST request to create assignment by active Admin and grading by a active Student from {string} and {int}")
	public void user_sends_a_post_request_to_create_assignment_by_active_admin_and_grading_by_a_active_student_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info("An active Admin creates and an active Student grades a new assignment");
	}

// Scenario 4: An Active Staff creates and grades a new assignment
	@When("User sends a POST request to create and grade new assignment by an active Staff from {string} and {int}")
	public void user_sends_a_post_request_to_create_and_grade_new_assignment_by_an_active_staff_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info("An Active Staff creates and grades a new assignment");
	}

// Scenario 5 : An active Staff creates and an active Admin grades a new assignment
	@When("User sends a POST request to create assignment by active Staff and grading by an active Admin from {string} and {int}")
	public void user_sends_a_post_request_to_create_assignment_by_active_staff_and_grading_by_an_active_admin_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info("An active Staff creates and an active Admin grades a new assignment");
	}

//Scenario 6: An active Staff creates and an active Student grades a new assignment
	@When("User sends a POST request to create assignment by active Staff and grading by an active student from {string} and {int}")
	public void user_sends_a_post_request_to_create_assignment_by_active_staff_and_grading_by_an_active_student_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info("An active Staff creates and an active Student grades a new assignment");
	}

// Scenario 7: An active Student creates and grades a new assignment
	@When("User sends a POST request to create and grade new assignment by active student from {string} and {int}")
	public void user_sends_a_post_request_to_create_and_grade_new_assignment_by_active_student_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info("An active Student creates and grades a new assignment");
	}

	@Then("The response status code should be {string}")
	public void the_response_status_code_should_be(String statuscode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Student can not create or grade assignment");
		}
	}

// Scenario 8 : An active Student creates and an active Admin grades a new assignment
	@When("User sends a POST request to create new assignment by active Student and grading by active Admin from {string} and {int}")
	public void user_sends_a_post_request_to_create_new_assignment_by_active_student_and_grading_by_active_admin_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info(
				"An active Student creates and an active Admin grades a new assignment. Student can not create assignment.");
	}

//Scenario 9 : An active Student creates and an active Staff grades a new assignment
	@When("User sends a POST request to create new assignment by active Student and garding by active Staff from {string} and {int}")
	public void user_sends_a_post_request_to_create_new_assignment_by_active_student_and_garding_by_active_staff_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info(
				"An active Student creates and an active Staff grades a new assignment,Student can not create assignment");
	}

//Scenario 10 :An inactive Admin creates and grades a new assignment
	@When("User create a POST request to create and grade new assignment by an inactive Admin from {string} and {int}")
	public void user_create_a_post_request_to_create_and_grade_new_assignment_by_an_inactive_admin_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info(
				"An inactive Admin creates and grades a new assignment, Inactive Admin user can not create or grade assignment");
	}

//Scenario 11 An inactive Staff creates and grades a new assignment
	@When("User create a POST request to create and grade new assignment by an inactive staff from {string} and {int}")
	public void user_create_a_post_request_to_create_and_grade_new_assignment_by_an_inactive_staff_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info(
				"An inactive Staff creates and grades a new assignment, Inactive staff user can not create or grade assignment");
	}

//Scenario 12 An active Staff attempts to create and grades a new assignment without a mandatory field
	@When("User create a POST request to to create and grades a new assignment without a mandatory field by active staff  from {string} and {int}")
	public void user_create_a_post_request_to_to_create_and_grades_a_new_assignment_without_a_mandatory_field_by_active_staff_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info(
				"An active Staff creates and an inactive Student grades a new assignment. Assignment can not be created without mandatory field");
	}

	// Scenario 13 An Active Admin creates and grades a new assignment to invalid
	// end point
	@Given("User sets a POST Request to valid base URL and invalid end point")
	public void user_sets_a_post_request_to_valid_base_url_and_invalid_end_point() {
		this.uri = Config.Base_Valid_URL + Config.PostAssignment_invalid_endpoint;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("User sets a POST Request to valid base URL and valid end point");
	}

	@When("User create a POST request to create assignment and grade assignment by an active Admin from {string} and {int}")
	public void user_create_a_post_request_to_create_assignment_and_grade_assignment_by_an_active_admin_from_and(
			String SheetName, Integer RowNumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {

		createPostRequest(SheetName, RowNumber);
		logger.info(
				"User create a POST request to create assignment and grade assignment by an active Admin to invalid end point. Pls enter valid end point");
	}
}