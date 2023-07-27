#Author: Jaswinder
Feature: Validation of PUT request in BATCH Module
Background: Setup Authorization 
	Given User sets Authorization to No Auth 

Scenario: Verify PUT Request for Batch by providing Invalid URL
Given User write request with Valid Request Body for Batch
When User send put request with Invalid URL
Then Server unavailable error message should be displayed with 503 status code

Scenario: Verify PUT request by providing Invalid EndPoint
Given User write request with valid Payload
When User send put request with Invalid Endpoint for Batch
Then Not found validation error message should be displayed with 404 status code

Scenario: Verify PUT request by providing BatchId as endpoint with Empty payload
Given User set put request with empty request body
When User send put request with Valid URL
Then Bad request error message should be displayed with 400 status code for batch

Scenario: Verify put request by Batch Id for already existing data
Given User set put request with valid Payload for Existing data
When User send put request for batch
Then Request should be successfull with status code 200 for batch

Scenario: Verify put request by Batch Id for non existing data on server
Given User set put request with invalid Payload 
When User send request with Valid batch URL
Then batch with id not found error message should be displayed with 400 status code

Scenario: Verify put request by Batch Id to update batch details without batch description
Given User set put request without batch description
When User requested put, with Valid URL
Then Batch should be created with null value for program description with status code 200

Scenario: Verify put request by Batch Id to update batch details without batch No of classes
Given User set put request with valid payload except batch no of classes
When User send put request to check without no of classes
Then Validation error  No of Classes is needed; It should be a positive number  should be displayed with 400 bad request
