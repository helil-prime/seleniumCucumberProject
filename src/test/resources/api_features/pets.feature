Feature: pets store api tests

  @findbystatus
  Scenario: As a user, I should be able to perform GET request to find a pet by status
    Given Find a pet by status GET request endpoint exists
    When I send a GET request by "available" to valid endpoint
    Then Response status code should be 200
    And Content type should be "application/json"
    
    @findbyid
    Scenario: As a user, I should be able to perform GET request to find a pet by id
    Given I have the GET request URL
    When I perform GET request to URL by 27225
    Then Response status code should be 200
    
    @postmydog
    Scenario: As a user, I should be able to perform POST request to post my dog
    Given I have the POST url and content body
    When I perform POST request to URL
    Then Response status code should be 200
    And response body should contain request body data
