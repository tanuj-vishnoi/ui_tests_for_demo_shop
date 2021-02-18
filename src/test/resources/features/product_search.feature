@regression @smoke
Feature: Product Search

Scenario: Test Verify in case of valid search system display the product or list of product
Given User launches the application url
When user enters a valid product search term
And clicks on search button
Then more than one product is displayed
And each product name conatins the search term