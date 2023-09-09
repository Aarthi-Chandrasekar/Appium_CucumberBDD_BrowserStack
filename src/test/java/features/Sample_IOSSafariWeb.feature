Feature: Login and add items to cart on SauceDemo

  @iOS_Safari
   Scenario Outline: User is able to add items to cart after successful login
        Given User is on the SauceDemo login page in Safari Browser
        When User logs in with username "<username>" and password "<password>" in Safari Browser
        Then User is able to login successfully in Safari Browser
        When User adds an item to the cart in Safari Browser
        Then The item is added to the cart successfully in Safari Browser

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |
