Feature: Testing new features

  @testing
  Scenario: Testing new features
    Given I am on the 'home' page
    When I click on the link "New Feature"
    Then I should see "This is a new feature"