Feature: Validation of DELETE request in Submission Module
Background: Setup Authorization 
Given User sets Authorization to No Auth
@test1
Scenario Outline: Check if user able to DELETE a submission with valid submission Id
  	Given User sets request for Program module with Valid Base URL and Valid submission Id
  	When User send DELETE request with Valid Submision ID from "<SheetName>" and <Rownumber> 
  	Then Request should be successfully displayed on console with status code "200" for DELETE SubmissionID  
  	
  	Examples: 
      | SheetName        | Rownumber |
      | DELETEValidSubmissionId |     0   |
@test2  
 Scenario Outline: Check if user able to DELETE a submission with Invalid submission Id
  	Given User sets request for Program module with Valid Base URL and InValid submission Id
  	When User send DELETE request with InValid Submision ID from "<SheetName>" and <Rownumber> 
  	Then Request should be successfully displayed on with status code "404" for DELETE SubmissionID  
  	
  	Examples: 
      | SheetName        | Rownumber |
      | DELETEInValidSubmissionId |     0     |   
  
