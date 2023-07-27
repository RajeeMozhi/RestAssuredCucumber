#Feature: Validation of POST request
#
#  Background: Setup Authorization
#    Given User sets Authorization to No Auth
#
#  @Test001-CreateProgram
#  Scenario Outline: Verify POST request for Program Module with valid endpoint and valid request body
#    Given User sets request for Program module with valid endpoint and valid request body
#    When User send POST request with data from "<SheetName>" and <Rownumber>
#    Then User should get status code 201 for POST request with valid endpoint and valid request body
#
#    Examples: 
#      | SheetName             | Rownumber |
#      | ProgramPOSTDataShweta |         0 |
#      
# @Test002-CreateBatch
#  Scenario Outline: Verify POST request for Batch module  with valid endpoint and valid request body
#    Given User sets request for Batch module with valid endpoint and valid request body
#    When User send POST request with data for batch module from "<SheetName>" and <Rownumber>
#    Then User successful created batch with status code 201
# 		 And Schema should be validated
#    Examples: 
#      | SheetName           | Rownumber |
#      | BatchPOSTDataShweta |         0 |
#      
#      
#@Test003-Negative-AllBlankParameters
#  Scenario: Verify Batch Module POST request with valid URL and Blank data fields
#    Given User sets request for Batch module with valid base URL and Blank paramertes in request body
#    When User sends POST request with Blank values in all fields in request body
#    Then User gets Bad Request error message with status code 400 in response body with message Enter mandatory fields
#    
#   
#  @Test004-Negative-OnlyMendatoryParametersBlank
# Scenario: Verify Batch Module POST request with valid URL and Blank data fields (only mendatory fields empty)
#   Given User sets request for Batch Module with valid base URL and Blank paramertes in request body
#   When User sends POST request with Blank values in all mandatory the fields in request body
#  Then User gets Bad Request error message with status code 400 in response body with message Enter mandatory fields
#    
#  @Test005-Negative-OnlyBatchNameBlank
#  Scenario Outline: Outline Verify Batch Module POST request with valid URL and Blank data fields
#  Given User sets request for Batch Module with valid base URL and Blank value in BatchName field
#  When User sends POST request with Blank values in BatchName field in request body from "<SheetName>" and <Rownumber>
#  Then User gets Bad Request error message with status code 400 in response body with message Enter mandatory fields
#    
#   Examples:   
#  | SheetName            	|	  Rownumber |
#  | BatchPostBatchNameEmpty |         0 |
#      
#   @Test006-Negative-OnlyBatchStatusBlank
#   Scenario Outline: Outline Verify Batch Module POST request with valid URL and Blank data field
#  Given User sets request for Batch Module with valid base URL and Blank value in BatchStatus field
#   When User sends POST request with Blank values in BatchStatus field in request body from "<SheetName>" and <Rownumber>
#   Then User gets Bad Request error message with status code 400 in response body with message Enter mandatory fields
#    
#      Examples:
#   |  SheetName            		|  Rownumber |
#   |  BatchPostBatchStatusEmpty|        0 |
#    
#    @Test007-Negative-OnlyProgramIDBlank
#	Scenario Outline: Outline Verify Batch Module POST request with valid URL and Blank data field
#   Given User sets request for Batch Module with valid base URL and Blank value in ProgramId field
#  When User sends POST request with Blank values in ProgramId field in request body from "<SheetName>" and <Rownumber>
#  Then User gets Bad Request error message with status code 400 in response body with message Enter mandatory fields
#    
#      Examples:
#   |  SheetName            				|  Rownumber |
#   |  BatchPOSTProgramIDEmpty			|        0 	|
#     
#     @Test008-Negative-OnlyNoOfClassesBlank
#    Scenario Outline: Outline Verify Batch Module POST request with valid URL and Blank data field
#   Given User sets request for Batch Module with valid base URL and Blank value in NoOfClasses field
#   When User sends POST request with Blank values in NoOfClasses field in request body from "<SheetName>" and <Rownumber>
#    Then User gets Bad Request error message with status code 400 in response body with message Enter mandatory fields
#    
#      Examples:
#     |  SheetName            				|  Rownumber |
#     |  BatchPostNoOfClassesEmpty			|        0 	|
#     
# #   @Test009-Negative-NoOfBatchStatusNull
#  # Scenario: Outline Verify POST request with valid URL and Null data fields
# # Given User sets request for Batch module with valid base URL and NULL value in BatchStatus field
# # When User sends POST request with NUll values in BatchStatus field in request body from "<SheetName>" and <Rownumber>
# #  Then User gets Internal Server Error message with status code 500 Null Values
#    
#   #   Examples: 
#   #  |SheetName            			|  Rownumber |
#   #  | BatchPOSTNullBatchStatus  |       0   |
#       
#    @Test010-Negative-AllNullFields
#    Scenario: Verify POST request with valid URL and null data fields
#   Given User sets request for Batch module with valid base URL and NULL paramertes in request body
#   When User sends POST request with Null values in all fields in request body
#   Then User gets Internal Server Error message with status code 500 Null Values
#    
# 
#     
#     