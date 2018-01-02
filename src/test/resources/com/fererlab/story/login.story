Meta:

Narrative:
As a user of the web site
I want to login to site
So that I can use the site

Scenario: login to web site
Given a login operation
When I type username john
When I type password 123123
When I call login
Then I should get a successful login response