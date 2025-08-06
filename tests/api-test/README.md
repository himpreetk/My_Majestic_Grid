# API Test Automation - My_Majestic_Grid

     This folder contains the API test automation suite for the My_Majestic_Grid application. The tests are 
     written in Java using the Rest Assured library and TestNG framework. The suite is designed to validate
     backend REST APIs with comprehensive coverage and uses Extent Reports to generate detailed HTML 
     reports after execution.

Folder Structure:

    api-test/
         |
         ├── .github/ GitHub workflows (if any)
         ├── .settings/ Eclipse settings
         ├── src/
         │ ├── main/
         │ │      └── java/
         │ │            └── utils/
         │ │                  └── ExtentReportManager.java Utility for initializing Extent Reports
         │ │
         │ └── test/
         │        └── java/
         │              ├── base/
         │              │      └── BaseTest.java Handles test setup and teardown
         │              │
         │              ├── tests/
         │              │       ├── LoginApiTest.java Contains tests for login API endpoints
         │              │       └── DashboardApiTest.java Contains tests for dashboard-related endpoints
         │              │
         │              └── utils/
         │                      └── TestData.java Central file for managing test data
         |
         ├── target/ Maven build output
         ├── test-output/
         │          ├── ExtentReport.html Executable HTML test report
         │          └── Additional files TestNG report files
         |
         ├── .classpath and .project Eclipse metadata
         ├── .gitignore Git ignored files and folders
         ├── pom.xml Maven project configuration
         ├── testng.xml TestNG test suite runner

Tech Stack:

     - Programming Language: Java (JDK 21.0.2)
     - API Testing Tool: Rest Assured
     - Test Framework: TestNG (version 7.9.0)
     - Reporting Tool: Extent Reports (version 5.1.1)
     - Build Tool: Maven
     - IDEs: Eclipse / VSCode

Test Execution Instructions:

     1. Make sure Java and Maven are installed and configured
     2. Navigate to the api-test directory
     3. Run the command:
           - mvn clean test

     4. Open the report located at:
          - test-output/ExtentReport.html

Test Coverage:

    1. LoginApiTest.java

         - Successful login with valid credentials (happy path)
         - Login with invalid password, email, or missing fields (sad path)
         - Attempt to login with special characters, blank inputs, and incorrect formats (edge cases)

    2. DashboardApiTest.java

         - Valid token usage to access brand list (happy path)
         - Unauthorized access without token or with expired token (sad path)
         - CRUD operations with incomplete payload, long brand names, and invalid IDs (edge cases)

Types of Test Cases Included:

    1. Happy Path Tests:

         - Verify correct inputs produce expected results
         - Ensure API behaves as intended with valid data and flows

    2. Sad Path Tests:

         - Deliberately use incorrect or missing inputs
         - Validate error messages, response codes (e.g., 400, 401, 403)

    3. Edge Case Tests:

         - Test boundary values, empty inputs, malformed requests
         - Validate system behavior under extreme or unexpected conditions

      These tests collectively ensure API robustness, error handling, and consistent behavior under different usage scenarios.

Reporting and Logs:

     - HTML reports are generated using Extent Reports
     - Logs include request/response details, status codes, and validations
     - Report available at test-output/ExtentReport.html
     - Can be used as evidence for QA sign-off or CI/CD test logs

Author:

    Himpreet Kaur
    Email: kaurhimpreet95@gmail.com
    LinkedIn: https://linkedin.com/in/your-profile
    GitHub: https://github.com/your-username

