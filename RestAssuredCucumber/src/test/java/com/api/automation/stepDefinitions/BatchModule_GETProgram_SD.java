package com.api.automation.stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.ExcelReader;
import com.api.auotmation.utilities.TestUtil;
import com.api.automation.base.BaseClass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BatchModule_GETProgram_SD extends BaseClass {

	String uri;
	public RequestSpecification request;
	Response response;
	String expectedMessage = null;
	String allBatchResponse = null;
	String programId = null;

	@Given("User creates GET Request for the LMS API endpoint for all batches")
	public void user_creates_GET_Request_for_the_LMS_API_endpoint_for_all_batches() {
		this.uri = Config.BatchMod_Get_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User sends HTTPS Request for all batches")
	public void user_sends_HTTPS_Request_for_all_batches() {
		response = this.request.get(this.uri);
		response.then().log().all();
	}

	@Then("User should get successfull message with status code {string}")
	public void user_should_get_successfull_message_with_status_code(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 200) {
			response.then().statusCode(Integer.parseInt(statuscode));
			response.then().assertThat().header("Connection", "keep-alive");
			List<Map> list = response.getBody().jsonPath().getList(".", Map.class);
			Map map = list.get(0);
			Integer batchId = (Integer) map.get("batchId");

			System.out.println("batchId = " + batchId);

			logger.info("Get Request to fetch all program data is successfull");
			JsonPath js = new JsonPath(response.asString());
			int size = js.getInt("data.size()");                         //added code to get the data size.
			System.out.println("No of Items in Program Data    "+size);}

	 else if (GetAllstatuscode == 404) {
		logger.info("Get Request unsuccessful");

		}
	}

	@Given("User creates request for Batch module with invalid base URL")
	public void user_creates_request_for_batch_module_with_invalid_base_url() {
		this.uri = Config.BatchMod_Get_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
	}

	@When("User sends GET request to batch module")
	public void user_sends_get_request_to_batch_module() {
		response = this.request.get(this.uri + "/" + "/");
		response.then().log().all();
	}

	@Then("Error message should display with status code {string}")
	public void error_message_should_display_with_status_code(String statuscode) {
		int GetAllstatuscode = response.getStatusCode();
		if (GetAllstatuscode == 404) {
			response.then().statusCode(Integer.parseInt(statuscode));
			// response.then().assertThat().header("Vary", "Access-Control-Request-Method");
			logger.info("Status code 404 received for GET all program with invalid URL");
		}

		else {
			logger.info("Get Request unsuccessful");

		}
	}

	@Given("User creates request for Batch module with valid base URL and valid path")
	public void user_creates_request_for_batch_module_with_valid_base_url_and_valid_path() {
		this.uri = Config.BatchMod_Get_URL + "/batchId/";
		this.request = RestAssured.given().header("Content-Type", "application/json"); 
	}

	@When("User sends GET request with valid batch ID from {string} and {int}")
	public void user_sends_get_request_with_valid_batch_id_from_and(String SheetName, Integer Rownumber) {
		//String batchId = getDataFromExcel(SheetName, Rownumber).get("batchId");

		Response batchResponse = this.request.get(Config.BatchMod_Get_URL);
		List<Map> list = batchResponse.getBody().jsonPath().getList(".", Map.class);
		if(list.size() > 0) {
			Map map = list.get(0);
			Integer batchId = (Integer) map.get("batchId");

			response = this.request
					.when()
					.get(uri + batchId)
					.then()
					.log().all().extract().response();
			allBatchResponse = "SUCCESS";
		} else {
	    	logger.info("batches do not exist");
		}
	}

	@Then("Request should be successfull with status code {string}")
	public void request_should_be_successfull_with_status_code(String statuscode) {
	    if("SUCCESS".equals(allBatchResponse)) {
			response.then().statusCode(Integer.parseInt(statuscode));
	    } else {
	    	logger.info("batches do not exist");
	    }
	}

	@Given("User creates request for Batch module with valid base URL and invalid batchId")
	public void user_creates_request_for_batch_module_with_valid_base_url_and_invalid_batchId() {
		this.uri = Config.BatchMod_Get_URL + "/batchId/";
		this.request = RestAssured.given().header("Content-Type", "application/json"); 

	}

	@When("User sends GET request with invalid batch ID from {string} and {int}")
	public void user_sends_get_request_with_invalid_batch_id_from_and(String sheetName, Integer rowNumber) {
		Map<String, String> rowData = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, sheetName, rowNumber);
		String batchId = rowData.get("batchId");
		expectedMessage = rowData.get("message");
		response = this.request
				.when()
				.get(uri + batchId)
				.then()
				.log().all().extract().response();
	}

	@Then("User receives {string} Not Found Status with message and boolean success details for invalid batchId")
	public void user_receives_Not_Found_Status_with_message_and_boolean_success_details_for_invalid_batchId(String statuscode) {
		response.then().statusCode(Integer.parseInt(statuscode));
		String message = response.getBody().jsonPath().get("message");
		message = message.trim();
		System.out.println("message = "+message);
		System.out.println("Expected Message = "+expectedMessage);
		Assert.assertEquals(true, expectedMessage.contains(message));

	}

	@Given("User creates request for Batch module with valid base URL and valid batch name")
	public void user_creates_request_for_batch_module_with_valid_base_url_and_valid_batch_name() {
		this.uri = Config.BatchMod_Get_URL + "/batchName/";
		this.request = RestAssured.given().header("Content-Type", "application/json"); 
	}

	@When("User sends GET request with valid batch Name from {string} and {int}")
	public void user_sends_get_request_with_valid_batch_name_from_and(String string, Integer int1) {
		Response batchResponse = this.request.get(Config.BatchMod_Get_URL);
		List<Map> list = batchResponse.getBody().jsonPath().getList(".", Map.class);
		if(list.size() > 0) {
			Map map = list.get(0);
			String batchName = (String) map.get("batchName");
			System.out.println("batchName = "+batchName);
			System.out.println("uri = "+uri);
			response = this.request
					.when()
					.get(uri + batchName)
					.then()
					.log().all().extract().response();
			allBatchResponse = "SUCCESS";
		} else {
	    	logger.info("batches do not exist");
		}

	}

	@Then("Request should be successfull with status code {string} for GET single batch Name")
	public void request_should_be_successfull_with_status_code_for_get_single_batch_name(String statuscode) {
	    if("SUCCESS".equals(allBatchResponse)) {
			response.then().statusCode(Integer.parseInt(statuscode));
			allBatchResponse = null;
	    } else {
	    	logger.info("batches do not exist");
	    }

	}

	@Given("User creates request for Batch module with valid base URL and invalid batch name")
	public void user_creates_request_for_batch_module_with_valid_base_url_and_invalid_batch_name() {
		this.uri = Config.BatchMod_Get_URL + "/batchName/";
		this.request = RestAssured.given().header("Content-Type", "application/json"); 

	}

	@When("User sends GET request with invalid batch Name from {string} and {int}")
	public void user_sends_get_request_with_invalid_batch_name_from_and(String sheetName, Integer rowNumber) {
		Map<String, String> rowData = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, sheetName, rowNumber);
		String batchName = rowData.get("batchName");
		expectedMessage = rowData.get("message");

		response = this.request
				.when()
				.get(uri + batchName)
				.then()
				.log().all().extract().response();

	}

	@Then("Request should be unsuccessfull with status code {string} for GET batch invalid Name")
	public void request_should_be_unsuccessfull_with_status_code_for_get_batch_invalid_name(String statuscode) {
		response.then().statusCode(Integer.parseInt(statuscode));
		String message = response.getBody().jsonPath().get("message");
		message = message.trim();
		System.out.println("message = "+message);
		System.out.println("Expected Message = "+expectedMessage);
		Assert.assertEquals(true, expectedMessage.contains(message));
		expectedMessage = null;

	}

	@Given("User creates request for Batch module with valid base URL and valid programId")
	public void user_creates_request_for_Batch_module_with_valid_base_URL_and_valid_programId() {
		this.uri = Config.BatchMod_Get_URL + "/program/";
		this.request = RestAssured.given().header("Content-Type", "application/json"); 

	}

	@When("User sends GET request with valid batch program ID from {string} and {int}")
	public void user_sends_get_request_with_valid_batch_program_id_from_and(String string, Integer int1) {
	    //Response programCreateResponse = TestUtil.createAndSendPostRequest(Config.programDeleteExcelFilePath, "createProgram", 0);
		Response batchResponse = this.request.get(Config.BatchMod_Get_URL);
		List<Map> list = batchResponse.getBody().jsonPath().getList(".", Map.class);
		if(list.size() > 0) {
			Map map = list.get(0);
		    Integer intProgramId = (Integer) map.get("programId");
			System.out.println("intProgramId = "+intProgramId);
			System.out.println("uri = "+uri);
			response = this.request
					.when()
					.get(uri + intProgramId)
					.then()
					.log().all().extract().response();

			allBatchResponse = "SUCCESS";
			programId = String.valueOf(intProgramId);
		} else {
	    	logger.info("batches do not exist");
		}

	}

	@Then("Request should be successfull with status code {string} for GET single batch program ID")
	public void request_should_be_successfull_with_status_code_for_get_single_batch_program_id(String statuscode) {
		response.then().statusCode(Integer.parseInt(statuscode));
	    if("SUCCESS".equals(allBatchResponse)) {
			List programIds = response.getBody().jsonPath().get("programId");
			System.out.println("programIds = " + programIds);
			assertEquals(programId, programIds.get(0).toString());
	    }else {
	    	logger.info("batches do not exist");
		}

	}

	@Given("User creates request for Batch module with valid base URL and invalid programId")
	public void user_creates_request_for_Batch_module_with_valid_base_URL_and_invalid_programId() {
		this.uri = Config.BatchMod_Get_URL + "/program/";
		this.request = RestAssured.given().header("Content-Type", "application/json"); 

	}

	@When("User sends GET request with invalid batch program ID from {string} and {int}")
	public void user_sends_get_request_with_invalid_batch_program_id_from_and(String sheetName, Integer rowNumber) {
		Map<String, String> rowData = TestUtil.getRowDataFromExcel(Config.programDeleteExcelFilePath, sheetName, rowNumber);
		String tempProgramId = rowData.get("programId");
		expectedMessage = rowData.get("message");
		response = this.request
				.when()
				.get(uri + tempProgramId)
				.then()
				.log().all().extract().response();


	}

	@Then("Request should be successfull with status code {string} for GET single batch program invalidID")
	public void request_should_be_successfull_with_status_code_for_get_single_batch_program_invalid_id(String statuscode) {
		response.then().statusCode(Integer.parseInt(statuscode));
		String message = response.getBody().jsonPath().get("message");
		message = message.trim();
		System.out.println("message = "+message);
		System.out.println("Expected Message = "+expectedMessage);
		Assert.assertEquals(true, expectedMessage.contains(message));
		expectedMessage = null;

	}

//	@And("Data size should be calculated")
//	public void dataSize(){
//		@Then("Get the size of Data")
//		public void get_the_size_of_data() {
//			JsonPath js = new JsonPath(response.asString());
//			int size = js.getInt("data.size()");
//			System.out.println("No of Items in Program Data    "+size);
//	
//	}
//}
}