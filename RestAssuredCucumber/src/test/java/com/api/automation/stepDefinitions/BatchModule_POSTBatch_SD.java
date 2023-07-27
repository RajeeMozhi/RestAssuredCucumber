/*package com.api.automation.stepDefinitions;

//package com.api.automation.stepDefinitions;
import com.api.auotmation.utilities.ExcelReader;
import com.api.auotmation.utilities.Config;
import com.api.automation.base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


@SuppressWarnings("unused")
public class BatchModule_POSTBatch_SD extends BaseClass {

	public String uri, Batch_uri;
	public RequestSpecification request, Batch_request;
	public Response response, batch_response;
	public static Integer programID = null, batchId = null;

	@Given("User sets Authorization to No Auth")
	public void user_sets_authorization_to_no_auth() {
		RestAssured.given().auth().none();
		logger.info("Authorization is set as No Auth");
	}
//------------------------ program module creation scenario-------------------------------------------
	@Given("User sets request for Program module with valid endpoint and valid request body")
	public void user_sets_request_for_program_module_with_valid_endpoint_and_valid_request_body() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");
	}

	@When("User send POST request with data from {string} and {int}")
	public void user_send_post_request_with_data_from_and(String SheetName, Integer Rownumber)
			throws InvalidFormatException, IOException {
		createPostRequest(SheetName, Rownumber);
		logger.info("Post Request sent with valid request body");
	}

	@Then("User should get status code {int} for POST request with valid endpoint and valid request body")
	public void user_should_get_status_code_for_post_request_with_valid_endpoint_and_valid_request_body(
			int statusCode) {
		 
		if (this.response == null) {
		        logger.error("response is null. Please ensure the POST request is executed correctly.");
		        return;
		    }
		
		int Poststatuscode = this.response.getStatusCode();
		if (Poststatuscode == 201) {
			this.response.then().statusCode(statusCode);
			logger.info("Post Request Successful");

		}

		else
			logger.info("Post Request unsuccessful with status code " + statusCode);

	}
	//------------------------ Batch module creation scenario-------------------------------------------
	@Given("User sets request for Batch module with valid endpoint and valid request body")
	public void user_sets_request_for_batch_module_with_valid_endpoint_and_valid_request_body() {

		this.Batch_uri = Config.BatchModule_POST_URL;
		System.out.println(Batch_uri);
		this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and valid data");

	}

	@When("User send POST request with data for batch module from {string} and {int}")
	public void user_send_post_request_with_data_for_batch_module_from_and(String SheetName, Integer Rownumber)
			throws InvalidFormatException, IOException {

		// createPostRequest(SheetName,Rownumber); //before creating batch
		createBatchPostRequest(SheetName, Rownumber);

		logger.info("Post Request sent with valid request body");
	}

	
// ctl + shift + i

	@Then("User successful created batch with status code {int}")
	public void user_successful_created_batch_with_status_code(int statusCode) {
		if (this.batch_response == null) {
	        logger.error("batch_response is null. Please ensure the POST request is executed correctly.");
	        return;
	    }

		int Poststatuscode = this.batch_response.getStatusCode();
		if (Poststatuscode == 201) {
			this.batch_response.then().statusCode(statusCode);;
			logger.info("Post Request Successful");
		}
		else
			logger.info("Post Request unsuccessful with status code " + statusCode);

	}
	//------------------------ Batch module Schema validation scenario-------------------------------------------
	@Then("Schema should be validated")
	public void schema_should_be_validated() {
	    
	}
	
	//------------------------ Batch module Blank paramertes in request body scenario-------------------------------------------
	
	@Given("User sets request for Batch module with valid base URL and Blank paramertes in request body")
	public void user_sets_request_for_batch_module_with_valid_base_url_and_blank_paramertes_in_request_body1() {
	   
		 this.Batch_uri = Config.BatchModule_POST_URL;
		    this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		    logger.info("Request set for Batch module with valid base URL and blank data");
		
	}
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in all fields in request body")
	public void user_sends_post_request_with_blank_values_in_all_fields_in_request_body() {
		JSONObject Batch_body = new JSONObject();
		Batch_body.put("batchStatus", "");
		Batch_body.put("batchName", "");
		Batch_body.put("batchDescription", "");
		Batch_body.put("batchNoOfClasses", "");
		System.out.println(programID);
		Batch_body.put("programId", "");

		batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
				.extract().response();
		
	}

	@Then("User gets Bad Request error message with status code {int} in response body with message Enter mandatory fields")
	public void user_gets_bad_request_error_message_with_status_code_in_response_body_with_message_enter_mandatory_fields(int statusCode) {
		
		if (this.batch_response == null) {
	        logger.error("batch_response is null. Please ensure the POST request is executed correctly.");
	        return;
	    }
		
		int Poststatuscode = this.batch_response.getStatusCode();
		if (Poststatuscode == 400) {
			this.batch_response.then().statusCode(statusCode);;
			logger.info("Post Request Fails");

		}

		else
			logger.info("Post Request unsuccessful with defect and  " + statusCode);

	}
	//------------------------ Batch module mandatory paramertes blank in request body scenario-------------------------------------------
	
	@Given("User sets request for Batch Module with valid base URL and Blank paramertes in request body")
	public void user_sets_request_for_batch_module_with_valid_base_url_and_blank_paramertes_in_request_body() {
		 this.Batch_uri = Config.BatchModule_POST_URL;
		    this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and blank data for mandatory fields");
		
	}
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in all mandatory the fields in request body")
	public void user_sends_post_request_with_blank_values_in_all_mandatory_the_fields_in_request_body() {
		JSONObject Batch_body = new JSONObject();
		Batch_body.put("batchStatus", "");
		Batch_body.put("batchName", "");
		Batch_body.put("batchDescription", "Selenium");
		Batch_body.put("batchNoOfClasses", "");
		System.out.println(programID);
		Batch_body.put("programId", "");

	//	batch_response = this.Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all().extract().response();
		batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
				.extract().response();
		
	}
	//------------------------ Batch module Batch name Blank in request body scenario-------------------------------------------
	

	@Given("User sets request for Batch Module with valid base URL and Blank value in BatchName field")
	public void user_sets_request_for_batch_module_with_valid_base_url_and_blank_value_in_batch_name_field() {
		this.Batch_uri = Config.BatchModule_POST_URL; 
		  this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and blank BatchName field ");
	}
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in BatchName field in request body from {string} and {int}")
	public void user_sends_post_request_with_blank_values_in_batch_name_field_in_request_body_from_and(String SheetName, int Rownumber) throws InvalidFormatException, IOException {
		//String batchName = DynamicBatchName();
		 String batchName = getDataFromExcel(SheetName,Rownumber).get("batchName");
		String batchDescription = getDataFromExcel(SheetName, Rownumber).get("batchDescription");
		String batchNoOfClasses = getDataFromExcel(SheetName, Rownumber).get("batchNoOfClasses");
		// Integer batchNoOfClasses = getDataFromExcel(SheetName,Rownumber).get
		String batchStatus = getDataFromExcel(SheetName, Rownumber).get("batchStatus");
		JSONObject Batch_body = new JSONObject();
		Batch_body.put("batchStatus", batchStatus);
		Batch_body.put("batchName", batchName);
		Batch_body.put("batchDescription", batchDescription);
		Batch_body.put("batchNoOfClasses", batchNoOfClasses);
		System.out.println(programID);
		Batch_body.put("programId", programID);
		
		batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
				.extract().response();
		
	}
	
	//------------------------ Batch module Batch Status Blank in request body scenario-------------------------------------------
	

	@Given("User sets request for Batch Module with valid base URL and Blank value in BatchStatus field")
	public void user_sets_request_for_batch_module_with_valid_base_url_and_blank_value_in_batch_status_field() {
		 this.Batch_uri = Config.BatchModule_POST_URL;
		    this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and blank BatchStatus field ");
	}
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in BatchStatus field in request body from {string} and {int}")
	public void user_sends_post_request_with_blank_values_in_batch_status_field_in_request_body_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		String batchName = DynamicprogName();
				 //String batchName = getDataFromExcel(SheetName,Rownumber).get("batchName");
				String batchDescription = getDataFromExcel(SheetName, Rownumber).get("batchDescription");
				String batchNoOfClasses = getDataFromExcel(SheetName, Rownumber).get("batchNoOfClasses");
				String batchStatus = getDataFromExcel(SheetName, Rownumber).get("batchStatus");
				JSONObject Batch_body = new JSONObject();
				Batch_body.put("batchStatus", batchStatus);
				Batch_body.put("batchName", batchName);
				Batch_body.put("batchDescription", batchDescription);
				Batch_body.put("batchNoOfClasses", batchNoOfClasses);
				System.out.println(programID);
				Batch_body.put("programId", programID);

				batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
						.extract().response();
	}
	
	//------------------------ Batch module Program ID Blank in request body scenario-------------------------------------------

	@Given("User sets request for Batch Module with valid base URL and Blank value in ProgramId field")
	public void user_sets_request_for_batch_module_with_valid_base_url_and_blank_value_in_program_id_field() {
		 this.Batch_uri = Config.BatchModule_POST_URL;
		    this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch Module with valid base URL and Blank value in ProgramId field ");;
	}
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in ProgramId field in request body from {string} and {int}")
	public void user_sends_post_request_with_blank_values_in_program_id_field_in_request_body_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		String batchName = DynamicprogName();
		 //String batchName = getDataFromExcel(SheetName,Rownumber).get("batchName");
		String batchDescription = getDataFromExcel(SheetName, Rownumber).get("batchDescription");
		String batchNoOfClasses = getDataFromExcel(SheetName, Rownumber).get("batchNoOfClasses");
		String batchStatus = getDataFromExcel(SheetName, Rownumber).get("batchStatus");
		JSONObject Batch_body = new JSONObject();
		Batch_body.put("batchStatus", batchStatus);
		Batch_body.put("batchName", batchName);
		Batch_body.put("batchDescription", batchDescription);
		Batch_body.put("batchNoOfClasses", batchNoOfClasses);
		Batch_body.put("programId", "");

		batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
				.extract().response();
	}
	//------------------------ Batch module No Of Classes Blank in request body scenario-------------------------------------------
	
	@Given("User sets request for Batch Module with valid base URL and Blank value in NoOfClasses field")
	public void user_sets_request_for_batch_module_with_valid_base_url_and_blank_value_in_no_of_classes_field() {
		 this.Batch_uri = Config.BatchModule_POST_URL;
		    this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch Module with valid base URL and Blank value in NoOfClasses fieldfor Batch module with valid base URL and blank BatchName field ");
	}
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Blank values in NoOfClasses field in request body from {string} and {int}")
	public void user_sends_post_request_with_blank_values_in_no_of_classes_field_in_request_body_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		String batchName = DynamicprogName();
		String batchDescription = getDataFromExcel(SheetName, Rownumber).get("batchDescription");
		String batchNoOfClasses = getDataFromExcel(SheetName, Rownumber).get("batchNoOfClasses");
		String batchStatus = getDataFromExcel(SheetName, Rownumber).get("batchStatus");
		JSONObject Batch_body = new JSONObject();
		Batch_body.put("batchStatus", batchStatus);
		Batch_body.put("batchName", batchName);
		Batch_body.put("batchDescription", batchDescription);
		Batch_body.put("batchNoOfClasses", batchNoOfClasses);
		System.out.println(programID);
		Batch_body.put("programId", programID);

		batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
				.extract().response();
	}
//---------------------------------Batch module NULL Value in Batch Status-------------------------------------
	
	@Given("User sets request for Batch module with valid base URL and NULL value in BatchStatus field")
	
	public void user_sets_request_for_batch_module_with_valid_base_url_and_null_value_in_batch_status_field() {
		 this.Batch_uri = Config.BatchModule_POST_URL;
		    this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and NULL value in BatchStatus field ");
	}
	
	@When("User sends POST request with NULL values in BatchStatus field in request body from {string} and {int}")
	public void user_sends_post_request_with_NULL_values_in_batch_status_field_in_request_body_from_and(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {
		String batchName = DynamicprogName();
				 //String batchName = getDataFromExcel(SheetName,Rownumber).get("batchName");
				String batchDescription = getDataFromExcel(SheetName, Rownumber).get("batchDescription");
				String batchNoOfClasses = getDataFromExcel(SheetName, Rownumber).get("batchNoOfClasses");
				String batchStatus = getDataFromExcel(SheetName, Rownumber).get("batchStatus");
				JSONObject Batch_body = new JSONObject();
				Batch_body.put("batchStatus", batchStatus);
				Batch_body.put("batchName", batchName);
				Batch_body.put("batchDescription", batchDescription);
				Batch_body.put("batchNoOfClasses", batchNoOfClasses);
				System.out.println(programID);
				Batch_body.put("programId", programID);

				batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
						.extract().response();
	}
	

	@Then("User gets Internal Server Error message with status code {int} Null Values")
	public void user_gets_internal_server_error_message_with_status_code_null_values(int statusCode) {
		
		 if (this.batch_response == null) {
		        logger.error("batch_response is null. Please ensure the POST request is executed correctly.");
		        return;
		    }
		 
		int Poststatuscode = this.batch_response.getStatusCode();
		if (Poststatuscode == 500) {
			this.batch_response.then().statusCode(statusCode);;
			logger.info("Post Request Fails");

		}

		else
			logger.info("Post Request unsuccessful with defect and  status code " + statusCode);

	}
//---------------------- Batch module NULL value in All Parameter in request body-----------
	@Given("User sets request for Batch module with valid base URL and NULL paramertes in request body")
	public void user_sets_request_for_batch_module_with_valid_base_url_and_null_paramertes_in_request_body() {
		 this.Batch_uri = Config.BatchModule_POST_URL;
		    this.Batch_request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Batch module with valid base URL and NULL value in BatchStatus field ");
	}
	
	@SuppressWarnings("unchecked")
	@When("User sends POST request with Null values in all fields in request body")
	public void user_sends_post_request_with_null_values_in_all_fields_in_request_body() {
		JSONObject Batch_body = new JSONObject();
		Batch_body.put("batchStatus", null);
		Batch_body.put("batchName", null);
		Batch_body.put("batchDescription", null);
		Batch_body.put("batchNoOfClasses", null);
		System.out.println(programID);
		Batch_body.put("programId", null);
		batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
				.extract().response();
	}

	
	@SuppressWarnings("unchecked")
	public void createPostRequest(String SheetName, int Rownumber) throws InvalidFormatException, IOException {
		// if (programID == null) {
		String programName = DynamicprogName();
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
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		// String programID = js.getString("programId");
		programID = js.getInt("programId");

		System.out.println(programID);

	}
	@SuppressWarnings("unchecked")
	public void createBatchPostRequest(String SheetName, Integer Rownumber) throws InvalidFormatException, IOException {

		String Batch_creationTime = Timestamp();
		String Batch_lastModTime = Timestamp();
		;
		String batchName = DynamicprogName();
		// String batchName = getDataFromExcel(SheetName,Rownumber).get("batchName");
		String batchDescription = getDataFromExcel(SheetName, Rownumber).get("batchDescription");
		String batchNoOfClasses = getDataFromExcel(SheetName, Rownumber).get("batchNoOfClasses");
		// Integer batchNoOfClasses = getDataFromExcel(SheetName,Rownumber).get
		String batchStatus = getDataFromExcel(SheetName, Rownumber).get("batchStatus");

		JSONObject Batch_body = new JSONObject();
		Batch_body.put("batchStatus", batchStatus);
		Batch_body.put("batchName", batchName);
		Batch_body.put("batchDescription", batchDescription);
		Batch_body.put("batchNoOfClasses", batchNoOfClasses);
		System.out.println(programID);
		Batch_body.put("programId", programID);

		batch_response = Batch_request.body(Batch_body.toJSONString()).when().post(this.Batch_uri).then().log().all()
				.extract().response();
		String batch_resp = batch_response.asString();
		JsonPath js = new JsonPath(batch_resp);
		batchId = js.getInt("batchId");
	}

	public String getJsonPath(Response response, String key)

	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
	/// Raaji Code
	/*
	 * @Then("Schema should be validated") public void schema_should_be_validated()
	 * {
	 * 
	 * int sc=response.getStatusCode(); if(sc==200) {
	 * response=this.request.when().get(Config.programMod_PUT_GetSingleProgram_URL+
	 * programId).then().log().all().extract().response();
	 * response.then().assertThat().header("Connection", "keep-alive");
	 * //response.then().assertThat().body(JsonSchemaValidator.
	 * matchesJsonSchema("API.Phase2.RestAssuredCucumber/Data Files/ProgramModule_PUTRequest_JSONValidation.json"
	 * ));
	 * 
	 * response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new
	 * File("./Data Files/ProgramModule_PUTRequest_JSONValidation.json")));
	 * logger.info("PUT Request schema validation is successful"); } else {
	 * logger.info("PUT Request schema validation is NOT successful"); } }
	 */

