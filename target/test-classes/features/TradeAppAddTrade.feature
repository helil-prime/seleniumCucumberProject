@Regression
Feature: Add Trade
  User is able to add delete edit the trade logs
  
  
  @addTrade @SmokeTest
  Scenario: As a user, I should be able to log my trade
    Given I am on the app log in page
    When I enter valid username "helil" and password "SuperHelil123!"
    And I click on trade signin button
    Then I should be in the trade homepage
    When I click on Add Trade button
    Then I should be on Save Trade Form
    When I select "Buy to Open" and enter symble "TSLA" entrydate "06/01/2021" entryprice "590" exitdate "06/10/2021" exitprice "600"
    And I click save button 
    Then the trade is displayed in the trade table
    
    # we will be working with html date pickers
    # we are going to create couple different scenarios to understand the difference between
    # the cucumber data tables with data passing in the steps
    
    @addTradeWithDatatable @SmokeTest
  Scenario: As a user, I should be able to log my trade
    Given I am on the app log in page
    When I enter valid username "helil" and password "SuperHelil123!"
    And I click on trade signin button
    Then I should be in the trade homepage
    When I click on Add Trade button
    Then I should be on Save Trade Form
    When I enter following trade details
    |Buy to Open|AMZN|06/01/2021|601|06/10/2021|622|
    And I click save button 
    Then the trade is displayed in the trade table
    
    @addTradeAndDatabase
    Scenario: As a user, I should be able to add my trade and validate database
    Given I am on the app log in page
    When I enter valid username "helil" and password "SuperHelil123!"
    And I click on trade signin button
    Then I should be in the trade homepage
    When I click on Add Trade button
    Then I should be on Save Trade Form
    When I enter following trade details
    |Buy to Open|AMZN|06/01/2021|601.05|06/10/2021|622.05|
    And I click save button 
    Then the trade input matches with the database record
    
    @databaseInsertToUI
    Scenario: As a user, I should be able to see the record inserted to database
    Given I insert the following record to database
    |205|1|0|SPCE|2021-03-10|3401.05|2021-06-5|3122.05|12.5|
    And I am on the app log in page
    When I enter valid username "helil" and password "SuperHelil123!"
    And I click on trade signin button
    Then I should be in the trade homepage 
    And the trade input displays on UI