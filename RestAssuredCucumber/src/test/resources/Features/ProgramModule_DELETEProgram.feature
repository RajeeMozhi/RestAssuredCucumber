Feature: Validation of DETETE request in program module

  Background: Setup Authorization
    Given User sets Authorization to No Auth

  @Prog_Del_01
  Scenario Outline: Verify if user able to delete a program with valid program ID
    Given User creates DELETE Request for the LMS API endpoint  and  valid program ID
    When User sends HTTPS Request to delete valid program ID from "<SheetName>" and <Rownumber>
    Then Request should be successfull with status code "200" and get the message "Message: Program Id-{programId} is deleted Successfully!"

    Examples: 
      | SheetName           | Rownumber |
      | deleteProgramModule |         0 |

  @Prog_Del_02
  Scenario Outline: Verify DELETE request by programID with invalid programID
    Given User creats request for Program module with invalid programID
    When User sends DELETE request with invalid programId from "<SheetName>" and <Rownumber>
    Then Program Bad request error message should be displayed with status code "404"

    Examples: 
      | SheetName           | Rownumber |
      | deleteProgramModule |         1 |

  @Prog_Del_03
  Scenario Outline: Verify DELETE request by programName with valid programName
    Given User creates request to delete Program module with valid programName
    When User sets DELETE request with programName from "<SheetName>" and <Rownumber>
    Then Success message with status code "200" and receive message "Message: Program Name -{programName} is deleted Successfully!"

    Examples: 
      | SheetName           | Rownumber |
      | deleteProgramModule |         0 |

  @Prog_Del_04
  Scenario Outline: Verify DELETE request by programName with invalid programName
    Given User creates request for Program module with invalid programName
    When User sets DELETE request with invalid programName from "<SheetName>" and <Rownumber>
    Then Bad request error message should display with status code "404"

    Examples: 
      | SheetName           | Rownumber |
      | deleteProgramModule |         2 |

  @Prog_Del_05
  Scenario: Verify DELETE request by programID with blank programID
    Given User creates request for Program module with blank programID
    When User sends DELETE request with blank programID
    Then Program Not found error message should be displayed with status code "404"

  @Prog_Del_06
  Scenario: Verify DELETE request by programName with blank programName
    Given User sends request for Program module with blank programName
    When User sets DELETE request with blank programName
    Then Program Not found error message should display on console with status code "404"