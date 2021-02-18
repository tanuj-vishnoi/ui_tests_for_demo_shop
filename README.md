# Project Directory

## src/test/java

#### browser factory

it create instance of the browser as mentioned in the config file

#### junit runner

this is the entry point . Its a junit runner to execute all the feature file present in the target feature folder

### Keywords

Its tha page object model presentation of the web page. Contains all the actions

### Page Objects:

conatins the locator as per the page object model

### Stepdefs

contains the all step destinations  of the steps mention in the feature files.

## src/test/resources

### features

contains all the feature files

### test data

contains yaml file for readind test data from file

## config property

browser= define browser chrome or firefox

browserversion= define version otherwise let it empty to run it whatever browser version you have

implicitwait= time out of selenium session

## How run

Through IDE : Go to junitrunner package-> run RunCucumberTest using junit

or command line us run command in cmd/terminal : mvn clean test

## Where to see resutls

target/cucumber/cucumber-html-reports/overview-features.html
