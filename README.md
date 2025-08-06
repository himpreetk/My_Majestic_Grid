# My_Majestic_Grid

    My_Majestic_Grid is a full-stack web application that simulates a lightweight admin system for managing
    a Global Car Brand List. Designed for testing and demonstration purposes, the app implements both 
    authentication and core CRUD operations through a responsive dashboard.

    🔐 Core Features

        🔑 Login Functionality
                Users authenticate using secure credentials to access the protected dashboard.

                ![Frontend Screenshot](frontend/front%20end%20screenshots/f3.png)

        📊 Dashboard Interface
                Authenticated users can view and interact with a table of global car brands in a dynamic grid layout.

        🛠 CRUD Operations
                - Create: Add a new car brand entry with key attributes
                - Read: View the list of all existing global brands
                - Update: Modify car brand information inline or via a form
                - Delete: Remove obsolete or unwanted brand entries

🌐 Live Application

    Frontend: https://my-majestic-grid-frontend.vercel.app

    Backend: https://my-majestic-grid-backend.onrender.com

📂 Repository Structure

    My_Majestic_Grid/
    │
    ├── frontend/ # React.js frontend (npm start)
    ├── backend/  # Node.js backend (node index.js)
    ├── tests/
    │   ├── api-test/ # API test automation
    │   │   ├── src/main/java/utils/ExtentReportManager
    │   │   ├── src/test/java/base/BaseTest
    │   │   ├── src/test/java/tests/LoginApiTest, DashboardApiTest
    │   │   ├── src/test/java/utils/TestData
    │   │   ├── testng.xml
    │   │   └── test-output/ExtentReport.html
    │   └── ui-test/  # UI test automation
    │       ├── src/main/java/base/BaseTest
    │       ├── src/main/java/pages/LoginPage, DashboardPage
    │       ├── src/test/java/tests/LoginUiTest, DashboardUiTest
    │       ├── testng.xml
    │       └── test-output/ExtentReport.html
    ├── documents/
    │   ├── Test Planning Document
    │   ├── Test Cases Document
    │   ├── Project Setup Document
    │   └── QA Sign-off Document
    └── README.md


🛠 Tools & Framework Versions

    | Category           | Tool / Framework         | Version     |
    |--------------------|--------------------------|-------------|
    | Backend            | Node.js                  | 24.4.1      |
    | Package Manager    | npm                      | 11.4.2      |
    | Frontend           | React.js                 | —           |
    | Database           | MongoDB                  | —           |
    | Test Automation    | Selenium WebDriver       | 4.21.0      |
    | API Automation     | Rest Assured             | —           |
    | Test Runner        | TestNG                   | 7.9.0       |
    | Report Generator   | Extent Reports           | 5.1.1       |
    | Java               | JDK                      | 21.0.2      |
    | Build Tool         | Maven                    | —           |
    | Browser Driver     | WebDriver Manager        | 5.7.0       |
    | IDEs               | Eclipse, VSCode          | 4.43.0 / 1.98.2 |
   

🧪 Test Execution Instructions

    API Tests
      1.  Navigate to: tests/api-test
      2.  Run: mvn clean test
      3.  View report at: test-output/ExtentReport.html

    UI Tests
      1.  Navigate to: tests/ui-test
      2.  Run: mvn clean test
      3.  View report at: test-output/ExtentReport.html

📚 QA Documentation Included

    Located in the documents/ folder:

       - Test Planning Document
       - Test Cases Document
       - Project Setup Document
       - QA Sign-off Document

🧩 Functional Highlights

    Frontend
       - Built with React.js
       - Grid-based UI with dynamic data rendering
       - API integration and routing

    Backend
       - Node.js and Express.js
       - MongoDB via Mongoose
       - REST API with structured controllers and models

    Automation
       - Modular test architecture (POM + Base)
       - Extent Reports for visual reporting
       - Separate suites for API and UI validations

🎓 Academic and Practical Value

    This project demonstrates:

       - Full-stack software engineering concepts
       - Practical QA automation strategies
       - Test documentation lifecycle
       - CI/CD-readiness and automation
       
    Ideal for academic portfolios, QA bootcamps, and hands-on learning.

🤝 Contributions

    You're welcome to contribute by opening issues or submitting pull requests for:

       - Test case expansion
       - New features
       - Code quality improvements
       - Documentation enhancements

📬 Contact

    Himpreet Kaur
       Email: kaurhimpreet95@gmail.com
       LinkedIn: https://linkedin.com/in/your-profile
       GitHub: https://github.com/your-username
