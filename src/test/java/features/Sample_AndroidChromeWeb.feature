Feature: Login and add items to cart on SauceDemo

  @Android-Chrome
  Scenario Outline: User is able to add items to cart after successful login
      Given User is on the SauceDemo login page in Chrome Browser
      When User logs in with username "<username>" and password "<password>" in Chrome Browser
      Then User is able to login successfully in Chrome Browser
      When User adds an item to the cart in Chrome Browser
      Then The item is added to the cart successfully in Chrome Browser

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |
