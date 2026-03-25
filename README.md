# AAA Life SDET Technical Assessment

## Overview

This project contains a small API test suite and a UI smoke test using Java, TestNG, Selenium, and RestAssured.

The focus was to build a clean, maintainable, and reliable test solution within the given time constraints.

---

## Tech Stack

* Java
* TestNG
* Selenium WebDriver
* RestAssured
* Maven

---

## Project Structure

* base → setup classes
* pages → UI Page Objects
* tests.api → API tests
* tests.ui → UI tests
* utils → config and test data

---

## Setup Instructions

### Prerequisites

* Java 11+
* Maven
* Chrome browser
* ChromeDriver

---

### Run Tests

Run all tests:
mvn clean test

Or run testng.xml from IDE

---

## API Test Coverage

Endpoints:

* Auth
* Create Booking
* Get Booking

Scenarios:

* Positive
* Negative (missing fields, invalid ID)
* Boundary (price = 0)

Data:

* JSON-based parameterization

Validation:

* Status codes
* Response body using JsonPath

---

## UI Test Coverage

Flow:

* Login
* Validate inventory
* Add item to cart
* Verify item in cart

---

## Design Decisions

* Used Page Object Model for UI
* Used Base classes to avoid duplication
* Used Map for request body instead of raw JSON
* Used JSON file for test data

---

## Stability

* Disabled Chrome password popup
* Used explicit waits
* Avoided Thread.sleep
* Added screenshot on failure

---

## Next Steps

* Add schema validation
* Add reporting
* Add CI integration
* Expand UI negative tests

---
