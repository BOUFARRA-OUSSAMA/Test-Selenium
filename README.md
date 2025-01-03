# WebDriver Test Suite

This project contains a set of automated tests for a web application using Selenium WebDriver and JUnit 5. The tests are written in Java and are designed to verify the functionality of the login and registration features of the application.

## Prerequisites

- Java Development Kit (JDK) 21
- Maven
- ChromeDriver
- Google Chrome browser


## Setup

1. **Install ChromeDriver**:
   Download ChromeDriver from [here](https://sites.google.com/a/chromium.org/chromedriver/downloads) and place it in a directory of your choice. Update the path in the `setUp` method of each test class to point to your ChromeDriver executable.

2. **Install Dependencies**:
   Run the following command to install the required dependencies:
   ```sh
   mvn clean install
Running the Tests
You can run the tests using Maven. Navigate to the project root directory and execute the following command:

Test Classes
MainTest.java
This class contains tests for the login and registration functionalities.

testValidLogin: Verifies that a user can log in with valid credentials.
testInvalidLogin: Verifies that an error message is displayed when logging in with invalid credentials.
testEmptyUsername: Verifies that an error message is displayed when the username field is left empty.
testEmptyPassword: Verifies that an error message is displayed when the password field is left empty.
testBothFieldsEmpty: Verifies that an error message is displayed when both the username and password fields are left empty.
testRegisterWithValidDetails: Verifies that a user can register with valid details.
FunctionalTests.java
This class contains a functional test that combines login and library search functionalities.

testLoginAndLibrarySearch: Verifies that a user can log in and search for a book in the library.
SecurityTests.java
This class contains security tests to check for vulnerabilities.

testSQLInjectionInLogin: Verifies that SQL injection is not possible in the login form.
testXSSInRegistration: Verifies that cross-site scripting (XSS) is not possible in the registration form.
LoginPageTest.java
This class contains additional tests for the login page.

testValidLogin: Verifies that a user can log in with valid credentials.
testEmptyFields: Verifies that an error message is displayed when both the username and password fields are left empty.
testPasswordVisibilityToggle: Verifies that the password visibility toggle button works correctly.
Notes
The tests use Chrome in headless mode to run without displaying the browser window.
Adjust the Thread.sleep durations based on the actual behavior of your application.
Ensure that the application is running locally at http://localhost:4200 before running the tests.
