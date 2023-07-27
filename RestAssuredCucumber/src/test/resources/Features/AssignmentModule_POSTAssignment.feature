Feature: Validation of POST request in Assignment Module

  Background: Setup Authorization
    Given User sets Authorization to No Auth

  @test1
  Scenario Outline: Valid An Active Admin creates and grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create and grade new assignment by an active Admin from "<SheetName>" and <Rownumber>
    Then The response status code should be "201" Created

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         0 |

  @test2
  Scenario Outline: Valid An active Admin creates and an active Staff grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create assignment by active Admin and grade by an active staff from "<SheetName>" and <Rownumber>
    Then The response status code should be "201" Created

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         1 |
      
    @test3
  Scenario Outline: Valid  An active Admin creates and an active Student grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create assignment by active Admin and grading by a active Student from "<SheetName>" and <Rownumber>
    Then The response status code should be "201" Created

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         2 |

  @test4
  Scenario Outline: Valid An Active Staff creates and grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create and grade new assignment by an active Staff from "<SheetName>" and <Rownumber>
    Then The response status code should be "201" Created

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         3 |

  @test5
  Scenario Outline: Valid An active Staff creates and an active Admin grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create assignment by active Staff and grading by an active Admin from "<SheetName>" and <Rownumber>
    Then The response status code should be "201" Created
    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         4 |

  @test6
  Scenario Outline: Valid An active Staff creates and an active Student grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create assignment by active Staff and grading by an active student from "<SheetName>" and <Rownumber>
    Then The response status code should be "201" Created

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         5 |

  @test7
  Scenario Outline: Invalid An active Student creates and grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create and grade new assignment by active student from "<SheetName>" and <Rownumber>
    Then The response status code should be "404"

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         6 |
    @test8
  Scenario Outline: Invalid An active Student creates and an active Admin grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create new assignment by active Student and grading by active Admin from "<SheetName>" and <Rownumber>
    Then The response status code should be "404"

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         7 |

  @test9
  Scenario Outline: Invalid An active Student creates and an active Staff grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User sends a POST request to create new assignment by active Student and garding by active Staff from "<SheetName>" and <Rownumber>
    Then The response status code should be "404"

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         8 |

  @test10
  Scenario Outline: Invalid An inactive Admin creates and grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User create a POST request to create and grade new assignment by an inactive Admin from "<SheetName>" and <Rownumber>
    Then The response status code should be "404"
     Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |         9 |

  @test11
  Scenario Outline: Invalid An inactive Staff creates and grades a new assignment
    Given User sets a POST Request to valid base URL and valid end point
    When User create a POST request to create and grade new assignment by an inactive staff from "<SheetName>" and <Rownumber>
    Then The response status code should be "404"

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |        10 |

  @test12
  Scenario Outline: Invalid An active Staff attempts to create and grades a new assignment without a mandatory field
    Given User sets a POST Request to valid base URL and valid end point
    When User create a POST request to to create and grades a new assignment without a mandatory field by active staff  from "<SheetName>" and <Rownumber>
    Then The response status code should be "404"

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |        12 |
    
    @test13
  Scenario Outline: Invalid An Active Admin creates and grades a new assignment
    Given User sets a POST Request to valid base URL and invalid end point
    When User create a POST request to create assignment and grade assignment by an active Admin from "<SheetName>" and <Rownumber>
    Then The response status code should be "404"

    Examples: 
      | SheetName      | Rownumber |
      | POSTAssignment |        13 |