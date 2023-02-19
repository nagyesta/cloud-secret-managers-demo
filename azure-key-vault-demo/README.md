# Azure Key Vault Local test example

The project in this folder contains a Spring Boot application, which is fetching Database connection information
from Azure Key Vault under normal circumstances.

As Local execution cannot always rely on the real service, the DEV profile changes show how we can rely on Docker
Compose to run a test double replacing Azure Key Vault, as well as a local MySql instance to demonstrate that the
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
************************
*                      *
* Azure Key Vault Demo *
*                      *
************************

MySQL Community Server - GPL - 8.0.32
```
