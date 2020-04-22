# API Automation Test Suite

<br/>This automated test suite covers all the test cases mentioned in Task 2 of technical challenge.

# Libraries Used

* RestAssured:
    * To incorporate API tests.
* Cucumber:
    * To perform parallel execution of test.
* Log4J:
    * To perform logging across test application for all test cases on output stream and in a file.
    
# Features:

* Logging
    - Implemented using Log4J to log.
* Generation human readable report
    - HTML Reports are available in the "target\cucumber-html-reports\overview-features.html" directory having details of each test case execution.
    
* Client model architecture
    -Rest assure client consist all type of request 
    (e.g get,post etc) and Model class contains the response to valid output.
    
* Configurator:
  * run tests in parallel mode;  

# Steps to execute the project:

* Method 1: Command Line:
    * Execute via command line by entering following command:
      - Go to project workspace
      
    ```bash
    mvn clean install
   ```
      - Refer the folder to see HTML report :target\cucumber-html-reports\overview-features.html
 
* Method 2: Feature file:
    * Right click on the `Feature` file: click on Run as >> Cucumber feature file
