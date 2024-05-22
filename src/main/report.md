# Online Banking System

## Introduction

The Online Banking System is a web application that allows users to manage their bank accounts. Users can perform various transactions such as opening new accounts, depositing money, withdrawing money, and transferring funds between accounts. This project is built using Spring Boot, Thymeleaf, and Bootstrap 5.

## Features

- Open a new bank account (Savings or Checking)
- Deposit money into an account
- Withdraw money from an account
- Transfer funds between accounts

## Technologies Used

- Java
- Spring Boot
- Thymeleaf
- Bootstrap 5
- H2 Database (for development purposes)
- Maven

## Prerequisites

- Java 11 or higher
- Maven
- An IDE or text editor (IntelliJ IDEA, Eclipse, VSCode, etc.)

## Setup and Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/online-banking-system.git
    cd online-banking-system
    ```

2. **Build the project:**

    ```bash
    mvn clean install
    ```

3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

4. **Access the application:**

   Open your web browser and navigate to `http://localhost:8080`.

## Project Structure

- `src/main/java/org/example/online_banking_system/`
    - `controller/` - Contains the Spring MVC controllers.
    - `model/` - Contains the entity classes.
    - `repository/` - Contains the repository interfaces.
    - `service/` - Contains the service classes.
    - `dto/` - Contains the data transfer objects (DTOs).
    - `OnlineBankingSystemApplication.java` - The main Spring Boot application class.

- `src/main/resources/`
    - `templates/` - Contains the Thymeleaf templates.
    - `application.properties` - Configuration file for the Spring Boot application.

## Usage

### Home Page

The home page provides navigation to the different functionalities of the banking system.

### Open New Account

- Navigate to the "Open New Account" page.
- Select the account type (Savings or Checking).
- Enter the initial deposit amount.
- Click "Open Account" to create a new account.

### Deposit Money

- Navigate to the "Deposit Money" page.
- Enter the account number and the amount to deposit.
- Click "Deposit" to deposit the money into the account.

### Withdraw Money

- Navigate to the "Withdraw Money" page.
- Enter the account number and the amount to withdraw.
- Click "Withdraw" to withdraw the money from the account.

### Transfer Money

- Navigate to the "Transfer Money" page.
- Enter the source account number, destination account number, and the amount to transfer.
- Click "Transfer" to transfer the money between the accounts.

## Code Overview

### Controller Classes

- **AccountController**: Handles account-related operations such as opening a new account.
- **TransactionController**: Handles transaction-related operations such as depositing, withdrawing, and transferring money.

### Service Classes

- **AccountService**: Contains the business logic for account operations.
- **TransactionService**: Contains the business logic for transaction operations.

### Repository Interfaces

- **AccountRepository**: Provides database access methods for `Account` entities.

### Entity Classes

- **Account**: Represents a bank account entity with attributes such as `accountId`, `accountNumber`, `accountType`, and `balance`.

### DTO Classes

- **TransferForm**: A data transfer object for handling transfer requests.

## Error Handling

The application handles various errors such as:

- Insufficient funds during withdrawals or transfers.
- Account not found for the given account number.

## Contributing

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.