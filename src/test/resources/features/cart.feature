@regression 
Feature: Shopping Cart 

Scenario:
Verify User can see product description title price on shopping cart page 
	Given User launches the application url 
	When user enters a valid product search term 
	And clicks on search button 
	Then more than one product is displayed
	When user clicks add to cart option
	Then product should be added in cart
	When user clicks on view cart option
	Then there should be only one product in shopping table
	Then product price and title should be displayed in the shopping cart table