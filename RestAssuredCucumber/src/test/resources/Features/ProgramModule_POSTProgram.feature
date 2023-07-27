Feature: Validation of POST request in Program Module

  Background: Setup Authorization
    Given User sets Authorization to No Auth
    
@test1
  Scenario Outline: Verify POST request with valid base URL and valid request body
    Given User sets request for Program module with valid base URL and valid request body
    When User send POST request with all data fields from "<SheetName>" and <RowNumber>
    Then User gets success message with status code "201" in response body with message Program created

    Examples: 
      | SheetName            | RowNumber |
      | ProgramPOSTValidData |         5 |
      
      
@test2
  Scenario Outline: Verify POST request with valid base URL and valid request body
    Given User sets request for Program module with valid base URL and valid request body
    When User send POST request with mandatory data fields from "<SheetName>" and <RowNumber>
    Then User gets success message with status code "201" in response body with message Program created

    Examples: 
      | SheetName            | RowNumber |
      | ProgramPOSTValidData |         6 |

@test3
  Scenario Outline: Verify POST request with invalid base URL and valid request body
    Given User sets request for Program module with invalid base URL and valid request body
    When User sends POST request with invalid URL and valid data from "<SheetName>" and <RowNumber>
    Then User gets Bad Request error message with status code "404" in response body with message Invalid URL

    Examples: 
      | SheetName              | RowNumber |
      | ProgramPOSTValidData 	 |         7 |
@test4
  Scenario: Verify POST request with valid URL and Blank data fields
    Given User sets request for Program module with valid base URL and Blank paramertes in request body
    When User sends POST request with Blank values in all fields in request body
    Then User gets Bad Request error message with status code "400" in response body with message Enter mandatory fields
    
@test5
  Scenario: Verify POST request with valid URL and Blank data fields
    Given User sets request for Program module with valid base URL and Blank paramertes in request body
    When User sends POST request with Blank values in all mandatory the fields in request body
    Then User gets Bad Request error message with status code "400" in response body with message Enter mandatory fields
    
@test6
  Scenario Outline: Verify POST request with valid URL and Blank data fields
    Given User sets request for Program module with valid base URL and Blank value in programName field
    When User sends POST request with Blank values in programName field in request body from "<SheetName>" and <Rownumber>
    Then User gets Bad Request error message with status code "400" in response body with message Enter mandatory fields
    
        Examples: 
      | SheetName            		 | Rownumber |
      | ProgramPOSTNoProgramName |         0 |
    
@test7
  Scenario Outline: Verify POST request with valid URL and Blank data fields
    Given User sets request for Program module with valid base URL and Blank value in programDescription field
    When User sends POST request with Blank values in programDescription field in request body from "<SheetName>" and <Rownumber>
    Then User gets success message with status code "201" in response body with message Program created
    
        Examples: 
      | SheetName            						| Rownumber |
      | ProgramPOSTNOProgramDescription 			|         0 |
    
@test8
  Scenario Outline: Verify POST request with valid URL and Blank data fields
    Given User sets request for Program module with valid base URL and Blank value in programStatus field
    When User sends POST request with Blank values in programStatus field in request body from "<SheetName>" and <Rownumber>
    Then User gets Internal Server Error message with status code "500": Null Values
    
        Examples: 
      | SheetName            			 | Rownumber |
      | ProgramPOSTNoProgramStatus 		 |         0 |

    
@test9
  Scenario: Verify POST request with valid URL and null data fields
    Given User sets request for Program module with valid base URL and NULL paramertes in request body
    When User sends POST request with Null values in all fields in request body
    Then User gets Internal Server Error message with status code "500": Null Values
    
@test10
  Scenario: Verify POST request with valid URL and null data fields
    Given User sets request for Program module with valid base URL and NULL paramertes in request body
    When User sends POST request with Null values in all mandatory fields in request body
    Then User gets Internal Server Error message with status code "500": Null Values
    

    
@test11
  Scenario Outline: Verify POST request with valid URL and null data fields
    Given User sets request for Program module with valid base URL and NULL value in programName field
    When User sends POST request with Null values in programName field in request body from "<SheetName>" and <Rownumber>
    Then User gets Internal Server Error message with status code "500": Null Values
    
        Examples: 
      | SheetName            | Rownumber |
      | ProgramPOSTValidData |         0 |
    
@test12
  Scenario Outline: Verify POST request with valid URL and null data fields
    Given User sets request for Program module with valid base URL and NULL value in programDescription field
    When User sends POST request with Null values in programDescription field in request body from "<SheetName>" and <Rownumber>
    Then User gets success message with status code "201" in response body with message Program created
    
        Examples: 
      | SheetName            | Rownumber |
      | ProgramPOSTValidData |         0 |
    
@test13
  Scenario Outline: Verify POST request with valid URL and null data fields
    Given User sets request for Program module with valid base URL and NULL value in programStatus field
    When User sends POST request with Null values in programStatus field in request body from "<SheetName>" and <Rownumber>
    Then User gets Internal Server Error message with status code "500": Null Values
    
        Examples: 
      | SheetName            | Rownumber |
      | ProgramPOSTValidData |         0 |
    
    @test14
  Scenario Outline: Verify POST request with valid URL and without one mandatory field
    Given User sets request for Program module with valid base URL and without one mandatory field
    When User sends POST request without programName field in request body from "<SheetName>" and <Rownumber>
    Then User gets Bad Request error message with status code "400" in response body with message Enter mandatory fields
    
        Examples: 
      | SheetName            		 | Rownumber |
      | ProgramPOSTNoProgramName 	 |	      0 |
    
@test15
  Scenario Outline: Verify POST request with valid URL and without one mandatory field
    Given User sets request for Program module with valid base URL and without one mandatory field
    When User sends POST request without programDescription field in request body from "<SheetName>" and <Rownumber>
    Then User gets success message with status code "201" in response body with message Program created
    
        Examples: 
      | SheetName            				 		| Rownumber |
      | ProgramPOSTNOProgramDescription 			|         0 |
    
@test16
  Scenario Outline: Verify POST request with valid URL and without one mandatory field
    Given User sets request for Program module with valid base URL and without one mandatory field
    When User sends POST request without programStatus field in request body from "<SheetName>" and <Rownumber>
    Then User gets Bad Request error message with status code "500" in response body with message Enter mandatory fields
    
        Examples: 
      | SheetName            			 | Rownumber |
      | ProgramPOSTNoProgramStatus 		 |         0 |
    
   
    
