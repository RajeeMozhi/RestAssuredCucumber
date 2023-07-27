Feature: Validation of GET request in Assignment Module
Background: Setup Authorization 
Given User sets Authorization to No Auth 

Scenario: Check if user able to retrieve a record with valid LMS API
Given User creates GET Request for the LMS API endpoint
When User sends HTTPS Request with LMS API endpoint
Then User receives 200 OK Status with response body                                                                  


Scenario: Check if user able to retrieve a record with valid LMS API with valid Assignment ID
Given User creates GET Request for the LMS API endpoint with valid Assignment ID
When User sends HTTPS Request for the LMS API endpoint with valid Assignment ID
Then User receives 200 OK StatusCode with response body on console                                                                 


Scenario: Check if user able to retrieve a record with valid BATCH ID
Given User creates GET Request for the LMS API endpoint with valid Batch Id
When User sends HTTPS Request for the LMS API endpoint with valid Batch Id
Then Response should be displayed with 200 OK StatuCode                                                  

#@HeaderValidation1
#Scenario: Check if user able to retrieve a record with valid BATCH ID and validate Headers in Response
#Given User creates GET Request for the LMS API endpoint with valid Batch Id
#When User sends HTTPS Request for the LMS API endpoint with valid Batch Id
#Then Response should be displayed with 200 OK StatuCode and 
#And Headers fields of Response should get validated                                                  

                                                          