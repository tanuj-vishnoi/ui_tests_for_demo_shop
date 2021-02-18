@regression
Feature: Login page verifications

 Scenario: Verify User Can navigate to the Login Page from the Landing using myaccount login option
  	Given User launches the application url
  	When Clicks on myaccount option from header
  	And Select the login page
  	Then User should navigates to login page

Scenario: Verify a valid registered user can login into the system using valid set of credentials
  	Given User launches the login page url
  	When User enters a valid username and password
  	And Clicks on the login button
  	Then User should able to see my account option "View your order history"
  	And page title should be "My Account"