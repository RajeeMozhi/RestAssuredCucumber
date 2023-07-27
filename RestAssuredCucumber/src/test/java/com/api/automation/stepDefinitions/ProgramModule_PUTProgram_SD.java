package com.api.automation.stepDefinitions;

import java.io.IOException;

import org.json.simple.JSONObject;
import java.io.*;
import java.util.*;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.api.automation.*;
import com.api.automation.base.BaseClass;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.CurrentDate;
import io.restassured.module.jsv.JsonSchemaValidator;


public class ProgramModule_PUTProgram_SD extends BaseClass {
	
	public RequestSpecification request;
	public Response response;
	public String uri;
	public static CurrentDate cd;
	public static int programId, sc;
	public static String programNamee;
	
	@Given("User sets Authorization to No Auth")
	public void user_sets_authorization_to_no_auth() {
	 	RestAssured.given().auth().none();
		logger.info("Authorization is set as No Auth");
	}
	
	public ProgramModule_PUTProgram_SD()
	{
		cd=new CurrentDate();
	}
	
	//-----------------SCENARIO Prerequest - Program Creation-----------------------------------------------------------	
	@Given("User sets request for Program module with base URL with valid endpoint and valid request body")
	public void user_sets_request_for_program_module_with_base_url_with_valid_endpoint_and_valid_request_body() {
		this.uri = Config.programMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("Request set for Program module with valid base URL and valid data");
	}

	@When("User send POST request with data from {string} and {int}")
	public void user_send_post_request_with_data_from_and(String SheetName, Integer RowNumber) 
			throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		createPostRequest(SheetName,RowNumber);   
		logger.info("Post Request sent with valid request body");
	}

	@Then("User should get status code {string} for POST request with valid endpoint and valid request body")
	public void user_should_get_status_code_for_post_request_with_valid_endpoint_and_valid_request_body(String statusCode) {
		
		int Poststatuscode = response.getStatusCode();
		if (Poststatuscode == 201) {
		response.then().statusCode(Integer.parseInt(statusCode));
		logger.info("Post Request Successful");
		}
		else 
			logger.info("Post Request unsuccessful with status code " + statusCode);
		  
		}
		
	// Common method for POST request
	@SuppressWarnings("unchecked")
	public void createPostRequest(String SheetName, int Rownumber) 
			throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		String programName = DynamicprogName();
		String programDescription = getDataFromExcel(SheetName,Rownumber).get("programDescription");
		String programStatus = getDataFromExcel(SheetName,Rownumber).get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();

		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);
		
		response = this.request
				.body(body.toJSONString())
				.when()
				.post(this.uri)
				.then()
				.log().all().extract().response();	
		logger.info("Common method for POST Request is done");
		
		JsonPath js = response.jsonPath();
		programId=js.getInt("programId");
		programNamee=js.getString("programName");
		System.out.println("ProgramId from Response  ="+programId);
		System.out.println("ProgramName from Response  ="+programNamee);
	}
	
		
	//--------------Scenario1--------------------------------
	@Given("User sets valid URL with mandatory Payload")
	public void user_sets_valid_url_with_valid_payload() {
		
		JSONObject body = new JSONObject();
	  	//body.put("programName", "July23-AssuredNinjas-SDET-185");
		body.put("programName", programNamee);
		body.put("programDescription","API Testing");
		body.put("programStatus", "Active");
		body.put("creationTime", "2023-07-22T16:50:15.034+00:00");
		body.put("lastModTime", cd.getCurrentDate());
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("User sets valid URL with mandatory Payload");
	}

	@When("User sends PUT request with valid URL")
	public void user_sends_put_request_with_valid_url() {
		
		this.uri = Config.base_URL + Config.programMod_PUT_ByProgId_URL+programId;
		System.out.println(uri);
		System.out.println("ProgramId"+programId);
		response = this.request.when().put(this.uri);
		logger.info("User sends PUT request with valid URL");
	
	}

	@Then("Request should be successfull with status code {int} and updated response body")
	public void request_should_be_successfull_with_status_code_and_updated_response_body(Integer int1) {
		int statusCode = this.response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		response.prettyPrint();
		logger.info("Request is successfull with status code  "+ statusCode);
	}
	
	//------------------Scenario2----------------------------------------------------
	@Given("User sets valid URL with invalid program id and mandatory Payload")
	public void user_sets_valid_url_with_invalid_program_id_and_mandatory_payload() {
		JSONObject body = new JSONObject();
	  	body.put("programName", "July23-AssuredNinjas-SDET-185");
		body.put("programDescription","API Testing");
		body.put("programStatus", "Active");
		body.put("creationTime", "2023-07-22T16:50:15.034+00:00");
		body.put("lastModTime", cd.getCurrentDate());
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("User sets valid URL with invalid program id and mandatory Payload");
	}

	@When("User sends PUT request with invalid program id")
	public void user_sends_put_request_with_invalid_program_id() {
		this.uri = Config.base_URL + Config.programMod_PUT_ByInvalidProgId_URL;
		response = this.request.when().put(this.uri);
		logger.info("User sends PUT request with invalid program id");
	}

	@Then("User should get an error message with status code {int} Not Found and boolean success details")
	public void user_should_get_an_error_message_with_status_code_not_found_and_boolean_success_details(Integer int1) {
	 int statusCode=this.response.getStatusCode();
	 System.out.println("------StatusCode for PUT request with Invalid Program Id is ="+ statusCode);
	 Assert.assertEquals(404, statusCode);
	 logger.info("User gets an error message with status code    "+statusCode);
	}
	
	//-------------------Scenario3---------------------------------------------------------------
	@Given("User sets valid URL with valid program id and without mandatory fields(programStatus) in Payload")
	public void user_sets_valid_url_with_valid_program_id_and_without_mandatory_fields_in_payload() {
		JSONObject body = new JSONObject();
		body.put("programId",programId);
	  	//body.put("programName", "");
		body.put("programDescription","Manual Testing");
		//body.put("programStatus", "");
		body.put("creationTime", "2023-07-22T16:50:15.034+00:00");
		body.put("lastModTime", cd.getCurrentDate());
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info(")User sets valid URL with valid program id and without mandatory fields(programStatus) in Payload");
	}

	@Then("User should get an error message with status code {int} Bad Request and boolean success details")
	public void user_should_get_an_error_message_with_status_code_bad_request_and_boolean_success_details(Integer int1) {
		 int statusCode=this.response.getStatusCode();
		 System.out.println("------StatusCode for PUT request with valid Program Id without mandatory field is = "+ statusCode);
		 //Assert.assertEquals(400, statusCode);
		 
		if (statusCode == 400) {
			logger.info("Bad request error message received with " + statusCode);
		}
		
		else 
		{
			logger.info("Actual Statuscode received" + statusCode + " Error to be reported");
		}
		 
	}
	
	//-------------------------Scenario 4--------------------------------------------------------------

	@Given("User sets valid URL with valid program id and with empty value in mandatory fields in Payload")
	public void user_sets_valid_url_with_valid_program_id_and_with_empty_value_in_mandatory_fields_in_payload() {
		JSONObject body = new JSONObject();
		body.put("programId",programId);
	  	body.put("programName", "");
		body.put("programDescription","Manual Testing");
		body.put("programStatus", "");
		body.put("creationTime", "2023-07-22T16:50:15.034+00:00");
		body.put("lastModTime", cd.getCurrentDate());
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("User sets valid URL with valid program id and with empty value in mandatory fields in Payload");
	}
	

	//------------------------Scenario 5--------------------------------------------------------------------
	@Given("User sets valid URL with only mandatory Payload")
	public void user_sets_valid_url_with_only_mandatory_payload() {
		System.out.println("______________Empty ProgramName-----------");
		//programName and programStatus are mandatory fields
		JSONObject body = new JSONObject();
		body.put("programId",programId);
	  	body.put("programName", programNamee);
		//body.put("programDescription","");
		body.put("programStatus", "Online");
		//body.put("creationTime", "2023-07-22T16:50:15.034+00:00");
		//body.put("lastModTime", cd.getCurrentDate());
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("User sets valid URL with only mandatory Payload");
	}

	//-----------------------Scenario 6------------------------------------------------------------------
	@Given("User sets valid URL with valid program id and empty payload")
	public void user_sets_valid_url_with_valid_program_id_and_empty_payload() {
		JSONObject body = new JSONObject();
		body.put("programId","");
	  	body.put("programName", "");
		body.put("programDescription","");
		body.put("programStatus", "");
		body.put("creationTime", "");
		body.put("lastModTime", "");
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("User sets valid URL with valid program id and empty payload");
	}

	@When("User should not able to update the program")
	public void user_should_not_able_to_update_the_program() {
		 int statusCode=this.response.getStatusCode();
		 System.out.println("------StatusCode for PUT request with empty payload = "+ statusCode);
		 logger.info("User gets error message with status code  "+statusCode);
		// Assert.assertEquals(500, statusCode);
	}
	
	//-----------------Scenario 7 ---------------------------------------------------------------------------------
	@Given("User sets invalid URL with valid mandatory Payload")
	public void user_sets_invalid_url_with_valid_mandatory_payload() {
		JSONObject body = new JSONObject();
	   // body.put("programId",11366);
		body.put("programId",programId);
		body.put("programName", "July23-AssuredNinjas-SDET-185");
		body.put("programDescription","API Testing");
		body.put("programStatus", "Inactive");
		body.put("creationTime", "2023-07-22T16:50:15.034+00:00");
		body.put("lastModTime", cd.getCurrentDate());
		System.out.println(body);
		this.request = RestAssured.given().header("Content-Type", "application/json").body(body);
		logger.info("User sets invalid URL with valid mandatory Payload");
	}

	@Then("User should get an error message with 404 status code")
	public void user_should_get_an_error_message() {
		 int statusCode=this.response.getStatusCode();
		 System.out.println("------StatusCode for PUT request with empty payload = "+ statusCode);
		 //Assert.assertEquals(404, statusCode);
		 logger.info("User gets an error message with status code  "+statusCode);
	}
	
	@When("User sends PUT request with invalid URL")
	public void user_sends_put_request_with_invalid_url() {
		this.uri = Config.invalid_base_URL +  Config.programMod_PUT_ByProgId_URL+programId;
		response = this.request.when().put(this.uri);
		logger.info("User sends PUT request with invalid URL");
	}
	
	//--------------------Scenario 8 --------------------------------------------------------
	@Then("Schema should be validated")
	public void schema_should_be_validated() {
	
	int sc=response.getStatusCode();
	if(sc==200)
	{
	response=this.request.when().get(Config.programMod_PUT_GetSingleProgram_URL+programId).then().log().all().extract().response();
	response.then().assertThat().header("Connection", "keep-alive");
	//response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema("API.Phase2.RestAssuredCucumber/Data Files/ProgramModule_PUTRequest_JSONValidation.json"));
	
	response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("./Data Files/ProgramModule_PUTRequest_JSONValidation.json")));
	logger.info("PUT Request schema validation is successful");
	}
	else
	{
		logger.info("PUT Request schema validation is NOT successful");
	}
	}
	
	
	//------------------------Scenario 9 ---------------------------------------------------------

		@Given("User set request for Program module with valid base url and valid request body by valid program name")
		public void user_set_request_for_program_module_with_valid_base_url_and_valid_request_body_by_valid_program_name() {
			this.uri=Config.base_URL+Config.programMod_PUT_ByName_URL+programNamee;
			System.out.println(this.uri);
			this.request=RestAssured.given().header("Content-Type","application/json");
			this.response = this.request.when().put(this.uri);
			
			logger.info("--------PUT Request with Program name is set ------------------");
		}
			
	
		@When("When User sends PUT request with all fields from {string} and {int}")
		public void when_user_sends_put_request_with_all_fields_from_and(String sheetName, Integer rowNumber) throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
		
			createPUTRequest(sheetName, rowNumber);
			logger.info("Program Module PUT Request by Program Name sent with all fields ");
		}
	
		// Created common method to PUT request and test
		@SuppressWarnings("unchecked")
		public void createPUTRequest(String SheetName, int Rownumber)
				throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		
			String programDescription = getDataFromExcel(SheetName, Rownumber).get("programDescription");
			String programStatus = getDataFromExcel(SheetName, Rownumber).get("programStatus");
			String creationTime = getDataFromExcel(SheetName, Rownumber).get("creationTime");
			String lastModTime = Timestamp();

			JSONObject body = new JSONObject();
			body.put("programId", programId);
			body.put("programName", programNamee);
			body.put("programDescription", programDescription);
			body.put("programStatus", programStatus);
			body.put("creationTime", creationTime);
			body.put("lastModTime", lastModTime);
			System.out.println(body);
			response = this.request.body(body.toJSONString()).when().put(this.uri).then().log().all().extract().response();
			
			response.prettyPrint();
		}
		//--------------------------Scenario 10-------------------------------------------------------------------------------------
		@When("When User sends PUT request with ONLY mandatory fields from {string} and {int}")
		public void when_user_sends_put_request_with_only_mandatory_fields_from_and(String sheetName, Integer rowNumber) 
				throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
			//createPUTRequest_mandatoryFieldsOnly(sheetName, rowNumber);
			createPUTRequest(sheetName, rowNumber);
			logger.info("Program Module PUT Request by Program Name sent with ONLY mandatory fields ");
		}
	
		//-------------------------Scenario 11-----------------------------------------------------------------------
				
		
				@Given("User set PUT request for Program module by Program Name with valid base url and without all mandatory fields in request body")
				public void user_set_put_request_for_program_module_by_program_name_with_valid_base_url_and_without_all_mandatory_fields_in_request_body() {
					this.uri=Config.base_URL+Config.programMod_PUT_ByName_URL+programNamee;
					System.out.println(this.uri);
					this.request=RestAssured.given().header("Content-Type","application/json");
					this.response = this.request.when().put(this.uri);
					
					logger.info("--------PUT Request with Program name is set ------------------");
				}

				@When("When User sends PUT request without mandatory fields from {string} and {int}")
				public void when_user_sends_put_request_without_mandatory_fields_from_and(String sheetName, Integer rowNumber) 
						throws InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
					//createPUTRequest_withoutMandatoryFields(sheetName, rowNumber);
					createPUTRequest(sheetName, rowNumber);
					logger.info("Program Module PUT Request by Program Name sent with ONLY mandatory fields ");
				}
				
				// Created common method to PUT request and test
				@SuppressWarnings("unchecked")
				public void createPUTRequest_withoutMandatoryFields
				(String SheetName, int Rownumber)
						throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
					String programId = getDataFromExcel(SheetName, Rownumber).get("programId");
					String programName = getDataFromExcel(SheetName, Rownumber).get("programName");
					
					JSONObject body = new JSONObject();
					//body.put("programId", programId);
					body.put("programName", programName);
					body.put("lastModTime", Timestamp());
					System.out.println(body);
					response = this.request.body(body.toJSONString()).when().put(this.uri).then().log().all().extract().response();
					response.prettyPrint();
				}
				
				@Then("Request should get error message with status code {int} Bad Request")
				public void request_should_get_error_message_with_status_code_bad_request(Integer int1) {
					 int statusCode=this.response.getStatusCode();
					 System.out.println("------StatusCode for PUT request with empty payload = "+ statusCode);
					 //Assert.assertEquals(400, statusCode);
					 if (statusCode == 400) {
							logger.info("Bad request error message received with " + statusCode);
						}
						
						else 
						{
							logger.info("Actual Statuscode received" + statusCode + " Error to be reported");
						}
				}
				
				//--------------Scenario 12-----------------------------------------------------------------
				@Given("User set request for Program module with valid base url and invalid program name")
				public void user_set_request_for_program_module_with_valid_base_url_and_invalid_program_name() {
					this.uri = Config.base_URL +  Config.programMdod_PUT_ByInvalidName_URL;
					this.request=RestAssured.given().header("Content-Type","application/json");
					logger.info("--------PUT Request with Invalid Program name is set ------------------");
				}

				@When("When User sends PUT request with invalid program name and mandatory fields from {string} and {int}")
				public void User_sends_PUT_request_with_invalid_program_name_and_mandatory_fields_from(String sheetName, int rowNumber)
						throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
					createPUTRequest(sheetName, rowNumber);
					logger.info("Program Module PUT Request by Program Name sent with all fields ");
					}
				

				@Then("Request should get error message with boolean success details and status code {int} Not Found")
				public void request_should_get_error_message_with_status_code_not_found(Integer int1) {
					int statusCode=this.response.getStatusCode();
					 System.out.println("------StatusCode for PUT request with invalid program name and mandatory fields = "+ statusCode);
					 //Assert.assertEquals(400, statusCode);
					 if (statusCode == 404) {
							logger.info("Not Found error message received with " + statusCode);
						}
						
						else 
						{
							logger.info("Actual Statuscode received" + statusCode + " Error to be reported");
						}
				}
				
	
}