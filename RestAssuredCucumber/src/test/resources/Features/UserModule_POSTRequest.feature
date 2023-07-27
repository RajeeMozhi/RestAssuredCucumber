Feature: Creating User with Role

Background: Setup Authorization 
	Given User sets Authorization to No Auth 

@UserModulePOSTScenario1
Scenario Outline: Vaidation of POST Request with valid data in User module for Role Admin
	Given User sets valid URL with mandatory Payload for user 
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then Request should be successful with status code 201 and creation of user 
	
	Examples:
	|    SheetName  	| 	Rownumber		|
	|UserPOSTValidData	|	0				|

@UserModulePOSTScenario2
Scenario Outline: Vaidation of POST Request with valid data in User module for Role Staff
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then Request should be successful with status code 201 and creation of user 
	
	Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTValidAllFields	|	1				|

	@UserModulePOSTScenario3
Scenario Outline: Vaidation of POST Request with valid data in User module for Role Student
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then Request should be successful with status code 201 and creation of user 
	
	Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTValidAllFields	|	9				|
	
			@UserModulePOSTScenario4
Scenario Outline: Vaidation of POST Request for user role creation with valid phone number which already exists 
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get error message 400 and boolean success details
	
	Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTValidData		|	0				|
	
		@UserModulePOSTScenario5
Scenario Outline: Vaidation of POST Request with invalid 11 digit Phone Number 
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get error message 400 and boolean success details
	
	Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	0				|

			@UserModulePOSTScenario6
Scenario Outline: Vaidation of POST Request for user role creation without all mandatory fields in the request body
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get error message 400 and boolean success details
	
	Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	5				|

	@UserModulePOSTScenario7
Scenario Outline: Vaidation of POST Request with invalid userTimeZone (OST) in User module for Role Student
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get an error message for invalid data with status code 400 and boolean success details 
		Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	14				|
	
		@UserModulePOSTScenario8
Scenario Outline: Vaidation of POST Request with invalid Visa Status (AUS-PR) in User module for Role Admin
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get an error message for invalid data with status code 400 and boolean success details 
		Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	15				|
	
	@UserModulePOSTScenario9
Scenario Outline: Vaidation of POST Request with invalid User Role Status (Deactivated) in User module for Role Admin
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get an error message for invalid data with status code 400 and boolean success details 
		Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	16				|
	
	@UserModulePOSTScenario10
Scenario Outline: Vaidation of POST Request with invalid roleId (R09)in User module for Role Staff
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get an error message for invalid data with status code 400 and boolean success details 
		Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	17				|	
	
		@UserModulePOSTScenario11
Scenario Outline: Vaidation of POST Request with all 0's for userPhoneNumber in User module for Role Staff
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get an error message for invalid data with status code 400 and boolean success details 
		Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	3				|	
	
	
		@UserModulePOSTScenario12
Scenario Outline: Vaidation of POST Request with invalid 9 digit Phone Number in Usermodule role creation
	Given User sets valid URL with mandatory Payload for user
	When User sends POST request with valid URL for user from "<SheetName>" and <Rownumber>
	Then User should get error message 400 and boolean success details
	
	Examples:
	|    SheetName  		| 	Rownumber		|
	|UserPOSTInvalidFields	|	1				|
	
	