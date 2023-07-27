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
import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.File;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProgramModule_GETProgram_SD extends BaseClass {

	String uri;
	public RequestSpecification request;
	Response response;

	@Given("User write request for Program module with Valid Base URL")
	public void user_write_request_for_program_module_with_valid_base_url() {
		this.uri = Config.programMod_GET_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("user sends Valid Base URL for Program Module");

	}

	@When("User send GET request")
	public void user_send_get_request() {
		response = this.request.get(this.uri);	
		response.then().log().all(); 
	}

	@Then("Request should be successfully displayed on console with status code {string} for GET All programs")
	public void request_should_be_successfully_displayed_on_console_with_status_code_for_get_all_programs(String statuscode) {
		//Statuscode Validation
				int GetAllstatuscode = response.getStatusCode();
				if (GetAllstatuscode == 200) {
				response.then().statusCode(Integer.parseInt(statuscode));
				//Header Validation
				response.then().assertThat().header("Connection", "keep-alive");
				logger.info("Get Request to fetch all program data is successfull");
			}

			else if (GetAllstatuscode == 404) {
				logger.info("Get Request unsuccessful");
			   } 
	}

	@Given("User write request for Program module with Invalid base URL")
	public void user_write_request_for_program_module_with_invalid_base_url() {
		this.uri = Config.programMod_GET_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");

	}

	@When("User send GET request with Invalid URL")
	public void user_send_get_request_with_invalid_url() {
		response = this.request.get(this.uri + "/" + "*" );	
		response.then().log().all();
	}

	@Then("Not found error message should be displayed on console with status code {string} for GET All programs")
	public void not_found_error_message_should_be_displayed_on_console_with_status_code_for_get_all_programs(String statuscode)
	{
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 404) {
		response.then().statusCode(Integer.parseInt(statuscode));
		logger.info("Status code 404 received for GET all program with invalid URL");
	}	
	else 
		logger.info("Get Request unsuccessful");
	}   


	@Given("User sets request for Program module with Valid Base URL and Valid Path")
	public void user_sets_request_for_program_module_with_valid_base_url_and_valid_path() {
		this.uri = Config.programMod_GET_URL_forexcel;
		this.request = RestAssured.given().header("Content-Type", "application/json");

	}

	@When("User send GET request with Valid program ID from {string} and {int}")
	public void user_send_get_request_with_valid_program_id_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();
		List<Map<String,String>> getData = 
				excelReader.getData(Config.excelFilePath, SheetName); 

		String programId = getData.get(Rownumber).get("programId");

		response = this.request
				.when()
				.get(Config.programMod_GET_URL_forexcel+"/"+ programId)
				.then()
				.log().all().extract().response();

	}

	@Then("Request should be successfully displayed on console with status code {string} for GET single program")
	public void request_should_be_successfully_displayed_on_console_with_status_code_for_get_single_program(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
		response.then().statusCode(Integer.parseInt(statuscode));
		response.then().assertThat().header("Connection", "keep-alive");
		//Json Schema Validation
		response.then().assertThat()
		.body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/JsonSchemas/GetSingleProgram.json")));

		logger.info("Get Request to fetch single program data is successful");
	}

	else if (GetAllstatuscode == 400) {
		logger.info("Get Request unsuccessful");
	    } 
	}

	@Given("User write request for Program module with Valid Base URL and Invalid Path")
	public void user_write_request_for_program_module_with_valid_base_url_and_invalid_path() {

		this.uri = Config.programMod_GET_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}


	@When("User send GET request with Invalid program ID from {string} and {int}")
	public void user_send_get_request_with_invalid_program_id_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		ExcelReader excelReader = new ExcelReader();
		List<Map<String,String>> getData = 
				excelReader.getData(Config.excelFilePath, SheetName); 

		String programId = getData.get(Rownumber).get("programId");

		response = this.request
				.when()
				.get(Config.programMod_GET_URL_forexcel+"/"+ programId)
				.then()
				.log().all().extract().response();
	}

	@Then("Bad request error message should be displayed on console with status code {string} for GET single program")
	public void bad_request_error_message_should_be_displayed_on_console_with_status_code_for_get_single_program(String statuscode) 
	{

		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200)
	{
		response.then().statusCode(Integer.parseInt(statuscode));
		response.then().assertThat().header("Connection", "keep-alive");
		//Json Schema Validation
		response.then().assertThat()
		.body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/JsonSchemas/GetSingleProgram.json")));

		logger.info("Get Request to fetch single program data is successful");
	}

	else if (GetAllstatuscode == 400)
	{
		logger.info("Get Request unsuccessful");
	 } 
	}
}

