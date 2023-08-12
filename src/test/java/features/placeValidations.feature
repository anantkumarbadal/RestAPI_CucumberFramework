Feature: Validating Place APIs

  #adding tags to run only particular test cases using the tags
  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI

    Given Add Place Payload with "<name>"  "<language>" "<address>"
    When User calls "AddPlaceAPI" with "post" http request
    Then The API call got success with Status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify the place_id created maps to "<name>" using "GetPlaceAPI"

    Examples:

    | name    | language | address     |
  #  | MyHouse | English  | DLF Phase 1 |
    | Anant   | Hindi  | Patna |

 @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When  User calls "DeletePlaceAPI" with "POST" http request
    Then  The API call got success with Status code is 200
    And   "status" in response body is "OK"