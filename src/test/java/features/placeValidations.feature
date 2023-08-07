Feature: Validating Place APIs

  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI

    Given Add Place Payload with "<name>"  "<language>" "<address>"
    When User calls "AddPlaceAPI" with post http request
    Then The API call got success with Status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:

    | name    | language | address     |
    | MyHouse | English  | DLF Phase 1 |
    | This House | Hindi  | Indira Nagar |


