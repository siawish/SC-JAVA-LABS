# Advanced Banking System - Design Documentation

## Task 2 New Features Implementation

### 1. Inter-Account Funds Transfer (Within Same Customer)

**Implementation Logic:**
- Customer selects source and destination accounts from their own account list
- System validates that both accounts belong to the same customer
- Transfer uses the existing `transferFunds()` method in Bank class
- Transaction history records both TRANSFER_OUT and TRANSFER_IN entries
- Validation ensures sufficient funds and account-specific rules (minimum balance, overdraft limits)

**Key Classes Involved:**
- `ATM.interAccountTransfer()` - User interface for account selection
- `Bank.transferFunds()` - Core transfer logic with validation
- `BankAccount.addTransferTransaction()` - Records transfer transactions

### 2. Cross-Customer Funds Transfer (Between Different Customers)

**Implementation Logic:**
- Customer inputs destination account number manually
- System validates destination account exists and is active
- Prevents transfers to customer's own accounts (redirects to inter-account transfer)
- Rigorous validation: account existence, active status, sufficient funds
- Same transfer mechanism as inter-account but with cross-customer validation

**Key Validation Steps:**
1. Destination account exists in bank's account HashMap
2. Destination account status is ACTIVE
3. Destination account belongs to different customer
4. Source account has sufficient funds considering account rules

### 3. Bank Administrator Interface

**Architectural Design:**
- **Separate Class**: `BankAdministrator` - distinct from customer ATM operations
- **Security Context**: Hardcoded admin credentials (admin/password)
- **Modular Design**: Admin functions completely separated from customer operations
- **Clean Interface**: ATM delegates admin login to BankAdministrator class

**Admin Functions Implementation:**

#### View All Customers
- Retrieves all customers from Bank's HashMap
- Displays customer ID, name, status, and associated account numbers
- Formatted output for easy reading

#### View All Accounts
- Retrieves all accounts from Bank's HashMap
- Tabular display showing account number, type, customer ID, balance, and status
- Efficient data retrieval using Bank's centralized data management

#### Create New Account
- Validates customer existence before account creation
- Enforces business rules (minimum balance for savings accounts)
- Generates unique account numbers using Bank's counter
- Updates both customer's account list and bank's account HashMap

#### Unblock Customer
- Locates customer by ID
- Checks current blocking status
- Confirmation prompt for security
- Reactivates customer and all associated accounts
- Uses Bank's centralized unblock method

### 4. Code Refactoring and Modularity

**Refactoring Improvements:**
- **Separation of Concerns**: Admin functions moved to dedicated BankAdministrator class
- **Single Responsibility**: ATM handles customer operations, BankAdministrator handles admin operations
- **Code Reuse**: Common validation logic centralized in Bank class
- **Helper Methods**: Input validation methods shared between classes

**Modular Architecture:**
```
ATM (Customer Interface)
├── Customer Login & Operations
├── Inter-Account Transfers
├── Cross-Customer Transfers
└── Delegates to BankAdministrator

BankAdministrator (Admin Interface)
├── Admin Authentication
├── Customer Management
├── Account Management
└── System Administration

Bank (Central Data Management)
├── Customer & Account Storage
├── Transfer Operations
├── Validation Logic
└── Data Retrieval Methods
```

### 5. Efficiency Optimizations

**Data Retrieval Optimization:**
- **HashMap Usage**: O(1) lookup for customers and accounts by ID
- **Centralized Queries**: Bank class provides optimized methods for admin queries
- **Lazy Loading**: Transaction history loaded only when requested
- **Efficient Searches**: Account validation uses direct HashMap lookup instead of iteration

**Transfer Operation Efficiency:**
- **Direct Account Access**: Uses account number for O(1) lookup
- **Minimal Validation**: Only necessary checks performed
- **Atomic Operations**: Transfer operations maintain data consistency
- **Optimized Transaction Logging**: Efficient ArrayList operations for history

### 6. Security Context Differentiation

**Customer Security:**
- PIN-based authentication
- Account blocking after failed attempts
- Session-based access to own accounts only
- Transaction-level validation

**Administrator Security:**
- Separate hardcoded credentials
- System-wide access privileges
- Ability to override customer blocks
- Administrative audit trail

### 7. Design Patterns Implemented

**Facade Pattern**: ATM provides simplified interface to complex banking operations
**Delegation Pattern**: ATM delegates admin functions to BankAdministrator
**Repository Pattern**: Bank class centralizes all data access
**Template Method**: BankAccount defines common structure, subclasses implement specifics

### 8. Error Handling Strategy

**Input Validation:**
- Try-catch blocks for numeric input parsing
- Range validation for menu choices
- Format validation for account numbers

**Business Logic Validation:**
- Account existence checks
- Balance and limit validations
- Status checks (active/blocked accounts)
- Customer ownership verification

**Graceful Error Recovery:**
- User-friendly error messages
- System continues operation after errors
- Clear guidance for corrective actions
- Audit trail for failed operations

This design ensures a robust, scalable, and maintainable banking system with clear separation between customer and administrative functionalities.