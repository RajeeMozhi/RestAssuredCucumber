Feature: Validation of GET request in User Module

Background: Setup Authorization
Given User sets Authorization to No Auth
    
@test1
Scenario: Valid - A user retrieves all users
    Given The user sets a GET request for all users with a valid base URL
    When The user sends the GET request with a valid endpoint
    Then The response code should be "200" OK and The response body should contain a list of all users
    
@test2
Scenario: Invalid - A user attempts to retrieve all users using an invalid endpoint
    Given The user sets a GET request for all users with a valid base URL
    When The user sends the GET request with an invalid endpoint
    Then The response code should be "404" Not Found 

@test3
Scenario: Invalid - A user attempts to retrieve all users using an invalid URL
    Given The user sets a GET request for all users with an invalid base URL
    When The user sends the GET request with a valid endpoint
    Then The response code should be "404" Not Found

@test4
Scenario Outline: Valid - A user retrieves information using a valid user ID
    Given User creates a GET Request to get User by ID
    When User sends the GET request to the valid endpoint with a valid User ID from "<SheetName>" and <RowNumber>
    Then The response code should be "200" OK and the response body should contain the details of the user
    
    Examples: 
      | SheetName   		 | RowNumber |
      | GetUserbyValidID |         0 |

@test5
Scenario Outline: Invalid - A user attempts to retrieve information using an invalid user ID
    Given User creates a GET Request to get User by ID
    When User sends the HTTPS request to the valid endpoint with an invalid User id from "<SheetName>" and <RowNumber>
    Then The response code should be "404" Not Found and the response should contain a boolean indicating the request was not successful
    Examples: 
   		| SheetName          | RowNumber |
      | GetUserbyInvalidID |         0 |

@test6
Scenario: Valid- A user attempts to retrieve all staff
    Given User creates a GET Request to get all staff
    When User sends the HTTPS request to the valid endpoint
    Then The response code should be "200" OK and the response body should contain a list of all staff members
 
@test7
Scenario: Valid- A user attempts to retrieve all Users with roles
    Given User creates a GET Request to retrive all users with roles
    When User sends the HTTPS request to valid end point
    Then The response code should be "200" OK and the response body should contain list of all Users with roles
   
@test8
Scenario: Invalid- A user attempts to retrieve all Users with roles
    Given User creates a GET Request to retrive all users with roles
    When User sends the HTTPS request to an invalid end point
    Then The response code should be "404" Bad request

