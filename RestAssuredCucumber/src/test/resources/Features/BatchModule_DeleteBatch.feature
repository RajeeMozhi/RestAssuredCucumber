@tag
Feature: Delete program by batch Id

  Background: Setup Authorization
    Given User sets Authorization to No Auth
@Test:1
  Scenario Outline: Check if user able to delete a Batch with invalid programName
  Given User creates DELETE Request for the LMS API endpoint  and  invalid programName from "<SheetName>" and <Rownumber>
  When User sends HTTPS Request
  Then User receives 404 Ok status with message
    
  Examples: 
    | SheetName                     | Rownumber |
    | BatchDeleteByInvalidId        |         0 |
@Test:2
 Scenario Outline: Check if user able to delete a Batch with valid programName
  Given User creates DELETE Request for the LMS API endpoint  and  valid programName from "<SheetName>" and <Rownumber>
  When User sends HTTPS Request
  Then User receives 200 Ok status with message
    
  Examples: 
    | SheetName                   | Rownumber |
    | BatchDeleteByValidId        |         0 | 
 @Test:3 
  Scenario: Check if user able to delete a Batch with valid url and batchid with empty string
  Given User creates DELETE Request for the LMS API endpoint  and  empty string for batch id
  When User sends HTTPS Request
  Then User receives 405 status with message "Method Not Allowed"
  
 @Test:4
 Scenario: Check if user able to delete a batch with valid url and by batchId with non existing batchId
 Given User create DELETE Request for the LMS API end point and non existing batchId for batchId
 When User send HTTPS Request
 Then User receives 404 status with message
 
 @Test:5
 Scenario: Check if user able to delete a batch with valid url and batchId that already deleted one
 Given User create DELETE request for the LMS API end point and batchId that already deleted one
 When User send HTTPS Request
 Then User receives 404 status with message as "Its deleted already"
 