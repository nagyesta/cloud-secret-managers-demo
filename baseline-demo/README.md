# Baseline test example

The project in this folder contains a Spring Boot application, which is storing Database connection information
in the application configuration committed in git. THIS IS A BAD PRACTICE, PLEASE NEVER DO THIS! COMMITTING YOUR
PRODUCTION CREDENTIALS IN GIT IS A SERIOUS SECURITY ISSUE.

The configuration shows how we can rely on Docker Compose to run a local MySql instance to demonstrate that the
connection was really successful.

# Getting Started

## Prerequisites

- Java 11 installed and configured properly
- Docker installed and available from CLI
- Recommended OS: Unix/Linux

## Instructions
Starting the project

```bash
./gradlew bootRun
```

In the output, look for these lines to see whether it worked:

```
*****************
*               *
* Baseline Demo *
*               *
*****************

MySQL Community Server - GPL - 8.0.32
```
