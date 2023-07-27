package com.api.automation.stepDefinitions;

import java.io.File;
import java.io.IOException;

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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BatchModule_DeleteProgram extends BaseClass {

	String uri;
	public RequestSpecification request;
	Response response;

	@Given("User creates DELETE Request for the LMS API endpoint  and  valid programName ")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_name() {
		this.uri = Config.BATCHMOD_DELETE_URL;
		logger.info("delete batch module with valid url and valid programName");

	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  valid programName from {string} and {int}")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_name_from_and(String sheetName,
			Integer rowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.BATCHMOD_DELETE_URL;
		logger.info("delete batch module with valid url and valid programName");
		System.out.println("delete batch module with valid url and valid programName " + sheetName);
		String batchId = getDataFromExcel(sheetName, rowNumber).get("batchId");
		request = RestAssured.given().log().all().pathParam("batchId", batchId);
	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid programName from {string} and {int}")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_program_name_from_and(String sheetName,
			Integer rowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.BATCHMOD_DELETE_URL;
		logger.info("delete batch module with valid url and invalid programName" + sheetName);
		System.out.println("delete batch module with valid url and invalid programName" + sheetName);
		String batchId = getDataFromExcel(sheetName, rowNumber).get("batchId");
		request = RestAssured.given().log().all().pathParam("batchId", batchId);
	}

	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		response = this.request.when().delete(this.uri).then().log().all().extract().response();
	}

	@Then("User receives {int} Ok status with message")
	public void user_receives_ok_status_with_message(Integer statusCode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == statusCode) {
			response.then().statusCode(statusCode);
			logger.info("Delete Request Successful");
		} else
			logger.info("Delete Request unsuccessful with status code " + statusCode);

		Assert.assertEquals(statusCode.intValue(), Poststatuscode);
	}

	// test 3
	@Given("User creates DELETE Request for the LMS API endpoint  and  empty string for batch id")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_empty_string_for_batch_id() {
		this.uri = Config.BATCHMOD_DELETE_URL;
		logger.info("delete batch module with valid url and empty string for the batch id");
		// System.out.println("delete batch module with valid url and valid programName
		// " + sheetName);
		// String batchId = getDataFromExcel(sheetName,rowNumber).get("BatchId");
		request = RestAssured.given().log().all().pathParam("batchId", "");

	}

	@Then("User receives {int} status with message {string}")
	public void user_receives_status_with_message(Integer statusCode, String message) {
		int Poststatuscode = response.getStatusCode();

		response.then().assertThat().body(JsonSchemaValidator
				.matchesJsonSchema(new File("src/test/resources/JsonSchema/BatchDelete405ErrorSchema.json")));

		response.then().assertThat().statusCode(statusCode);

		String jsonError = response.getBody().jsonPath().get("error");

		Assert.assertThat(jsonError, Matchers.containsString(message));
	}

	@Then("User receives {int} status with message")
	public void user_receives_status_with_message(Integer statusCode) {
		int Poststatuscode = response.getStatusCode();

		response.then().assertThat().statusCode(statusCode);

		String jsonMessage = response.getBody().jsonPath().get("message");

		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));
		Assert.assertThat(jsonMessage, Matchers.containsString("Batch not found with Id"));
		
		if (Poststatuscode == statusCode) {
			response.then().statusCode(statusCode);
			logger.info("Delete Request Successful");
		} else
			logger.info("Delete Request unsuccessful with status code " + statusCode);

		Assert.assertEquals(statusCode.intValue(), Poststatuscode);
	}

	// Test 4
	@Given("User create DELETE Request for the LMS API end point and non existing batchId for batchId")
	public void user_create_delete_request_for_the_lms_api_end_point_and_non_existing_batch_id_for_batch_id() {

		this.uri = Config.BATCHMOD_DELETE_URL;
		logger.info("delete batch module with valid url and non existing batch id");
		request = RestAssured.given().log().all().pathParam("batchId", 12323);

	}

	@When("User send HTTPS Request")
	public void user_send_https_request() {

		response = this.request.when().delete(this.uri).then().log().all().extract().response();
	}

	/**
	 * @param errorCode
	 * @param errorMessage
	 * @param statusCode
	 */
	@Then("User receives Batch errorCode {string} and errorMessage {string} should be displayed with {int} bad request status code")
	public void user_receives_batch_error_code_and_error_message_should_be_displayed_with_bad_request_status_code(
			String errorCode, String errorMessage, int statusCode) {
		int Poststatuscode = response.getStatusCode();
		response.then().assertThat().statusCode(statusCode).body("success", Matchers.equalTo(false))
				.body("message", Matchers.containsString("Batch not found with Id"))
				.header("Content-Type", Matchers.containsString("application/json"));
		if (Poststatuscode == statusCode) {
			// response.then().statusCode(statusCode);
			logger.info("Delete Request Successful");
		} else
			logger.info("Delete Request unsuccessful with status code " + Poststatuscode);

		// Assert.assertEquals(statusCode.intValue(), Poststatuscode );
	}

//Test:5

	@Given("User create DELETE request for the LMS API end point and batchId that already deleted one")
	public void user_create_delete_request_for_the_lms_api_end_point_and_batch_id_that_already_deleted_one() {
		this.uri = Config.BATCHMOD_DELETE_URL;
		logger.info("delete batch with valid url and already delted batch");
		request = RestAssured.given().log().all().pathParam("batchId",9080);

	}

	@Then("User receives {int} status with message as {string}")
	public void user_receives_status_with_message_as(Integer statusCode, String message) {
		int Poststatuscode = response.getStatusCode();
		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));

	}

}