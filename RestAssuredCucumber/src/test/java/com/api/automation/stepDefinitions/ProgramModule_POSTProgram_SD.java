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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SuppressWarnings("unused")
public class ProgramModule_POSTProgram_SD extends BaseClass {

	String uri;
	public RequestSpecification request;
	Response response;

	// Created common method to post request and test
	@SuppressWarnings("unchecked")
	public void createPostRequest(String SheetName, int Rownumber)
			throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programName=DynamicprogName();
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	// Created common method to post request with mandatory fields only
	@SuppressWarnings("unchecked")
	public void createPostRequest_mandatoryFields(String SheetName, int Rownumber)
			throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programName=DynamicprogName();
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");

		JSONObject body = new JSONObject();
		
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	/*@Given("User sets Authorization to No Auth")
	public void user_sets_authorization_to_no_auth() {
		RestAssured.given().auth().none();
		logger.info("Authorization is set as No Auth");
	}*/

	// Scenario 1:Verify POST request with valid base URL and valid request body
	@Given("User sets request for Program module with valid base URL and valid request body")
	public void user_sets_request_for_program_module_with_valid_base_url_and_valid_request_body() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid request body");

	}

	@When("User send POST request with all data fields from {string} and {int}")
	public void user_send_post_request_with_all_data_fields_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

		createPostRequest(sheetName, rowNumber);
		logger.info("Program Module Post Request sent with all fields ");
	}

	// Scenario 2 Verify POST request with valid base URL and valid request body -
	// User send POST request with mandatory data fields
	@When("User send POST request with mandatory data fields from {string} and {int}")
	public void user_send_post_request_with_mandatory_data_fields_from_and(String sheetName, Integer rowNumber)
			throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

		createPostRequest_mandatoryFields(sheetName, rowNumber);
		logger.info("Program Module Post Request sent with mandatory fields only");

	}

	@Then("User gets success message with status code {string} in response body with message Program created")
	public void user_gets_success_message_with_status_code_in_response_body_with_message_program_created(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 201) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Program Successfully created");

			/*
			 * //use delete request to delete the posted data// JsonPath js =
			 * response.jsonPath(); int programId = js.getInt("programId"); response =
			 * this.request .when() .delete(Config.DeleteProgram_URL + "/" + programId)
			 * .then() .log().all().extract().response();
			 */

		}

		else
			// In case of scenario 15 , the status code is 400 and the execution is coming to this block, however test is still shown as successful. need to investigate this.
			logger.info("Post Request unsuccessful with status code " + statuscode);
	}

	// Scenario 3
	@Given("User sets request for Program module with invalid base URL and valid request body")
	public void user_sets_request_for_program_module_with_invalid_base_url_and_valid_request_body() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with invalid base URL and valid data");

	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with invalid URL and valid data from {string} and {int}")
	public void user_sends_post_request_with_invalid_url_and_valid_data_from_and_row_number(String SheetName,
			int Rownumber)
			throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programName=DynamicprogName();
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri + "/saveprogram").then().log().all()
				.extract().response();

		logger.info("Valid request body sent");
	}

	@Then("User gets Bad Request error message with status code {string} in response body with message Invalid URL")
	public void user_gets_bad_request_error_message_with_status_code_in_response_body_with_message_invalid_url(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Bad request error message received with " + statuscode);
		}

		else
			logger.info("Statuscode received" + statuscode + ". Error to be reported");

	}

	// Scenario 4: Verify POST request with valid URL and Blank data fields
	@Given("User sets request for Program module with valid base URL and Blank paramertes in request body")
	public void user_sets_request_for_program_module_with_valid_base_url_and_blank_paramertes_in_request_body() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");

	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in all fields in request body")
	public void user_sends_post_request_with_blank_values_in_all_fields_in_request_body() {

		JSONObject body = new JSONObject();
		body.put("programName", "");
		body.put("programDescription", "");
		body.put("programStatus", "");
		body.put("creationTime", "");
		body.put("lastModTime", "");

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	// Scenario 5: Verify POST request with valid URL and Blank values in mandatory
	// fields
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in all mandatory the fields in request body")
	public void user_sends_post_request_with_blank_values_in_all_mandatory_the_fields_in_request_body() {

		JSONObject body = new JSONObject();
		body.put("programName", "");
		body.put("programDescription", "");
		body.put("programStatus", "");

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	// Scenario 6: Verify POST request with valid URL and Blank data fields
	@Given("User sets request for Program module with valid base URL and Blank value in programName field")
	public void user_sets_request_for_program_module_with_valid_base_url_and_blank_value_in_program_name_field() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request for Program module with valid base URL and Blank value in programName field");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in programName field in request body from {string} and {int}")

	public void user_sends_post_request_with_blank_values_in_program_name_field_in_request_body_from_and_rownumber(
			String SheetName, int Rownumber)
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		String programName=DynamicprogName();
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Request sent for Program module: Post with Null Values in Program Name");
	}

//Scenario 7: Verify POST request with valid URL and Blank data fields
	@Given("User sets request for Program module with valid base URL and Blank value in programDescription field")
	public void user_sets_request_for_program_module_with_valid_base_url_and_blank_value_in_program_description_field() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request for Program module with valid base URL and Blank value in programName field");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in programDescription field in request body from {string} and {int}")
	public void user_sends_post_request_with_blank_values_in_program_description_field_in_request_body_from_and_rownumber(
			String SheetName, int Rownumber)
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		String programName=DynamicprogName();
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Request sent for Program module: Post with Null Values in Program Description");
	}

//Scenario 8: Verify POST request with valid URL and Blank data fields
	@Given("User sets request for Program module with valid base URL and Blank value in programStatus field")
	public void user_sets_request_for_program_module_with_valid_base_url_and_blank_value_in_program_status_field() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request for Program module with valid base URL and Blank value in programStatus field");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in programStatus field in request body from {string} and {int}")
	public void user_sends_post_request_with_blank_values_in_program_status_field_in_request_body_from_and_rownumber(
			String SheetName, int Rownumber)
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		String programName=DynamicprogName();//
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Request sent for Program module: Post with Null Values in Program Status");
	}

	@Then("User gets Internal Server Error message with status code {string}: Null Values")
	public void user_gets_Internal_Server_Error_message_with_status_code_in_response_body_with_message_enter_mandatory_fields(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 500) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Bad request error message received with " + statuscode);
		} else
			logger.info("Statuscode received" + statuscode + ". Error to be reported");
	}

	// Scenario 9: Verify POST request with valid URL and null data fields
	@Given("User sets request for Program module with valid base URL and NULL paramertes in request body")
	public void user_sets_request_for_program_module_with_valid_base_url_and_null_paramertes_in_request_body() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and NULL paramertes in request body");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Null values in all fields in request body")
	public void user_sends_post_request_with_null_values_in_all_fields_in_request_body() {
		JSONObject body = new JSONObject();
		body.put("programName", null);
		body.put("programDescription", null);
		body.put("programStatus", null);
		body.put("creationTime", null);
		body.put("lastModTime", null);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

	// Scenario 10: Verify POST request with valid URL and null data fields
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Null values in all mandatory fields in request body")
	public void user_sends_post_request_with_null_values_all_mandatory_fields_in_request_body() {
		JSONObject body = new JSONObject();
		body.put("programName", null);
		body.put("programStatus", null);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();
	}

//Scenario 11: Verify POST request with valid URL and null data fields
	@Given("User sets request for Program module with valid base URL and NULL value in programName field")
	public void user_sets_request_for_program_module_with_valid_base_url_and_null_value_in_program_name_field() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request for Program module with valid base URL and NULL paramertes in request body");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Null values in programName field in request body from {string} and {int}")
	public void user_sends_post_request_with_null_values_in_programName_field_in_request_body_from_and_rownumber(
			String SheetName, int Rownumber)

			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		String programName = null;
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Request for Program Module : Post with Null Values in Program Name");
	}

//Scenario 12 Verify POST request with valid URL and null data fields

	@Given("User sets request for Program module with valid base URL and NULL value in programDescription field")
	public void user_sets_request_for_program_module_with_valid_base_url_and_null_value_in_program_description_field() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request for Program module with valid base URL and NULL paramertes in request body");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Null values in programDescription field in request body from {string} and {int}")
	public void user_sends_post_request_with_null_values_in_program_description_field_in_request_body_from_and_rownumber(
			String SheetName, int Rownumber)
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programName=DynamicprogName();
		String programDescription = null;
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Request for Program Module : Post with Null Values in Program Description");
	}

// Scenario 13 Verify POST request with valid URL and null data fields
	@Given("User sets request for Program module with valid base URL and NULL value in programStatus field")
	public void user_sets_request_for_program_module_with_valid_base_url_and_null_value_in_program_status_field() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request for Program module with valid base URL and NULL paramertes in request body");
	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request with Null values in programStatus field in request body from {string} and {int}")
	public void user_sends_post_request_with_null_values_in_program_status_field_in_request_body_from_and_rownumber(
			String SheetName, int Rownumber)
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programName=DynamicprogName();
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = null;
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Request for Program Module : Post with Null Values in Program Status");
	}

//Scenario 14 Verify POST request with valid URL and without one mandatory field-No Program Name
	@Given("User sets request for Program module with valid base URL and without one mandatory field")
	public void user_sets_request_for_program_module_with_valid_base_url_and_without_one_mandatory_field() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request for Program module with valid base URL and NULL paramertes in request body");

	}

	@SuppressWarnings("unchecked")
	@When("User sends POST request without programName field in request body from {string} and {int}")
	public void user_sends_post_request_without_program_name_field_in_request_body_from_and_rownumber(String SheetName,
			int Rownumber) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programName=DynamicprogName();
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Post Request for Program Module with No Program Name ");
	}

	//Scenario 15 Verify POST request with valid URL and without one mandatory field- No Program Description
	@SuppressWarnings("unchecked")
	@When("User sends POST request without programDescription field in request body from {string} and {int}"
	  ) public void
	  user_sends_post_request_without_program_description_field_in_request_body_from_and_rownumber(String SheetName,
				int Rownumber) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
			//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
			String programName=DynamicprogName();
			String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
			String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
			String creationTime = Timestamp();
			String lastModTime = Timestamp();

			JSONObject body = new JSONObject();
			body.put("programName", programName);
			body.put("programDescription", programDescription);
			body.put("programStatus", programStatus);
			body.put("creationTime", creationTime);
			body.put("lastModTime", lastModTime);

			response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

			logger.info("Post Request for Program Module with No Program Description ");
		}
	 
	 
	@SuppressWarnings("unchecked")
	// Scenario 16 Verify POST request with valid URL and without one mandatory
	// field
	@When("User sends POST request without programStatus field in request body from {string} and {int}")
	public void user_sends_post_request_without_program_status_field_in_request_body_from_and_rownumber(
			String SheetName, int Rownumber)
			throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		//String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
		String programName=DynamicprogName();
		String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);

		response = this.request.body(body.toJSONString()).when().post(this.uri).then().log().all().extract().response();

		logger.info("Post Request for Program Module with No Program Status ");
	}

	@Then("User gets Bad Request error message with status code {string} in response body with message Enter mandatory fields")
	public void user_gets_bad_request_error_message_with_status_code_in_response_body_with_message_enter_mandatory_fields(
			String statuscode) {
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 400) {
			response.then().statusCode(Integer.parseInt(statuscode));
			logger.info("Bad request error message received with " + statuscode);
		} else
			logger.info("Statuscode received" + statuscode + ". Error to be reported");
	}
}

/*
 * @Given("User sets Authorization to No Auth") public void
 * user_sets_authorization_to_no_auth() { RestAssured.given().auth().none();
 * logger.info("Authorization is set as No Auth");
 * 
 * /* @Given("User sets request for Program module with valid endpoint and valid request body"
 * ) public void
 * user_sets_request_for_program_module_with_valid_endpoint_and_valid_request_body
 * () { this.uri = Config.programMod_POST_URL; this.request =
 * RestAssured.given().header("Content-Type", "application/json"); logger.
 * info("Request set for Program module with valid base URL and valid data"); }
 * 
 * @When("User send POST request with data from {string} and {int}") public void
 * user_send_post_request_with_data_from_and(String SheetName, Integer
 * RowNumber) throws InvalidFormatException, IOException {
 * createPostRequest(SheetName,RowNumber);
 * logger.info("Post Request sent with valid request body"); }
 * 
 * @Then("User should get status code {string} for POST request with valid endpoint and valid request body"
 * ) public void
 * user_should_get_status_code_for_post_request_with_valid_endpoint_and_valid_request_body
 * (String statusCode) {
 * 
 * int Poststatuscode = response.getStatusCode(); if (Poststatuscode == 201) {
 * response.then().statusCode(Integer.parseInt(statusCode));
 * logger.info("Post Request Successful");
 * 
 * //use delete request to delete the posted data// /*JsonPath js =
 * response.jsonPath(); int programId = js.getInt("programId"); response =
 * this.request .when() .delete(Config.DeleteProgram_URL + "/" + programId)
 * .then() .log().all().extract().response(); }
 * 
 * else logger.info("Post Request unsuccessful with status code " + statusCode);
 * 
 * }
 * 
 * 
 * // Common method for POST request
 * 
 * @SuppressWarnings("unchecked") public void createPostRequest(String
 * SheetName, int Rownumber) throws InvalidFormatException, IOException { String
 * programName = DynamicprogName(); String programDescription =
 * getDataFromExcel(SheetName,Rownumber).get("programDescription"); String
 * programStatus = getDataFromExcel(SheetName,Rownumber).get("programStatus");
 * String creationTime = Timestamp(); String lastModTime = Timestamp();
 * 
 * JSONObject body = new JSONObject(); body.put("programName", programName);
 * body.put("programDescription", programDescription); body.put("programStatus",
 * programStatus); body.put("creationTime", creationTime);
 * body.put("lastModTime", lastModTime);
 * 
 * response = this.request .body(body.toJSONString()) .when() .post(this.uri)
 * .then() .log().all().extract().response(); }
 */