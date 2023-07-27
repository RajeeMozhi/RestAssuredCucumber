#Author Jass Harpreet Priyanka
Feature: Validation of PUT request in Submission Module
Background: Setup Authorization 
Given User sets Authorization to No Auth 
@test1
Scenario: Check if user able to  grade Assignment with valid submission  Id and Mandatory request body
Given User creates PUT Request for the LMS API endpoint  and Valid submission Id
When User sends HTTPS Request and  request Body with Mandatory  Fields
Then User receives 200 OK Status with updated value in response body
@test2
Scenario: Check if user able to  grade Assignment with invalid submission  Id and Mandatory request body
Given User creates PUT Request for the LMS API endpoint  and  invalid submission ID
When User sends  Request and  request Body with Mandatory  field
Then User receives 400 Not Found Status with message and boolean success details
@test3
Scenario: Check if user able to  grade Assignment with valid submission  Id and Missing mandatory request body
Given User creates PUT Request for the LMS API endpoint  and Valid submission Id
When User sends HTTPS Request  and request Body with missing mandatory fields
Then User receives 400 Bad Request Status with message and boolean success details