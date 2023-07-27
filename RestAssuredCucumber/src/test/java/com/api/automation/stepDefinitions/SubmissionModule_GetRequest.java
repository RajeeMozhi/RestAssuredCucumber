package com.api.automation.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hamcrest.Matchers;
import org.junit.Assert;

import com.api.auotmation.utilities.Config;
import com.api.automation.base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SubmissionModule_GetRequest extends BaseClass {
	String uri;
	public RequestSpecification request;
	Response response;

	@Given("User creates GET Request for the LMS API endpoint")
	public void user_creates_get_request_for_the_lms_api_endpoint() {
		this.uri = Config.SUBMISSIONMOD_GET_URL;
		logger.info("get all submission");
		// request= RestAssured.given().get(uri).then().log().all().extract();
		// request = RestAssured.given().log().all().basePath(uri);
		request = RestAssured.given().basePath(uri);

	}

	@When("User sends HTTPS Request for submission module")
	public void user_sends_https_request_for_submission_module() {

		response = this.request.when().get(uri).then().log().all().extract().response();
	}

	@Then("User receives {int} OK Status with response body.")
	public void user_receives_ok_status_with_response_body(Integer statusCode) {

		response.then().assertThat().statusCode(statusCode);
		JsonPath jsonPath = new JsonPath(response.asString());
		logger.info("Assignment ID :" + jsonPath.get("assignmentId"));
		response.then().body("assignmentId", Matchers.not(Matchers.emptyArray()));
	}

//test 2
	@Given("User creates GET Request for the LMS API endpoint with valid Assignemnt ID from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_assignemnt_id_from_and(String SheetName,
			Integer RollNumber) throws InvalidFormatException, IOException {
		this.uri = Config.SUBMISSIONMOD_GETGRADE_ASSIGNMENTID;
		logger.info("get submission with valid assignment id");
		String assignmentId = getDataFromExcel(SheetName, RollNumber).get("assignmentId");
		logger.info("2. get submission with valid assignment id" + assignmentId);
		request = RestAssured.given().log().all().pathParam("assignmentId", assignmentId);
	}

	@When("User sends HTTPS Request for grades with valid assignmentId")
	public void user_sends_https_request_for_grades_with_valid_assignment_id() {
		response = this.request.when().get(uri).then().log().all().extract().response();
	}

	@Then("User receives {int} OK Status with the response body")
	public void user_receives_ok_status_with_response_body_for_valid_assignment_id(Integer statusCode) {
		int postCode = response.getStatusCode();
		response.then().assertThat().statusCode(statusCode);

	}

//test 3
	@Given("User creates GET Request for the LMS API endpoint with invalid Assignemnt ID from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_assignemnt_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.SUBMISSIONMOD_GETGRADE_ASSIGNMENTID;
		logger.info("get submission with invalid assignment id");
		String assignmentId = getDataFromExcel(SheetName, RowNumber).get("assignmentId");
		logger.info("3. get submission with invalid assignment id" + assignmentId);
		request = RestAssured.given().log().all().pathParam("assignmentId", assignmentId);

	}

	@When("User sends HTTPS Request for grades with invalid assignmentId")
	public void user_sends_https_request_for_grades_with_invalid_assignment_id() {
		response = this.request.when().get(uri).then().log().all().extract().response();
	}

	@Then("User receives {int} Not Found Status with message and boolean success details")
	public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
//		response.then().assertThat().body(JsonSchemaValidator
//				.matchesJsonSchema(new File("src/test/resources/JsonSchema/BatchDelete405ErrorSchema.json")));
//		String jsonError = response.getBody().jsonPath().get("error");
//		Assert.assertThat(jsonError, Matchers.containsString("Method NOt Found"));
		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));

	}

	// Test 4
	@Given("User creates GET Request for the LMS API endpoint with valid Student Id from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_student_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.SUBMISSIONMOD_GETGRADE_STUDENTID;
		logger.info("get submission with valid student Id from sheet " + SheetName);
		String studentId = getDataFromExcel(SheetName, RowNumber).get("studentId");
		logger.info("3. get submission with valid student Id" + studentId);
		request = RestAssured.given().log().all().pathParam("studentID", studentId);

	}

	@When("User sends HTTPS Request with valid studentId")
	public void user_sends_https_request_with_valid_student_id() {
		response = this.request.when().get(uri).then().log().all().extract().response();

	}

	@Then("user receives {int} ok message with success response body")
	public void user_receives_ok_message_with_success_response_body(Integer statusCode) {
		int postCode = response.getStatusCode();
		response.then().assertThat().statusCode(statusCode);
	}

	// Test 5
	@Given("User creates GET Request for the LMS API endpoint with invalid Student Id from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_student_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.SUBMISSIONMOD_GETGRADE_STUDENTID;
		logger.info("get submission details using invalid studentId");
		String studentId = getDataFromExcel(SheetName, RowNumber).get("studentId");
		logger.info("submission with invalid stdentid" + studentId);
		request = RestAssured.given().log().all().pathParam("studentID", studentId);

	}

	@When("User sends HTTPS Request with invalid studentId")
	public void user_sends_https_request_with_invalid_student_id() {
		response = this.request.when().get(uri).then().log().all().extract().response();
	}

	@Then("User receives {int} Not Found Status with message for invalid data")
	public void user_receives_not_found_status_with_message_for_invalid_data(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
		String jsonError = response.getBody().jsonPath().getString("error");
		//Assert.assertThat(jsonError, Matchers.containsString("Method Not Found"));
		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));
	}
// Test 6

	@Given("User creates GET Request for the LMS API endpoint with valid Batch Id from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.SUBMISSIONMOD__GETSUBMISSION_BATCHID;
		logger.info("get submissing using batch id");
		String batchId = getDataFromExcel(SheetName, RowNumber).get("batchId");
		logger.info("submission with invalid batchid" + batchId);
		request = RestAssured.given().log().all().pathParam("batchID", batchId);

	}

	@When("User sends HTTPS Request with valid Batch Id")
	public void user_sends_https_request_with_valid_batch_id() {
		response = this.request.get(uri).then().log().all().extract().response();
	}

	@Then("User receives {int} ok message for valid Batch Id")
	public void user_receives_ok_message_for_valid_batch_id(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
		response.then().body("assignmentId", Matchers.not(Matchers.emptyArray()));

	}

	// Test 7
	@Given("User creates GET Request for the LMS API endpoint with invalid Batch Id from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_batch_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.SUBMISSIONMOD__GETSUBMISSION_BATCHID;
		logger.info("get submission by invalid batch id");
		String batchId = getDataFromExcel(SheetName, RowNumber).get("batchId");
		logger.info("submission with invalid batchid" + batchId);
		request = RestAssured.given().log().all().pathParam("batchID", batchId);
	}

	@When("User sends HTTPS Request with invalid batchId")
	public void user_sends_https_request_with_invalid_batch_id() {
		response = this.request.get(uri).then().log().all().extract().response();

	}

	@Then("User receives {int} message with boolean success details for invalid batchId")
	public void user_receives_message_with_boolean_success_details_for_invalid_batch_id(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
		String jsonError = response.getBody().jsonPath().getString("error");
		//Assert.assertThat(jsonError, Matchers.containsString("Method Not Found"));
		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));

	}
//Test 8

	@Given("User creates GET Request for the LMS API endpoint with valid User Id from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_user_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.SUBMISSIONMOD_GETSUBMISSION_USERID;
		logger.info("get submissing using user id");
		String userId = getDataFromExcel(SheetName, RowNumber).get("userId");
		logger.info("submission with invalid batchid" + userId);
		request = RestAssured.given().log().all().pathParam("userId", userId);

	}

	@When("User sends HTTPS  Request with valid userId")
	public void user_sends_https_request_with_valid_user_id() {
		response = this.request.when().get(uri).then().log().all().extract().response();

	}
	@Then("User get {int} ok message for valid userId")
	public void user_get_ok_message_for_valid_user_id(Integer statusCode) {
	   response.then().assertThat().statusCode(statusCode);
		response.then().body("assignmentId", Matchers.not(Matchers.emptyArray()));

	}
	//Test 9
	@Given("User creates GET Request for the LMS API endpoint with invalid User Id from {string} and {int}")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_user_id_from_and(String SheetName, Integer RowNumber) throws InvalidFormatException, IOException {
		
		this.uri = Config.SUBMISSIONMOD_GETSUBMISSION_USERID;
		logger.info("get submissing using invalid user id");
		String userId = getDataFromExcel(SheetName,RowNumber).get("userId");
		logger.info("submission with invalid userId" + userId);
		request = RestAssured.given().log().all().pathParam("userId",userId);

	 	}
	


	@When("User sends HTTPS Request for invalid userId")
	public void user_sends_https_request_for_invalid_user_id() {
		response = this.request.get(uri).then().log().all().extract().response();
	}

	@Then("User Receives {int} with error message for invalid userId")
	public void user_receives_with_error_message_for_invalid_user_id(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
		String jsonError = response.getBody().jsonPath().getString("error");
		//Assert.assertThat(jsonError, Matchers.containsString("Method NOt Found"));
		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));

	}



}