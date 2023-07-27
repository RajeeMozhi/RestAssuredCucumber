Feature: Validation of GET request in Program Module

	Background: Setup Authorization 
	Given User sets Authorization to No Auth 
	@test1
  Scenario: Verify GET request to retrieve all program data with Valid URL
    Given User write request for Program module with Valid Base URL
    When User send GET request 
    Then Request should be successfully displayed on console with status code "200" for GET All programs
    @test2
  Scenario: Verify GET request to retrieve all program data with Invalid URL
    Given User write request for Program module with Invalid base URL
    When User send GET request with Invalid URL
    Then Not found error message should be displayed on console with status code "404" for GET All programs
    @test3
  Scenario Outline: Verify GET request to retrieve single program data with Valid program ID
  	Given User sets request for Program module with Valid Base URL and Valid Path
  	When User send GET request with Valid program ID from "<SheetName>" and <Rownumber> 
  	Then Request should be successfully displayed on console with status code "200" for GET single program  

  	Examples: 
      | SheetName        		 | Rownumber |
      | ProgramGETValidProgramId |     0     |

  @test4
  Scenario Outline: Verify GET request to retrieve single program data with Invalid program ID
  	Given User write request for Program module with Valid Base URL and Invalid Path
  	When User send GET request with Invalid program ID from "<SheetName>" and <Rownumber>
  	Then Bad request error message should be displayed on console with status code "400" for GET single program

  	Examples: 
      | SheetName        			| Rownumber |
      | ProgramGETInValidProgramId  |     0     |
