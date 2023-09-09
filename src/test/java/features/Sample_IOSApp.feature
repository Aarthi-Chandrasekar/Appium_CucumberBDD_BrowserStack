Feature: Testing General IOS Store App
  @iOS-NativeApp
    Scenario Outline: User is able to add items to cart after successful login in safari
      Given the app is opened in iPhone
      When User logs in app with username "<username>" and password "<password>" in iPhone
      Then User is able to login successfully in iPhone

     Examples:
          | username      | password     |
          | standard_user | secret_sauce |