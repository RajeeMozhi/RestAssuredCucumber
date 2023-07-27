Feature: Creating User with Role

Background: Setup Authorization 
	Given User sets Authorization to No Auth 

#@UserModulePUTScenario1
#Scenario Outline: Vaidation of POST Request with valid data in User module for Role Admin
#	Given User sets valid URL with mandatory Payload for user role creation
#	When User sends POST request with valid URL for user role from "<SheetName>" and <Rownumber>
#	Then Request should be successful with status code 201 and creation of user Role
#	
#	Examples:
#	|    SheetName  	| 	Rownumber		|
#	|UserPOSTValidData	|	2				|
#	
#@UserModulePUTScenario2	
#	Scenario Outline: Verify that user is able to update a user with valid User Id and request body
#	Given User sets valid URL with mandatory Payload for user to update a user
#	When User sends HTTPS PUT Request and  request Body with mandatory fields from "<SheetName>" and <Rownumber>
#	Then Request should be successful with status code <200> and updation of user 
#	
#	Examples:
#	|    SheetName  	| 	Rownumber		|
#	|UserPOSTValidData	|	3				|

@UserModulePUTScenario3
	Scenario Outline: Verify that user is not able to update a user with invalid User Id and mandatory request body
	Given User sets valid URL with mandatory Payload for user to update a user
	When User sends HTTPS PUT Request with invalid  user id and  request Body with mandatory fields from "<SheetName>" and <Rownumber>
	Then Request should get error message with status code <400> Not Found  
	
	Examples:
	|    SheetName  	| 	Rownumber		|
	|UserPOSTValidData	|	4				|
		