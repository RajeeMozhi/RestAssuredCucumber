Feature: Validation of POST in Submission Module

  Background: Setup Authorization
    Given User sets Authorization to No Auth
@test1
  Scenario Outline: Check if user able to  create a submission  with valid endpoint and request body (non existing values)
    Given User creates POST Request for the API with valid BASEURL and Valid Endpoint
    When User sends HTTPS POST Request and  request Body with mandatory ,additional  fields from "<SheetName>" and <Rownumber>
    Then The User should get Statuscode as "201"

    Examples: 
      | SheetName         | Rownumber |
      | POSTSubmissionMod |         0 |
@test2
  Scenario Outline: Check if user able to  create a submission  with valid endpoint and request body (existing values)
    Given User creates POST Request for the LMS API with valid Base URL and Valid Endpoint
    When User sends POST Request using existing values for  mandatory ,additional  fields from "<SheetName>" and <Rownumber>
    Then The User should get Statuscode as "400" on console

    Examples: 
      | SheetName         | Rownumber |
      | POSTSubmissionMod |         1 |
@test3
  Scenario Outline: Check if user able to create a submission missing mandatory fields in request body
    Given User creates POST Request for the valid LMS Base URL and Valid Endpoint
    When User sends HTTPS Request and request Body with missing mandatory fields from "<SheetName>" and <Rownumber>
    Then User receives "400" Statuscode with message and boolean success details

    Examples: 
      | SheetName         | Rownumber |
      | POSTSubmissionMod |         2 |