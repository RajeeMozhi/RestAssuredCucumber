Feature: Validation of DETETE request in Assignment module

  Background: Setup Authorization
    Given User sets Authorization to No Auth

  @Test_01
  Scenario Outline: Verify if user is able to delete a record with valid Assignment ID
    Given User creates DELETE Request for the LMS API Endpoint  and  valid Assignment ID
    When User sends HTTPS Request to delete valid Assignment ID from "<SheetName>" and <Rownumber>
    Then Request should be successfull with status code "200" the message "Assignment deleted successfully"

    Examples: 
      | SheetName              | Rownumber |
      | createAndDelAssignment |         0 |

  @Test_02
  Scenario Outline: Verify if user able to delete a record with valid LMS API,invalid Assignment Id
    Given User creates DELETE Request for the LMS API Endpoint  and  invalid Assignment ID
    When User sends HTTPS Request to delete invalid Assignment ID from "<SheetName>" and <Rownumber>
    Then Assigment Bad request error message should be displayed with status code "404"

    Examples: 
      | SheetName              | Rownumber |
      | createAndDelAssignment |         1 |

  @Test_03
  Scenario: Verify if user is able to delete a submission with valid LMS API and empty Assignment ID
    Given User creates DELETE Request for the empty assignment Id to delete assignment Endpoint
    When User sends HTTPS Request to delete a submission with empty assignment Id
    Then The User must receieve error message and with status code "405"