**Mobile Automation Framework with Appium and Browserstack**


**About**:

The Appium-BrowserStack Framework is an open source, Java-based solution for automating mobile app testing across Android and iOS devices using Appium and BrowserStack. 
It supports parallel tests on Android and iOS devices and records test sessions and automatically saves video recordings. 
Integrated with Allure for enhanced reporting. We've chosen to utilize BrowserStack over Perfecto and Sauce Labs for our cloud automation due to its extensive feature set and favorable free subscription options.

**Features**:

**Appium & BrowserStack Integration**: Easily automate mobile apps using Appium and run them on BrowserStack's cloud setup.

**Support for Both Android and iOS**: Test on both Android APKs and iOS IPA applications.

**Parallel Test Execution**: Boost testing speed by running tests on multiple devices at the same time. This is achieved using an ExecutorService that handles parallel tasks.

**Automated Video Recording and Automated Downloads**: Utilizing VideoUtility.downloadAllVideos(), all video sessions are automatically downloaded post testing.

**Sample Tests Provided**: The framework comes with ready-to-use tests for both Android and iOS, covering native apps and web interfaces. These tests focus on a sample retail application for real-world testing.

**Configurable Setup**: Set your testing preferences like device, platform, and application under test in the capabilities.json file.

Comes with a sample Android APK and sample iOS IPA for testing out the retail mobile app on both platforms.

**Steps to Run the Framework**:

**Setup on BrowserStack**: Begin by creating an account on BrowserStack and retrieve the Username and AccessKey.

**Application upload**: For Android, upload the apk file under the app automation section of Browserstack and retrieve the unique application URL. For iOS, upload an IPA file within BrowserStack's app automation and note down the distinctive application URL.

**Configuration**: 

Replace username, key with your BrowserStack username and key in capabilities.json and replace URLs (apk and ios) from your recent uploads on BrowserStack.

Configure devices, platforms, and other parameters in the capabilities.json file.

**Allure Setup**: Follow instructions from Allure's official site (https://docs.qameta.io/allure/)

**Execution**:

Run the TestRunner.java 

Post execution, for an Allure report, run: allure serve allure-results.
