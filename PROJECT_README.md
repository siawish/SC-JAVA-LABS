# Banking System Project - Lab 06
**Student**: Malik Muhammad Sisaiwsh  
**Course**: Advanced Object-Oriented System Design & Data Management  
**Total Marks**: 6  

## Project Structure

### 📁 BasicBankingSystem (Task 1 - 4 Marks)
**Core ATM & Account Management System**

**Features**:
- Customer authentication with PIN security
- Multiple account types (Savings & Checking) with business rules
- Transaction logging with comprehensive history
- Account blocking after failed login attempts
- Receipt generation for all operations
- Polymorphic account operations

**Run Command**: `java -cp BasicBankingSystem Main`

### 📁 AdvancedBankingSystem (Task 2 - 2 Marks)
**Complete Banking Solution with Transfers & Administration**

**Additional Features**:
- Inter-account fund transfers (between own accounts)
- Cross-customer fund transfers (to other customers)
- Bank administrator interface with full management
- Account creation and customer unblocking
- Enhanced transaction tracking with transfer details

**Run Command**: `java -cp AdvancedBankingSystem Main`

## Login Credentials

### Customer Accounts
- **C001**, PIN: **1234** - Has Savings ($2000) + Checking ($1500)
- **C002**, PIN: **5678** - Has Savings ($3000) + Checking ($800)  
- **C003**, PIN: **9999** - Has Savings ($5000)

### Administrator (AdvancedBankingSystem only)
- Username: **admin**, Password: **password**

## Account Rules
- **Savings Account**: Minimum balance $500 required
- **Checking Account**: Overdraft limit $1000 (can go negative)
- **Security**: 3 failed PIN attempts = account blocked

## OOP Concepts Demonstrated

### Inheritance
```
BankAccount (Abstract)
├── SavingsAccount (minimum balance rules)
└── CheckingAccount (overdraft rules)
```

### Polymorphism
- Same `deposit()` and `withdraw()` methods behave differently per account type
- ATM works with BankAccount references regardless of concrete type

### Data Structures
- **HashMap**: Fast O(1) customer and account lookups
- **ArrayList**: Transaction history and account collections
- **Enums**: Type-safe transaction types and statuses

### Design Patterns
- **Template Method**: BankAccount defines structure, subclasses implement specifics
- **Repository**: Bank class centralizes all data management
- **Facade**: ATM provides simple interface to complex banking operations

## Quick Test Sequences

### BasicBankingSystem Test
```
1 → C001 → 1234 → 1 → 1 → 2 → 500 → 3 → 200 → 4 → 5 → 2 → 2
```

### AdvancedBankingSystem Test
```
Customer: 1 → C001 → 1234 → 2 → 1 → 2 → 300 → 4
Admin: 2 → admin → password → 1 → 3 → C003 → 1 → 1000 → 5 → 3
```

## Technical Implementation

### Error Handling
- Input validation with try-catch blocks
- Business rule enforcement with status tracking
- Graceful failure recovery with user feedback

### Security Features
- PIN-based authentication with attempt limiting
- Account status management (ACTIVE/BLOCKED/CLOSED)
- Transaction logging for audit trails

### Extensibility
- Easy addition of new account types through inheritance
- Modular design supports feature expansion
- Clean separation of concerns between classes

This project demonstrates comprehensive understanding of advanced OOP principles, data structures, and real-world software design patterns in a practical banking application.