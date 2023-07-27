Feature: Validation of PUT request for Assignment Mudule

  Background: Setup Authorization

    Given User sets Authorization to No Auth
   @test-A1-Positive-Valid-URL
   Scenario Outline: Verify put request for Assignment module  with valid endpoint and mandatory fields
    Given User sets request for Assignment module with valid endpoint and mandatory fields
    When User send PUT request with data for Assignment module from "<SheetName>" and <Rownumber>
    Then User successful created assignment with status code 200
 		 
    Examples: 
      | SheetName           | Rownumber |
      | AssignmentPUTData   |         0 |
   
   
   
   @test-A2-Negative-Invalid-URL
    Scenario Outline: Verify put request for Assignment module  with Invalid endpoint and mandatory fields
    Given User sets request for Assignment module with invalid endpoint 
    When User send PUT request with data for Assignment module from "<SheetName>" and <Rownumber>
    Then User is not successful creating assignment with status code 404
 		 
    Examples: 
      | SheetName           | Rownumber |
      | AssignmentPUTData   |         0 |
   
   
     @test-A3-Negative-valid-URL-Missing-Payload
    Scenario Outline: Verify put request for Assignment module  with valid endpoint and missing mandatory fields
    Given User sets request for Assignment module with valid endpoint 
    When User send PUT request with data for Assignment module from "<SheetName>" and <Rownumber>
    Then User is not successful creating assignment with status code 400
 		 
    Examples: 
      | SheetName                     | Rownumber |
      | AssignmentPutMissingPayload   |         0 |
   
   
  