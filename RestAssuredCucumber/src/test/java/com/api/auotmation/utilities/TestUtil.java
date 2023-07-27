package com.api.auotmation.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestUtil {

	@SuppressWarnings("unchecked")
	public static Response createAndSendPostRequestForProgram()  {
		String uri = Config.programMod_POST_URL;
		RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");		
		Map<String, String> rowData = getRowDataFromExcel(Config.programDeleteExcelFilePath, "createProgram",0);
		String programName = rowData.get("programName") +RandomStringUtils.randomNumeric(3);
		String programDescription = rowData.get("programDescription");
		String programStatus = rowData.get("programStatus");
		String creationTime = Timestamp();
		String lastModTime = Timestamp();
	
		JSONObject body = new JSONObject();
		body.put("programName", programName);
		body.put("programDescription", programDescription);
		body.put("programStatus", programStatus);
		body.put("creationTime", creationTime);
		body.put("lastModTime", lastModTime);
		
		Response response = request
				.body(body.toJSONString())
				.when()
				.post(uri)
				.then()
				.log().all().extract().response();	
		return response;
		
	}
	
	
	public static Map<String,String> getRowDataFromExcel(String excelFilePath , String SheetName, int Rownumber)   {
		ExcelReader excelReader = new ExcelReader();
		Map<String, String> Data = null;
		try {
			Data = excelReader.getData(excelFilePath, SheetName).get(Rownumber);
			//Data = excelReader.getData(Config.programDeleteExcelFilePath, SheetName).get(Rownumber);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error reading tests data file", e);
		}
		return Data;
	}

	public static String Timestamp() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String timestamp = df.format(new Date());
		return (timestamp);
	}

	//assignment module_DeleteProg
	@SuppressWarnings("unchecked")
	public static Response createAndSendPostRequestForAssignment()  {
		String uri = Config.assignmentMod_POST_URL;
	
		RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");		
		Map<String, String> rowData = getRowDataFromExcel(Config.programDeleteExcelFilePath, "createAssignment",0);
		String assignmentName = rowData.get("assignmentName") +RandomStringUtils.randomNumeric(3);
		String assignmentDescription = rowData.get("assignmentDescription");
		String dueDate = rowData.get("dueDate");
		String graderId = rowData.get("graderId");
		String batchID = rowData.get("batchID");
		String createdBy = rowData.get("createdBy");

		JSONObject body = new JSONObject();
		body.put("assignmentName", assignmentName);
		body.put("assignmentDescription", assignmentDescription);
		body.put("dueDate", dueDate);
		body.put("graderId", graderId);
		body.put("createdBy", createdBy);
		body.put("batchId", batchID);
		
		System.out.println("createAndSendPostRequestForAssignment called \n" + body.toJSONString());
		
		Response response = request
				.body(body.toJSONString())
				.when()
				.post(uri)
				.then()
				.log().all().extract().response();	
		return response;
		
	}
}
