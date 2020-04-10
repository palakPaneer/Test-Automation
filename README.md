# Automation Tests for DeckOfCards API

2 APIs will be tested with Rest Assured library in this project, the code could will be the foundation for testing all of the http://deckofcardsapi.com/ APIs.

## Create a new deck of cards

Code is in src/test/java/ApiTests/NewDeckTests.java

### 1. GET https://deckofcardsapi.com/api/deck/new/

### 2. Support adding Jokers with a POST

Note: Requirement asked to test POST endpoint with jokers_enabled=true, but Rest Assured doesn't support 301 redirect for POST requests. I tried several ways to modify redirect config, didn't work, so I tested GET endpoint instead. <br />Ideally a manual redirect logic can help test this, by:
            <br /> 1) taking Location from response of the first call
            <br /> 2) Merging cookies from one request to another
            <br /> 3) Make new request with needed headers\cookies to a extracted location and then verifying that redirect intention was correct.
           <br /> (According to http://biercoff.com/why-rest-assured-doesnt-redirect-post-requests/)
           <br /> Due to time limit, didn't implement such redirect logic.
           
## Draw one or more cards from a deck

Code is in src/test/java/ApiTests/DrawCardTests.java

### GET https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/

In this part I checked 2 scenarios: there are enough cards to draw from pile; not enough cards to draw.

## API test automation for deckOfCards API

Run unit tests by `mvn package`
