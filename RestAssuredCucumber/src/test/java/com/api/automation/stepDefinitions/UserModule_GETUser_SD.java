package com.api.automation.stepDefinitions;

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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserModule_GETUser_SD extends BaseClass {

	String uri;
	public RequestSpecification request;
	Response response;

	// Scenario 1: Valid - A user retrieves all users

	@Given("The user sets a GET request for all users with a valid base URL")
	public void the_user_sets_a_get_request_for_all_users() {
		this.uri = Config.Base_Valid_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");

	}

	// Scenario 1: Valid - A user retrieves all users

	@When("The user sends the GET request with a valid endpoint")
	public void the_user_sends_the_get_request_to_a_valid_base_url_and_a_valid_endpoint() {
		response = this.request.get(this.uri + Config.GetAllUsers_valid_endpoint);
		response.then().log().all();
	}

	@Then("The response code should be {string} OK and The response body should contain a list of all users")
	public void the_response_code_should_be_ok_and_the_response_body_should_contain_a_list_of_all_users(
			String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		response.then().statusCode(Integer.parseInt(statuscode));
		if (GetAllstatuscode == 200) {

			// Header Validation
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request to fetch All Users is successfull");
		}

		else if (GetAllstatuscode == 404) {
			logger.info("Get All Users Request unsuccessful");
		}
	}

	// Scenario 2- A user attempts to retrieve all users using an invalid endpoint

	@When("The user sends the GET request with an invalid endpoint")
	public void the_user_sends_the_get_request_for_all_users_to_a_valid_base_with_an_invalid_endpoint() {
		response = this.request.get(this.uri + Config.GetAllUsers_invalid_endpoint);
		response.then().log().all();
	}

	// Scenario 3: Invalid - A user attempts to retrieve all users using an invalid
	// URL
	@Given("The user sets a GET request for all users with an invalid base URL")
	public void the_user_sets_a_get_request_for_all_users_invalid_URL() {
		this.uri = Config.Base_Invalid_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("The user sends the GET request to an invalid base URL but with a valid endpoint {string}")
	public void the_user_sends_the_get_request_to_an_invalid_base_url_but_with_a_valid_endpoint(String string) {
		response = this.request.get(this.uri + Config.GetAllUsers_valid_endpoint);
		response.then().log().all();
	}

	@Then("The response code should be {string} Not Found")
	public void the_response_code_should_be(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			// Header Validation
			logger.info("Get Request to fetch All Users is unsuccessfull");
		}
	}

	// Scenario 4: Valid - A user retrieves information using a valid user ID
	@Given("User creates a GET Request to get User by ID")
	public void user_creates_a_get_request_to_get_user_by_id() {
		this.uri = Config.Base_Valid_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User sends the GET request to the valid endpoint with a valid User ID from {string} and {int}")
	public void user_sends_the_Get_request_to_the_valid_endpoint_with_a_valid_user_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {

		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> getData = excelReader.getData(Config.excelFilePath, SheetName);

		String userId = getData.get(RowNumber.intValue()).get("Valid User ID");

		response = this.request.when().get(this.uri + Config.GetUserByID_valid_endpoint + userId).then().log().all()
				.extract().response();
	}

	@Then("The response code should be {string} OK and the response body should contain the details of the user")
	public void the_response_code_should_be_ok_and_the_response_body_should_contain_the_details_of_the_user(
			String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		response.then().statusCode(Integer.parseInt(statuscode));
		if (GetAllstatuscode == 200) {

			// Header Validation
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request to fetch User by User Id is successfull");
		}

		else if (GetAllstatuscode == 404) {
			logger.info("Get Request to fetch User by User Id is unsuccessfull");
		}
	}

//Scenario 5: Invalid - A user attempts to retrieve information using an invalid user ID
	@When("User sends the HTTPS request to the valid endpoint with an invalid User id from {string} and {int}")

	public void user_sends_the_https_request_to_the_valid_endpoint_with_an_invalid_user_id_from_and(String SheetName,
			Integer RowNumber) throws InvalidFormatException, IOException {

		ExcelReader excelReader = new ExcelReader();

		List<Map<String, String>> getData = excelReader.getData(Config.excelFilePath, SheetName);

		String userId = getData.get(RowNumber.intValue()).get("InvalidUserId");

		response = this.request.when().get(this.uri + Config.GetUserByID_invalid_endpoint + userId).then().log().all()
				.extract().response();
	}

	@Then("The response code should be {string} Not Found and the response should contain a boolean indicating the request was not successful")
	public void the_response_code_should_be_not_found_and_the_response_should_contain_a_boolean_indicating_the_request_was_not_successful(
			String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		response.then().statusCode(Integer.parseInt(statuscode));
		if (GetAllstatuscode == 404) {

			// Header Validation
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request to fetch User by User Id is unsuccessfull");
		}
	}

//Scenario 6 Valid- A user attempts to retrieve all staff
	@Given("User creates a GET Request to get all staff")
	public void user_creates_a_get_request_to_get_all_staff() {
		this.uri = Config.Base_Valid_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User sends the HTTPS request to the valid endpoint")
	public void user_sends_the_https_request_to_the_valid_endpoint() {
		response = this.request.get(this.uri + Config.GetAllStaff_valid_endpoint);
		response.then().log().all();
	}

	@Then("The response code should be {string} OK and the response body should contain a list of all staff members")
	public void the_response_code_should_be_ok_and_the_response_body_should_contain_a_list_of_all_staff_members(
			String statuscode) {

		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
			logger.info("Status code 200, request successful");
		} else {
			logger.warn("Expected status code 200, but got 404, Error is being reported " + GetAllstatuscode);
		}

	}

//Scenario 7 Valid- A user attempts to retrieve all Users with roles
	@Given("User creates a GET Request to retrive all users with roles")
	public void user_creates_a_get_request_to_retrive_all_users_with_roles() {
		this.uri = Config.Base_Valid_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User sends the HTTPS request to valid end point")
	public void user_sends_the_https_request_to_valid_end_point() {
		response = this.request.get(this.uri + Config.GetAllUsersWithRoles_valid_endpoint);
		response.then().log().all();
	}

	@Then("The response code should be {string} OK and the response body should contain list of all Users with roles")
	public void the_response_code_should_be_ok_and_the_response_body_should_contain_list_of_all_users_with_roles(
			String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			// Header Validation
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request to fetch All Staff is successfull");
		}

		else if (GetAllstatuscode == 404) {
			logger.info("Get All Staff Request unsuccessful");
		}
	}

	// Scenario 8-Invalid- A user attempts to retrieve all Users with roles
	@When("User sends the HTTPS request to an invalid end point")
	public void user_sends_the_https_request_to_an_invalid_end_point() {
		response = this.request.get(this.uri + Config.GetAllUsersWithRoles_invalid_endpoint);
		response.then().log().all();
	}

	@Then("The response code should be {string} Bad request")
	public void the_response_code_should_be_bad_request(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			// Header Validation
			response.then().assertThat().header("Connection", "keep-alive");
			logger.info("Get Request to fetch All User with roles is successfull");
		}

		else if (GetAllstatuscode == 404) {
			logger.info("Get Request to fetch All User with roles is unsuccessfull");
		}
	}

}