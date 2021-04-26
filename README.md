  <h3 align="center">Automation Framework</h3>

  <p align="center">
   A high level framework which can be extendable easily.
   The framework can be easily run in different environments like test,SIT,live etc 
   and different browsers like chrome browser, fireFox, remoteWebdriver for grid/Browserstack/saucelabs or mobile devices.
</p>

<!-- ABOUT THE PROJECT -->
## About The Project
* Location of the cucumber BDD file : \src\test\resources\features\Desktop
* Location of the step definitions : src\test\java\com\qualiTest\test\stepDefinitions\web
* Location of the PageObjects : src\test\java\com\qualiTest\test\pageObjects
* Location of the TestData : src\main\resources\TestData\Live
* Location of configs like browser details : src\main\resources\profiles\live
* Location of Generated Cucumber html reports : target\cucumber-report\cucumber-html-reports

### Built With

This section should list any major frameworks that you built your project using. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.
* Selenium
* Cucumber
* Maven

### Prerequisites

* maven
* java
* please make sure the chrome driver or other driver match the chrome version you are in 
  the drivers are located under tools/drivers
  
<!-- USAGE EXAMPLES -->
## Usage
The tests can be run in multiple ways using the below commands

1) mvn clean install -P live
when the browser parameter is not passed, it will be picked from the default environment config properties file 
eg: src/main/resources/profiles/live/

2) Browser detais or other details of config can be passed from the run time parameters like
mvn clean install -P live -Dbrowser="chrome"