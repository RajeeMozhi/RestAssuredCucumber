@SubmissionModule
Feature: Delete user by userId
  Background: Setup Authorization
    Given User sets Authorization to No Auth

  @Test:1
  Scenario: Check if user able to retrieve all submission with valid LMS API endpoint
    Given User creates GET Request for the LMS API endpoint
    When User sends HTTPS Request for submission module
    Then User receives 200 OK Status with response body. 
      
  @get_gradesby_assignmentId:2(get_gradesby_assignmentId)
   Scenario Outline: Check if user able to retrieve a grades with valid Assignment ID
   Given User creates GET Request for the LMS API endpoint with valid Assignemnt ID from "<SheetName>" and <RowNumber>
   When  User sends HTTPS Request for grades with valid assignmentId
   Then User receives 200 OK Status with the response body
    Examples: 
    | SheetName                           | RowNumber |
    | SubmissionGetValidAssignmentId     |         0 |
  
   @get_gradesby_invalidassignmentId:3(get_gradesby_invalidassignmentId)
   Scenario Outline: Check if user able to retrieve a grades with invalid Assignment ID
   Given User creates GET Request for the LMS API endpoint with invalid Assignemnt ID from "<SheetName>" and <RowNumber>
   When  User sends HTTPS Request for grades with invalid assignmentId
   Then User receives 404 Not Found Status with message and boolean success details
    Examples: 
    | SheetName                           | RowNumber |
    | SubmissionGetInvalidAssignmentI     |         0 |
    
    @Getgrades_by_studentId:4(Getgrades_by_studentId)
    Scenario Outline: Check if user able to retrieve a submission with valid student ID 
    Given User creates GET Request for the LMS API endpoint with valid Student Id from "<SheetName>" and <RowNumber>
    When User sends HTTPS Request with valid studentId
    Then user receives 200 ok message with success response body
    Examples: 
    | SheetName                           | RowNumber |
    | GetSubmissionByValidSI              |         0 |
    @Getgrdes_by_invalidStudentId:5(Getgrdes_by_invalidStudentId)
    Scenario Outline: Check if user able to retrieve a grades with invalid Student ID
    Given User creates GET Request for the LMS API endpoint with invalid Student Id from "<SheetName>" and <RowNumber>
    When User sends HTTPS Request with invalid studentId
    Then User receives 404 Not Found Status with message for invalid data
     Examples: 
    | SheetName                           | RowNumber |
    | GetSubmissionByInValidSI              |         0 |
    
    @getSubmission_by_batchId:6(getSubmission_by_batchId)
    Scenario Outline: Check if user able to retrieve a grades with valid  batch ID 
    Given User creates GET Request for the LMS API endpoint with valid Batch Id from "<SheetName>" and <RowNumber>
    When User sends HTTPS Request with valid Batch Id
    Then User receives 200 ok message for valid Batch Id
      Examples: 
    | SheetName                           | RowNumber |
    | GetSubmissionValidBI                |         0 |
    
    @getSubmission_by_invalidbatchId:7(getSubmission_by_invalidbatchId)
    Scenario Outline: Check if user able to retrieve a grades with invalid batch ID
    Given User creates GET Request for the LMS API endpoint with invalid Batch Id from "<SheetName>" and <RowNumber>
    When User sends HTTPS Request with invalid batchId
    Then User receives 404 message with boolean success details for invalid batchId
       Examples: 
    | SheetName                           | RowNumber |
    | GetSubmissionInvalidBId             |         0 |
    @getSubmission_by_validuserId:8(getSubmission_by_validuserId)
    Scenario Outline: Check if user able to retrieve a submission with valid User ID
    Given User creates GET Request for the LMS API endpoint with valid User Id from "<SheetName>" and <RowNumber>
    When User sends HTTPS  Request with valid userId
    Then User get 200 ok message for valid userId
      Examples: 
    | SheetName                           | RowNumber |
    | GetSubmissionByValiduserID          |         0 |
    @getSubmission_by_invaliduserId:9(getSubmission_by_invaliduserId)
    Scenario Outline: Check if user able to retrieve a submission with invalid User Id
    Given User creates GET Request for the LMS API endpoint with invalid User Id from "<SheetName>" and <RowNumber>
    When User sends HTTPS Request for invalid userId
    Then User Receives 404 with error message for invalid userId
       Examples: 
    | SheetName                           | RowNumber |
    | GetSubmissionByInvalidUserId          |         0 |
    
    
    
   
   
    
 