package com.api.automation.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"./src/test/resources/Features"
							/*"./src/test/resources/Features/ProgramModule_PUTProgram.feature",
							"./src/test/resources/Features/ProgramModule_GETProgram.feature",
							"./src/test/resources/Features/ProgramModule_DELETEProgram.feature",
							"./src/test/resources/Features/BatchModule_PUTBatch.feature",
							"./src/test/resources/Features/BatchModule_GetProgram.feature",
							"./src/test/resources/Features/UserModule_GETUser.feature",
							"./src/test/resources/Features/UserModule_POSTRequest.feature",
							"./src/test/resources/Features/AssignmentModule_POSTAssignment.feature",
							"./src/test/resources/Features/BatchModule_PUTBatch.feature",		
							"./src/test/resources/Features/BatchModule_DeleteBatch.feature",
							"./src/test/resources/Features/UserModule_POSTRequest.feature",
							"./src/test/resources/Features/UserModule_PUTRequest.feature",
							"./src/test/resources/Features/UserModule_DeleteUser.feature",
							"./src/test/resources/Features/SubmissionModule_DELETEProgram.feature",
							"./src/test/resources/Features/SubmissionModule_GetRequest.feature",
							"./src/test/resources/Features/SubmissionModule_POSTAssignment.feature",
							"./src/test/resources/Features/SubmissionModule_PUTSubmission.feature",
							"./src/test/resources/Features/AssignmentModule_DELETEProgram.feature",
							"./src/test/resources/Features/AssignmentModule_GETProgram.feature",
							"./src/test/resources/Features/AssignmentModule_PUTSubmission.feature",
							"./src/test/resources/Features/AssignmentModule_PUTSubmission.feature"*/
							
							}, 
				glue = {"com.api.automation.stepDefinitions"},
				monochrome = true, 
				dryRun = false, 
				plugin = { "pretty",
				"json:target/cucumber-reports/reports.json", 
				"junit:target/cucumber-reports/Cucumber.xml",
				"html:target/cucumber-reports/reports.html", 
				"html:test-output/index.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}, 
				
				
				//tags = "@UserModulePOSTScenario14",
				
				publish = true)

public class TestRunner {

}