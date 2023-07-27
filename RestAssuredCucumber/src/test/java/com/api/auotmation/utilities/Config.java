package com.api.auotmation.utilities;

public class Config {

	// public static final String
	// base_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	public static final String base_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	public static final String invalid_base_URL = "https://lms-api-hackathon-aug2023-930a8b0f895d.herokuapp.com/lms";

	// Program Module - POST Request - HARPREET
	// public static final String
	// programMod_POST_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/saveprogram";
	public static final String Base_Valid_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	public static final String Base_Invalid_URL = "https://lms-api-hackathon-2023-930a8b0f895d.herokuapp.com/lms";

	// public static final String
	// programMod_POST_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/saveprogram";

	public static final String GetAllUsers_valid_endpoint = "/users/users";
	public static final String GetAllUsers_invalid_endpoint = "/user/users";

	public static final String GetUserByID_valid_endpoint = "/users/users/";
	public static final String GetUserByID_invalid_endpoint = "/user/users/";

	public static final String GetAllUsersWithRoles_valid_endpoint = "/users/users/roles";
	public static final String GetAllUsersWithRoles_invalid_endpoint = "/user/users/roles";

	public static final String GetAllStaff_valid_endpoint = "/users/users/getAllStaff";
	public static final String GetAllStaff_invalid_endpoint = "/user/users/getAllStaff";

	public static final String PostAssignment_valid_endpoint = "/assignments";
	public static final String PostAssignment_invalid_endpoint = "/assignment";

	// Program Module - PUT Request - RAJI
	// public static final String programMod_PUT_ByProgId_URL="/putprogram/11333";
	public static final String programMod_PUT_ByProgId_URL = "/putprogram/";
	public static final String programMod_PUT_ByInvalidProgId_URL = "/putprogram/89898989";
	public static final String programMod_PUT_ByName_URL = "/program/";
	public static final String programMdod_PUT_ByName_URL = "/program/July23-AssuredNinjas-SDE-088";
	public static final String programMdod_PUT_ByInvalidName_URL = "/program/July23-AssuredNinjaas-SDE-088";
	public static final String programMod_PUT_GetSingleProgram_URL = " https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/programs/";

	public static final String userMod_POST_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/users/users/roleStatus";
	public static final String userMod_invalid_POST_URL = "https://lms-api-hackathon-aaa2023-930a8b0f895d.herokuapp.com/lms/users/users/roleStatus";
	public static final String excelFilePath = "./Data Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";
	public static final String jsonFilePath_userMod_POST_valid = "Data Files\\UserModule_POSTAdmin.json";
	public static final String userMod_PUT_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	public static final String userMod_PUT_byUserId_URL="/users/users/";
	public static final String userMod_PUT_byUserId_invalid_URL="/users/users/U907878787878";
	
	
	// public static final String
	// excelFilePath="/API.Phase2.RestAssuredCucumber/Data
	// Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";

	// Program Module - GET Request - JASS--------------------------------------
	public static final String programMod_GET_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/allPrograms";
	public static final String programMod_GET_URL_forexcel = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/programs";
	// public static final String
	// excelFilePath="/API.Phase2.RestAssuredCucumber/Data
	// Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";
	public static final String BASE_Invalid_URL = "https://lms-backend.herokuapp.com/lms";
	public static final String Valid_BatchPut = "/batches/2442";
	public static final String Valid_BatchPut6495 = "/batches/6495";
	public static final String Invalid_BatchPut = "/batchessssssss/1456"; // not mine
	public static final String Invalid_Batch = "/batches/6011"; // mine

	// end points PUT submission by Jass
	// End poin for delete submissionId created by Jass
	// public static final String Submission_EndPoint="/assignmentsubmission";

	public static final String Resubmit_Assignment = "/assignmentsubmission/2009";
	public static final String Invalid_Submission = "/assignmentsubmission/200*";

//	public static final String base_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
//	public static final String programMod_POST_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/saveprogram";		
//	public static final String programMod_GET_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/allPrograms";  

//	public static final String programMod_GET_URL_forexcel="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/programs";  
//	public static final String excelFilePath="./Data Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";
	// public static final String
	// excelFilePath="/API.Phase2.RestAssuredCucumber/Data
	// Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";

//	public static final String BASE_Invalid_URL = "https://lms-backend.herokuapp.com/lms";
//	public static final String Valid_BatchPut = "/batches/2442";
//	public static final String Valid_BatchPut6495 = "/batches/6495";
//	public static final String Invalid_BatchPut = "/batchessssssss/1456"; //not mine
//	public static final String Invalid_Batch="/batches/6011"; //mine

	// EndPoint for Assignment Module for Get request created by Jass
	public static final String Valid_EndPoint_GetAllAssignment = "/assignments"; // sucessfully working
	public static final String Valid_AssignmentID = "/assignments/3197";// working
	public static final String Valid_BatchID = "/assignments/batch/6401";// working

	// End poin for delete submissionId created by Jass
	public static final String Submission_EndPoint = "/assignmentsubmission";

	// Program Module - DELETE Request -Priyanka
	public static final String BatchMod_Get_URL = base_URL + "/batches";
	public static final String basePathDelByProgramId = "/deletebyprogid";
	public static final String basePathDelByProgramName = "/deletebyprogname";
	public static final String basePathDelByAssignmentID = "/assignments";
	public static final String programDeleteExcelFilePath = "./Data Files/ProgramDeleteData.xlsx";
	public static final String programMod_POST_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/saveprogram";

	public static final String assignmentMod_POST_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments";

	// Ahila

	// public static final String
	// base_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	// public static final String
	// programMod_POST_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/saveprogram";
	// public static final String excelFilePath="./Data
	// Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";
	// public static final String
	// excelFilePath="/API.Phase2.RestAssuredCucumber/Data
	// Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";
	// public static final String DeleteProgram_URL =
	// "https://lms-backend-service.herokuapp.com/lms/deletebyprogid";
	public static final String BATCHMOD_DELETE_URL = base_URL + "/batches/{batchId}";
	public static final String USERMOD_DELETE_URL = base_URL + "/users/users/{userId}";
	public static final String SUBMISSIONMOD_GET_URL = base_URL + "/assignmentsubmission";
	public static final String SUBMISSIONMOD_GETGRADE_ASSIGNMENTID = base_URL
			+ "/assignmentsubmission/getGrades/{assignmentId}";
	public static final String SUBMISSIONMOD_GETGRADE_STUDENTID = base_URL
			+ "/assignmentsubmission/getGradesByStudentId/{studentID}";
	public static final String SUBMISSIONMOD__GETSUBMISSION_BATCHID = base_URL
			+ "/assignmentsubmission/grades/{batchID}";
	public static final String SUBMISSIONMOD_GETSUBMISSION_USERID = base_URL + "/assignmentsubmission/student/{userId}";

	// Shweta

	// public static final String
	// base_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	// public static final String
	// programMod_POST_URL="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/saveprogram";
	public static final String BatchModule_POST_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/batches";
	// public static final String excelFilePath="./Data
	// Files/API-Phase2-RESTAssuredCucumber-Data.xlsx";
	public static final String assignment_PUT_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments/4017";
	public static final String assignment_PUT_Invalid_URL = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments//abc";

}
