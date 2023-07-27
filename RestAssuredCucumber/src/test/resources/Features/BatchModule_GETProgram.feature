Feature: Validation of GET request in Batch module

  @Batch_Get_01
  Scenario: Verify GET request to retrieve all batch data in valid LMS API and data size for all batches fetched
    Given User creates GET Request for the LMS API endpoint for all batches
    When User sends HTTPS Request for all batches
    Then User should get successfull message with status code "200"

  @Batch_Get_02
  Scenario: Verify GET request to retrieve all batch data with invalid LMS API
    Given User creates request for Batch module with invalid base URL
    When User sends GET request to batch module
    Then Error message should display with status code "404"

  @Batch_Get_03
  Scenario Outline: Verify GET request to retrieve single batch data with valid batch ID
    Given User creates request for Batch module with valid base URL and valid path
    When User sends GET request with valid batch ID from "<SheetName>" and <Rownumber>
    Then Request should be successfull with status code "200"

    Examples: 
      | SheetName      | Rownumber |
      | batchesGet |         0 |

  @Batch_Get_04
  Scenario Outline: Verify GET request to retrieve single batch data with invalid batch ID
    Given User creates request for Batch module with valid base URL and invalid batchId
    When User sends GET request with invalid batch ID from "<SheetName>" and <Rownumber>
    Then User receives "404" Not Found Status with message and boolean success details for invalid batchId

    Examples: 
      | SheetName  | Rownumber |
      | batchesGet |         0 |

  @Batch_Get_05
  Scenario Outline: Verify GET request to retrieve single batch data with valid batch Name
    Given User creates request for Batch module with valid base URL and valid batch name
    When User sends GET request with valid batch Name from "<SheetName>" and <Rownumber>
    Then Request should be successfull with status code "200" for GET single batch Name

    Examples: 
      | SheetName  | Rownumber |
      | batchesGet |         1 |

  @Batch_Get_06
  Scenario Outline: Verify GET request to retrieve single batch data with invalid batch Name
    Given User creates request for Batch module with valid base URL and invalid batch name
    When User sends GET request with invalid batch Name from "<SheetName>" and <Rownumber>
    Then Request should be unsuccessfull with status code "404" for GET batch invalid Name

    Examples: 
      | SheetName  | Rownumber |
      | batchesGet |         1 |

  @Batch_Get_07
  Scenario Outline: Verify GET request to retrieve single batch data with valid program ID
    Given User creates request for Batch module with valid base URL and valid programId
    When User sends GET request with valid batch program ID from "<SheetName>" and <Rownumber>
    Then Request should be successfull with status code "200" for GET single batch program ID

    Examples: 
      | SheetName  | Rownumber |
      | batchesGet |         0 |

  @Batch_Get_08
  Scenario Outline: Verify GET request to retrieve single batch data with invalid program ID
    Given User creates request for Batch module with valid base URL and invalid programId
    When User sends GET request with invalid batch program ID from "<SheetName>" and <Rownumber>
    Then Request should be successfull with status code "404" for GET single batch program invalidID

    Examples: 
      | SheetName  | Rownumber |
      | batchesGet |         2 |