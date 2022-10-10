Sample Selenium Project for (https://web2.0calc.com/): Calculator Test

## Project design

- Build tool: Maven
- Main design patterns:
    - Page Object
    - Page Factory
    - Fluent API

## Build & Run

- Prerequisites:
    - Chrome web browser installed
    - JDK >= 8
- Checkout project with GIT: `git clone https://bitbucket.org/Agata_05/calculator-test.git`
- From the project directory run command: `mvnw clean test`

## Open Project with Intellij

- Install [Lombok plugin](https://plugins.jetbrains.com/plugin/6317-lombok)
- Add "Framework Support" for Maven (if not detected automatically)

## Remarks for project

- The test suite has been prepared just for Chrome browser for brevity. In the production scenario different browsers
  would be introduced using profiles or test parameters. Remote driver would be used to utilize selenium grid server.
- Java 8 has been used just in order to assure maximum backwards compatibility for the runtime environment. A
  recommended JDK would be the newest LTS version e.i. 17 as of now.
- Operations in calculator can be entered in 2 ways: via keypad or directly via display. Test cases of Calculator Page
  contain tests of operations entered by keypad only. In the production scenario both ways should be covered.

# Main external libraries

- [Selenium 3](https://www.selenium.dev/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [AssertJ](https://joel-costigliola.github.io/assertj/)

# Code quality

- Checkstyle
- SonarLint
- SpotBugs/FindBugs