@UserModule
Feature: Delete user by userId
  Background: Setup Authorization
    Given User sets Authorization to No Auth
@Test:1
  Scenario Outline: Check if user able to delete a user with valid userId
  Given User creates DELETE Request for the LMS API endpoint for user and valid userId from "<SheetName>" and <RowNumber>
  When User sends HTTPS Request for user 
  Then User receives 200 Ok status with message for user deletion
    
  Examples: 
    | SheetName                     | RowNumber |
    | UserDeleteByUserId            |         0 |
    
 @Test:2
 Scenario Outline: Check if user able to delete a user with invalid userId in the user module
 Given User creates DELETE Request for the LMS API endpoint for user and  invalid userId from "<SheetName>" and <RowNumber>
 When User sends HTTPS Request for user for invalid data
 Then User receives 404 Not Found Status with message and boolean success details for user
  Examples: 
    | SheetName                           | RowNumber |
    | UserDeleteByInvalidUserId           |         0 |

  @Test:3
   Scenario: Check if user able to delete a user with invalid data empty string userId in the user module
 Given User creates DELETE Request for the LMS API endpoint for user and invalid userId with empty string
 When User sends HTTPS Request for user for invalid data
 Then User receives 405 Not Found Status with message for empty string and boolean success details 

  @Test:4
   Scenario: Check if user able to delete a user with valid url and by userId with non existing userId
 Given User create DELETE Request for the LMS API end point for user and non existing userId for userId
 When User send HTTPS Request for user deleteion
 Then User receives 404 status with message for failure
 
 @Test:5
 Scenario: Check if user able to delete a user with invalid url and valid id 
 Given User create DELETE Request for the LMS API for the user with invalid link and valid userId
 When user send HTTPS Request with invalid link
 Then User receives 404 status 