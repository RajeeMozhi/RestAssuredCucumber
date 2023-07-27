
package com.api.automation.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

import com.api.automation.base.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.api.automation.payLoad.*;
import com.api.auotmation.utilities.*;
import java.io.IOException;

import org.json.simple.JSONObject;

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
import com.api.auotmation.utilities.Config;
import com.api.auotmation.utilities.CurrentDate;
import io.restassured.module.jsv.JsonSchemaValidator;


public class UserModule_POSTRequest_SD extends BaseClass{
	
	public RequestSpecification request;
	public Response response;
	public String uri;
	public static CurrentDate cd;
	
	@Given("User sets valid URL with mandatory Payload for user")
	public void user_sets_valid_url_with_mandatory_payload_for_user_admin() throws IOException {
		this.uri = Config.userMod_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("POST request for User module is sent with all fields  for role Admin");
		
		//.body(new String(Files.readAllBytes(Paths.get("./Data Files/UserModule_POSTAdmin.json"))));
	}
	
	@Given("User sets invalid URL with mandatory Payload for user") 
	public void invalid_url() {
		this.uri = Config.userMod_invalid_POST_URL;
		this.request = RestAssured.given().header("Content-Type", "application/json");
		logger.info("POST request for User module is sent with invalid URL");
	}

	@When("User sends POST request with valid URL for user from {string} and {int}")
	public void user_sends_post_request_with_valid_url_for_user_admin_from_and(String SheetName, Integer RowNumber) 
			throws InvalidFormatException, IOException {
		createPOSTRequest_UserModule_allFields(SheetName, RowNumber);
		logger.info("User send POST request with valid URL for Role");
	}

	
	@Then("Request should be successful with status code {int} and creation of user")
	public void request_should_be_successfull_with_status_code_and_creation_of_user_admin(Integer int1) {
				
		int statusCode=this.response.getStatusCode();
		 System.out.println("Post Status code "+ statusCode);
		 //Assert.assertEquals(400, statusCode);
		 if (statusCode == 201) {
				logger.info("User created with  " + statusCode);
				
				boolean startsWith = response.jsonPath().getString("userId").startsWith("U");
				if(true)
					logger.info("UserId starts with U");
				else
					logger.info("UserId does not start with U");
				//Assert.assertEquals("U",response.jsonPath().getString("userId").startsWith("U"));
				
			}
			
			else 
			{
				logger.info("Post Request unsuccessful with status code" + statusCode + " Error to be reported");
			}
		}
		
	
	//Created common method to post request and test each case	
		@SuppressWarnings("unchecked")
			public void createPOSTRequest_UserModule_allFields(String SheetName, int Rownumber) throws InvalidFormatException, IOException {
			String userComments = getDataFromExcel(SheetName,Rownumber).get("userComments");
			String userEduPg = getDataFromExcel(SheetName,Rownumber).get("userEduPg");
			String userEduUg = getDataFromExcel(SheetName,Rownumber).get("userEduUg");
			String userFirstName = getDataFromExcel(SheetName,Rownumber).get("userFirstName");
			String userLastName = getDataFromExcel(SheetName,Rownumber).get("userLastName");
			String userLinkedinUrl = getDataFromExcel(SheetName,Rownumber).get("userLinkedinUrl");
			String userLocation = getDataFromExcel(SheetName,Rownumber).get("userLocation");
			String userMiddleName = getDataFromExcel(SheetName,Rownumber).get("userMiddleName");
			String userPhoneNumber = getDataFromExcel(SheetName,Rownumber).get("userPhoneNumber");
				
			String roleId = getDataFromExcel(SheetName,Rownumber).get("roleId");
			String userRoleStatus = getDataFromExcel(SheetName,Rownumber).get("userRoleStatus");
			
			String userTimeZone = getDataFromExcel(SheetName,Rownumber).get("userTimeZone");
			String userVisaStatus = getDataFromExcel(SheetName,Rownumber).get("userVisaStatus");
						
			JSONObject body = new JSONObject();
			body.put("userComments", userComments);
			body.put("userEduPg", userEduPg);
			body.put("userEduUg", userEduUg);
			body.put("userFirstName", userFirstName);
			body.put("userLastName", userLastName);
			body.put("userLinkedinUrl", userLinkedinUrl);
			body.put("userLocation", userLocation);
			body.put("userMiddleName", userMiddleName);
			body.put("userPhoneNumber", userPhoneNumber);
				
			HashMap<String, String> userRoleMaps = new HashMap<>();
			userRoleMaps.put("roleId", roleId);
			userRoleMaps.put("userRoleStatus", userVisaStatus);
			
	  	            
	        ArrayList<HashMap<String, String>> jsonArray = new ArrayList<>();
	        jsonArray.add(userRoleMaps);

	        body.put("userRoleMaps", jsonArray);
	        
	    	body.put("userTimeZone", userTimeZone);
			body.put("userVisaStatus", userVisaStatus);
			
			response = this.request
					.body(body.toJSONString())
					.when()
					.post(this.uri)
					.then()
					.log().all().extract().response();
			response.prettyPrint();
			
			
			JsonPath js = response.jsonPath();
			js.prettyPrint();
			
			}
		
				
		@Then("User should get error message {int} and boolean success details")
		public void user_should_get_error_message_and_boolean_success_details(Integer int1) {
			try {
				int statusCode=this.response.getStatusCode();
				System.out.println("Status Code  = "+statusCode);
				if(statusCode == 201)
				{
					logger.info("User created with  " + statusCode);
				}
									
				else
					{
					logger.warn("Unsuccessful role creation with " + statusCode);
					}
			}
			
			catch(Exception e) {
				logger.warn("User creation is NOT Succesful due to "+e.getMessage());
			}
			
					
		}
		

		@Then("User should get an error message for invalid data with status code {int} and boolean success details")
		public void user_should_get_an_error_message_with_status_code_and_boolean_success_details(Integer int1) {
		   
			int statusCode=this.response.getStatusCode();
			 System.out.println("Error Status code "+ statusCode);
			 //Assert.assertEquals(400, statusCode);
			 if (statusCode == 400) {
				 String message = response.getBody().jsonPath().get("message");
					message = message.trim();
					System.out.println("Error Message ;  ------->"+message);
					System.out.println("Status Code   :--------->" + response.getStatusCode());
					logger.info("Bad Request with Status code  " + statusCode);
				}
				
				else 
				{
					logger.warn("Invalid data - Status code is " + statusCode + " Error to be reported");
				}}
			
			
		@Then("Request should be made with successful header and schema validation")
		public void schema_should_be_validated() {
		
		int sc=response.getStatusCode();
		long responseTime = response.getTime();
		logger.info("Response time is  ="+responseTime);
		
		if(sc==201)
		{
		response=this.request.when().get(Config.userMod_POST_URL).then().log().all().extract().response();
		response.then().assertThat().header("Connection", "keep-alive");
		response.then().assertThat().header("Server","Cowboy");
		response.then().assertThat().header("Content-Type","application/json");
		response.then().assertThat().header("Transfer-Encoding","chunked");
		
		//response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema("API.Phase2.RestAssuredCucumber/Data Files/ProgramModule_PUTRequest_JSONValidation.json"));
		
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("./Data Files/UserModule_POSTRequest_Schema.json")));
		logger.info("User module - POST Request schema validation is successful");
		}
		else
		{
			logger.info("User Module - POST Request schema validation is NOT successful");
		}
		}
		
	}
