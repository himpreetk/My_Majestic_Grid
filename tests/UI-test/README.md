# UI Test Automation - My_Majestic_Grid

     This folder contains the UI test automation framework for the My_Majestic_Grid full-stack application.
     The tests are developed using Java, Selenium WebDriver, and TestNG. The framework is designed using
     the Page Object Model (POM) and generates professional HTML reports using Extent Reports. 

Folder Structure:

    UI-test/
    |
    ├── .settings/ Eclipse project settings
    ├── src/
    │     ├── main/
    │     │      └── java/
    │     │             ├── base/ BaseTest.java (global test setup and teardown)
    │     │             ├── pages/ LoginPage.java, DashboardPage.java (page object files)
    │     │             └── utils/ ExtentReportManager.java, TestListener.java
    │     └── test/
    │            └── java/
    │                    └── tests/ LoginUITest.java, DashboardUITest.java (test scripts)
    |
    ├── target/ Maven build output
    ├── test-output/
    │           ├── screenshots/ Screenshots from failed tests
    │           ├── ExtentReport.html HTML test execution report
    │           ├── index.html TestNG report index
    │           ├── emailable-report.html Email-friendly summary
    │           └── other TestNG files Additional CSS, JS, and log files
    |
    ├── pom.xml Maven configuration file
    ├── testng.xml TestNG test suite configuration
    ├── .classpath and .project Eclipse workspace metadata

Tech Stack:

     - Programming Language: Java (JDK 21.0.2)
     -  Testing Framework: TestNG (version 7.9.0)
     -  UI Automation Tool: Selenium WebDriver (version 4.21.0)
     -  Build Tool: Maven
     -  Reporting: Extent Reports (version 5.1.1)
     -  WebDriver Management: WebDriverManager (version 5.7.0)
     -  IDEs: Eclipse (4.43.0) and VSCode (1.98.2)

Key Features:

      - Page Object Model (POM) architecture
      - BaseTest setup for WebDriver configuration
      - ExtentReport integration for HTML test reporting
      - TestNG Listener for custom logging and screenshot capture
      - Screenshot generation on test failure
      - Supports multiple test scenarios for login and dashboard modules

Running the Tests:

      1. Prerequisites:
          - Java JDK 21 or higher
          - Maven installed
          - Chrome browser installed
          - Navigate to the UI-test directory

      2. Run the tests using the following command:
               -   mvn clean test

      3. After execution, open the following file to view the test report:
               - test-output/ExtentReport.html

Test Scenarios Covered:

     1. Login Functionality:
           - Valid login test
           - Invalid login attempt and error validation

     2. Dashboard:
           - Load dashboard after login
           - CRUD operations on global car brand list (Create, Read, Update, Delete)

Screenshots and Reports:

        - Failed test screenshots are saved in the test-output/screenshots folder
        - Main test execution report is available in test-output/ExtentReport.html
        - Other supporting reports like testng-results.xml and emailable-report.html are also available in 
          the same directory

Author:

      Himpreet Kaur
      Email: kaurhimpreet95@gmail.com
      LinkedIn: https://linkedin.com/in/your-profile
      GitHub: https://github.com/your-username
