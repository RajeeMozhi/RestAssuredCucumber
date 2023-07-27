Feature: Validation of PUT request in Program Module

Background: Setup Authorization 
	Given User sets Authorization to No Auth 
	
	@POSTModulePUTRequestScenario0	
	Scenario Outline: Verify POST request with valid base URL and valid data 
	Given User sets request for Program module with base URL with valid endpoint and valid request body 
	When User send POST request with data from "<SheetName>" and <RowNumber> 
	Then User should get status code "201" for POST request with valid endpoint and valid request body 
	
	Examples: 
	| SheetName            	 | RowNumber |
	| ProgramInputToPost	 |         0 |
		
	@POSTModulePUTRequestScenario1	
Scenario: Verify PUT request by valid program id with mandatory request body for already existing data 
	Given User sets valid URL with mandatory Payload 
	When User sends PUT request with valid URL 
	Then Request should be successfull with status code 200 and updated response body 
	
	@POSTModulePUTRequestScenario2	
Scenario: Verify PUT request by invalid program id with mandatory request body 
	Given User sets valid URL with invalid program id and mandatory Payload 
	When User sends PUT request with invalid program id 
	Then User should get an error message with status code 404 Not Found and boolean success details 
	
	@POSTModulePUTRequestScenario3
Scenario: Verify PUT request by valid program id without mandatory fields in request body 
	Given User sets valid URL with valid program id and without mandatory fields in Payload 
	When User sends PUT request with valid URL 
	Then User should get an error message with status code 400 Bad Request and boolean success details 
	
		@POSTModulePUTRequestScenario4
Scenario: Verify PUT request by valid program id with empty values in mandatory fields in request body 
	Given User sets valid URL with valid program id and with empty value in mandatory fields in Payload 
	When User sends PUT request with valid URL 
	Then User should get an error message with status code 400 Bad Request and boolean success details 
	
	@POSTModulePUTRequestScenario5
Scenario: Verify PUT request by valid program id with only mandatory fields for already existing data 
	Given User sets valid URL with only mandatory Payload 
	When User sends PUT request with valid URL 
	Then Request should be successfull with status code 200 and updated response body 
	
	@POSTModulePUTRequestScenario6	
Scenario: Verify PUT request with valid program id and empty payload 
	Given User sets valid URL with valid program id and empty payload 
	When User sends PUT request with valid URL 
	But User should not able to update the program 
	
	@POSTModulePUTRequestScenario7	
Scenario: Verify PUT request with invalid URL and mandatory request body 
	Given User sets invalid URL with valid mandatory Payload 
	When User sends PUT request with invalid URL 
	Then User should get an error message with 404 status code 
	
	@POSTModulePUTRequestScenario8	
	Scenario: JSON Schema Validation for an updated program by program id in PUT request 
		Given User sets valid URL with mandatory Payload 
		When User sends PUT request with valid URL 
		Then Request should be successfull with status code 200 and updated response body
		And Schema should be validated 
	
	@POSTModulePUTRequestScenario9	
Scenario Outline: Verify PUT request by valid program name with all fields including mandatory request body for already existing data using DDT 
	Given User set request for Program module with valid base url and valid request body by valid program name
	When When User sends PUT request with all fields from "<SheetName>" and <RowNumber> 
	Then Request should be successfull with status code 200 and updated response body 
	
	Examples: 
		| SheetName			| 	RowNumber			|
		|ProgramPUTValidData|	0					|
		
		@POSTModulePUTRequestScenario10
Scenario Outline: Verify PUT request by valid program name with ONLY mandatory fields for already existing data using DDT 
	Given User set request for Program module with valid base url and valid request body by valid program name
	When When User sends PUT request with ONLY mandatory fields from "<SheetName>" and <RowNumber> 
	Then Request should be successfull with status code 200 and updated response body 
	
	Examples: 
		| SheetName			| 	RowNumber			|
		|ProgramPUTValidData|	1					|
		
		@POSTModulePUTRequestScenario11	
Scenario Outline: Verify PUT request by valid program name without all mandatory fields for already existing data using DDT 
	Given User set PUT request for Program module by Program Name with valid base url and without all mandatory fields in request body 
	When When User sends PUT request without mandatory fields from "<SheetName>" and <RowNumber> 
	Then Request should get error message with status code 400 Bad Request 
	
	Examples: 
		| SheetName			| 	RowNumber			|
		|ProgramPUTValidData|	2					|
		
		@POSTModulePUTRequestScenario12	
Scenario Outline: Verify PUT request by invalid program name with all mandatory fields using DDT 
	Given User set request for Program module with valid base url and invalid program name 
	When When User sends PUT request with invalid program name and mandatory fields from "<SheetName>" and <RowNumber> 
	Then Request should get error message with boolean success details and status code 404 Not Found 
	
	Examples: 
		| SheetName			| 	RowNumber			|
		|ProgramPUTValidData|	3					|	

	@POSTModulePUTRequestScenario13
	Scenario Outline: JSON Schema Validation for an updated program by program name in PUT request 
		Given User set request for Program module with valid base url and valid request body by valid program name
		When When User sends PUT request with all fields from "<SheetName>" and <RowNumber> 
		Then Request should be successfull with status code 200 and updated response body 
		And Schema should be validated 
		
			Examples: 
		| SheetName			| 	RowNumber			|
		|ProgramPUTValidData|	0					|
	
	
		@POSTModulePUTRequestScenario14	
Scenario Outline: Verify PUT request by valid program name with all fields including NULL values to mandatory request body for already existing data using DDT 
	Given User set request for Program module with valid base url and valid request body by valid program name
	When When User sends PUT request with all fields from "<SheetName>" and <RowNumber> 
	Then Request should get error message with status code 400 Bad Request 
	
	Examples: 
		| SheetName			| 	RowNumber			|
		|ProgramPUTValidData|	5					|