# Customer Accounts API

## Table of Contents
- [Customer Accounts API Overview](#customer-accounts-api-overview)
- [Customer Accounts API Endpoints](#customer-accounts-api-endpoints)
    - [Create Account](#create-account)
    - [Get Customer Account Information](#get-customer-account-information)
- [Customer Accounts API Setup](#customer-accounts-api-setup)

# Transactions API

## Table of Contents
- [Transactions API Overview](#transactions-api-overview)
- [Transactions API Endpoints](#transactions-api-endpoints)
    - [Create Transaction](#create-transaction)
    - [Get Transactions by Account ID](#get-transactions-by-account-id)
- [Transactions API Setup](#transactions-api-setup)

# Customer Account Info UI

## Table of Contents
- [Overview](#overview-2)
- [Customer Account Info UI Setup](#customer-account-info-ui-setup)
- [Components](#components)
    - [CreateAccount](#createaccount)
    - [CustomerAccountInfo](#customeraccountinfo)
    - [AccountDetails](#accountdetails)
    - [TransactionDetails](#transactiondetails)
- [Usage](#usage)
- [Notes](#notes)

# Customer Accounts API

## Customer Accounts API Overview

The `customer-accounts-api` is a RESTful API for managing customer accounts. It allows creating new accounts for existing customers and retrieving customer account information including balances and transactions.

## Customer Accounts API Endpoints

### Create Account

```http
POST /api/v1/accounts
```

#### Request Body

```json
{
  "customerId": 1,
  "initialCredit": 100.0
}
```

#### Response

- Status: `201 Created`

### Get Customer Account Information

```http
GET /api/v1/accounts/{customerId}/account-info
```

#### Path Variables

- `customerId`: The ID of the customer.

#### Response

```json
{
  "name": "Gizem",
  "surname": "Egeli",
  "accounts": [
    {
      "id": 1,
      "balance": 3.00,
      "transactions": [
        {
          "accountId": 1,
          "referenceId": "741323e2-7a1f-45b9-8189-1be48ee8c1a0",
          "amount": 100.00,
          "transactionType": "DEPOSIT",
          "description": "Initial Credit",
          "transactionDate": "2024-07-19T14:34:58.513903"
        }
      ]
    }
  ]
}
```

- Status: `200 OK`

## Customer Accounts API Setup

1. Clone the repository.
2. Navigate to the project directory.
3. Ensure you have the following in `src/main/resources/data.sql`:
    ```sql
    INSERT INTO customer (name, surname) VALUES ('Gizem', 'Egeli');
    ```
4. Build the project using Maven:
    ```bash
    mvn clean install
    ```
5. Run the application:
    ```bash
    mvn spring-boot:run
    ```
6. The application uses an H2 in-memory database for development purposes. You can access the H2 console at `http://localhost:8081/h2-console`.

# Transactions API

## Transactions API Overview

The `transactions-api` is a RESTful API for managing transactions within customer accounts. It allows creating new transactions and retrieving transactions by account ID.

## Transactions API Endpoints

### Create Transaction

```http
POST /api/v1/account-transactions
```

#### Request Body

```json
{
  "accountId": 1,
  "amount": 100,
  "transactionType": "DEPOSIT",
  "description": "load balance"
}
```

#### Response

- Status: `201 Created`

### Get Transactions by Account ID

```http
GET /api/v1/account-transactions/{accountId}
```

#### Path Variables

- `accountId`: The ID of the account.

#### Response

```json
[
  {
    "referenceId": "741323e2-7a1f-45b9-8189-1be48ee8c1a0",
    "accountId": 1,
    "amount": 10.00,
    "transactionType": "DEPOSIT",
    "description": "Initial Credit",
    "transactionDate": "2024-07-19T14:34:58.513903"
  }
]
```

- Status: `200 OK`

## Transactions API Setup

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

# Customer Account Info UI

## Overview

The `customer-account-info-ui` is a React-based frontend application for interacting with the `customer-accounts-api` and `transactions-api`. It allows users to create accounts and view account and transaction details.

## Customer Account Info UI Setup

1. Clone the repository.
2. Navigate to the project directory.
3. Install the dependencies:
    ```bash
    npm install
    ```
4. Start the application:
    ```bash
    npm start
    ```

## Components

### CreateAccount

A form for creating a new account.

### CustomerAccountInfo

Displays customer account information and allows users to retrieve account details by customer ID.

### AccountDetails

Displays a list of accounts for the customer.

### TransactionDetails

Displays a list of transactions for a selected account.

## Usage

1. Open the application in your browser at `http://localhost:3000`.
2. Use the form to create a new account by providing a customer ID and an initial credit amount.
3. Retrieve account details by entering a customer ID and clicking "Retrieve Accounts".
4. View account and transaction details displayed in a user-friendly format.

## Notes

- Ensure that the backend services (`customer-accounts-api` and `transactions-api`) are running before starting the UI.
- The default API endpoints are configured for `http://localhost:8081` for the accounts API and `http://localhost:8082` for the transactions API. Adjust these endpoints as needed in the UI configuration.
- The `customer-accounts-api` uses an H2 in-memory database for development. The initial data is loaded from `src/main/resources/data.sql`.
- If the `transactions-api` is not running when you start the `customer-accounts-api`, the Customer Account Info service will not function correctly and will return errors when attempting to retrieve account transactions. Ensure both services are running simultaneously.
- A Postman collection file Account-Transaction-Application.postman_collection.json is included in the root directory for testing the APIs.