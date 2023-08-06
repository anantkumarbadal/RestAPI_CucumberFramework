Feature: Validating Place APIs

  Scenario: Verify if Place has been successfully added using AddPlaceAPI
    Given Add Place Payload
    When User calls "AddPlaceAPI" with post http request
    Then The API call got success with Status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

