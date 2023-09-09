Feature: Testing General Store App
  @Android-NativeApp
  Scenario Outline: Open the app and check the title
      Given the app is opened in Android phone
      When User logs in with username "<username>" and country "<country>" and select gender "<gender>" in Android phone
      Then User is able to login successfully and lands on Products page in Android phone

     Examples:
           | username| country | gender|
           | Aarthi | India | female |