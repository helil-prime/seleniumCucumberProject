Feature: Add Trade
  User is able to add delete edit his trade logs

  #Scenario: As a user, I should be able to log my trade
  #Given I am on the app log in page
  #When I enter valid username "helil" and password "SuperHelil123!"
  #And I click on signin button
  #Then I should be in the trade homepage
  #When I click on Add Trade button
  #Then I should be on Save Trade Form
  #When I select "Buy to Open" and enter symble "TSLA" entrydate "06/01/2021" entryprice "590" exitdate "06/10/2021" exitprice "600"
  #And I click save button
  #Then the trade is displayed in the trade table
  
  
  
  
  
  @TradedataTable
  Scenario: As a user, I should be able to log my trade
    Given I am on the app log in page
    When I enter valid username "helil" and password "SuperHelil123!"
    And I click on trade signin button
    Then I should be in the trade homepage
    When I click on Add Trade button
    Then I should be on Save Trade Form
    When I enter following trade details
      | Buy to Open | TSLA | 06/01/2021 | 590 | 06/10/2021 | 600 |
    And I click save button
    Then the trade is displayed in the trade table
    #Scenario Outline: As a user, I am able to log trades and my trades should be on the homepage table
    #Given I am on the app log in page
    #When I enter valid username "helil" and password "SuperHelil123!"
    #And I click on signin button
    #Then I should be in the trade homepage
    #When I click on Add Trade button
    #Then I should be on Save Trade Form
    #When I select "Buy to Open" and enter symbols "<Symbols>" entrydate "<EntryDate>" entryprice "<EntryPrice>" exitdate "<ExitDate>" exitprice "<ExitDate>"
    #And I click save button 
    #Then Trades symbols "<Symbols>" entrydate "<EntryDate>" entryprice "<EntryPrice>" exitdate "<ExitDate>" exitprice "<ExitDate>" are correct in database
    #
    #Examples: 
    #| Symbols | EntryDate | EntryPrice | ExitDate | exitPrice |
    #| TSLA | 06/01/2021 | 590 | 06/10/2021 | 600 |
    #| AMC | 05/15/2021 | 24 |  |  |
    #| DAL | 02/21/2021 | 37 |  |  |
    #| ZM | 03/04/2021 | 367 | 05/10/2021 | 363 |
