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

public class UserModule_DeleteUser extends BaseClass {

	String uri;
	public RequestSpecification request;
	Response response;

	@Given("User creates DELETE Request for the LMS API endpoint for user and valid userId from {string} and {int}")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_user_id_from_and(String sheetName,
			Integer rowNumber) throws InvalidFormatException, IOException {
		this.uri = Config.USERMOD_DELETE_URL;
		logger.info("delete user with valid url and valid userId");
		System.out.println("delete batch module with valid url and valid programName " + sheetName);
		String userId = getDataFromExcel(sheetName, rowNumber).get("userId");
		request = RestAssured.given().log().all().pathParam("userId", userId);
	}

	@When("User sends HTTPS Request for user")
	public void user_sends_https_request_for_user() {

		response = this.request.when().delete(this.uri).then().log().all().extract().response();
	}

	@Then("User receives {int} Ok status with message for user deletion")
	public void user_receives_ok_status_with_message_for_user_deletion(Integer statusCode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == statusCode) {
			response.then().statusCode(statusCode);
			logger.info("Delete Request Successful");
		} else
			logger.info("Delete Request unsuccessful with status code " + statusCode);

		Assert.assertEquals(statusCode.intValue(), Poststatuscode);
	}

//test 2

	@Given("User creates DELETE Request for the LMS API endpoint for user and  invalid userId from {string} and {int}")
	public void user_creates_delete_request_for_the_lms_api_endpoint_for_user_and_in_valid_from_and(String sheetName,
			Integer rowNumber) throws InvalidFormatException, IOException {

		this.uri = Config.USERMOD_DELETE_URL;
		logger.info("delete user with valid url and invalid userId");
		System.out.println("delete batch module with valid url and valid programName " + sheetName);
		String userId = getDataFromExcel(sheetName, rowNumber).get("userId");
		request = RestAssured.given().log().all().pathParam("userId", userId);
	}

	@When("User sends HTTPS Request for user for invalid data")
	public void user_sends_https_request_for_user_for_invalid_data() {
		response = this.request.when().delete(uri).then().log().all().extract().response();
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for user")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_user(Integer statusCode) {
		int postCode = response.getStatusCode();
		response.then().assertThat().statusCode(statusCode);
		String jsonMessage = response.getBody().jsonPath().get("message");
		// Assert.assertEquals(statusCode, jsonMessage);
		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));
		Assert.assertThat(jsonMessage, Matchers.containsString("doesnot exist"));

	}
//Test 3

	@Given("User creates DELETE Request for the LMS API endpoint for user and invalid userId with empty string")
	public void user_creates_delete_request_for_the_lms_api_endpoint_for_user_and_invalid_user_id_with_empty_string() {
		this.uri = Config.USERMOD_DELETE_URL;
		logger.info("delete user with empty string for user id");
		request = RestAssured.given().log().all().pathParam("userId", "");
	}

	@Then("User receives {int} Not Found Status with message for empty string and boolean success details")
	public void user_receives_not_found_status_with_message_for_empty_string_and_boolean_success_details(
			Integer statusCode) {
		int postCode = response.getStatusCode();
		response.then().assertThat().body(JsonSchemaValidator
				.matchesJsonSchema(new File("src/test/resources/JsonSchema/BatchDelete405ErrorSchema.json")));
		response.then().assertThat().statusCode(statusCode);
		String jsonError = response.getBody().jsonPath().get("error");
		Assert.assertThat(jsonError, Matchers.containsString("Method Not Allowed"));

	

	}

//Test 4
	@Given("User create DELETE Request for the LMS API end point for user and non existing userId for userId")
	public void user_create_delete_request_for_the_lms_api_end_point_for_user_and_non_existing_user_id_for_user_id() {
		this.uri = Config.USERMOD_DELETE_URL;
		logger.info("delete user with valid url and invalid user id");
		request = RestAssured.given().log().all().pathParam("userId", 123232);
	}

	@When("User send HTTPS Request for user deleteion")
	public void user_send_https_request_for_user_deleteion() {
		response = this.request.when().delete(uri).then().log().all().extract().response();

	}

	@Then("User receives {int} status with message for failure")
	public void user_receives_status_with_message_for_failure(Integer statusCode) {
		
		int postCode = response.getStatusCode();
		response.then().assertThat().statusCode(statusCode);
		Assert.assertEquals(false, response.getBody().jsonPath().get("success"));
		String jsonMessge = response.getBody().jsonPath().get("message");
		Assert.assertThat(jsonMessge, Matchers.containsString("doesnot exist"));

	}
//test 5


@Given("User create DELETE Request for the LMS API for the user with invalid link and valid userId")
public void user_create_delete_request_for_the_lms_api_for_the_user_with_invalid_link_and_valid_user_id() {
   this.uri= "https://lmsss-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/U12e/{userId}";
   logger.info("delete user with invalid url and valid userId");
  request= RestAssured.given().log().all().pathParam("userId",1234);
   
}

@When("user send HTTPS Request with invalid link")
public void user_send_https_request_with_invalid_link() {
	response=this.request.when().delete(uri).then().log().all().extract().response();
}

@Then("User receives {int} status")
public void user_receives_status(Integer statusCode) {
	int postCode=response.getStatusCode();
	if(postCode==statusCode)
	response.then().assertThat().statusCode(statusCode);
		
}
}
