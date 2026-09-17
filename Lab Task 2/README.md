# Modular ATM — Maintainer Guide

Simple console ATM used for a Software Engineering lab. The project is organized so common maintenance tasks stay localized.

## How to run

```bash
javac *.java
java FinalProject
```

Default login (see `AtmConfig`): username `huzaifa`, PIN `1234`.

## How to run transaction checks

```bash
java TransactionServiceTest
```

These checks exercise deposit/withdraw rules without console login.

## Class overview

| Class | Responsibility |
|---|---|
| `FinalProject` | Starts the app and wires dependencies |
| `AtmController` | Login gate, menu loop, routes operations |
| `Authenticator` | Username + PIN attempts |
| `Account` | Encapsulated account data |
| `TransactionService` | Deposit / withdraw / balance rules |
| `TransactionResult` | Success / invalid amount / insufficient funds |
| `ConsoleUI` | Console input and output |
| `ReceiptPrinter` | Withdrawal receipt |
| `AtmConfig` | Defaults, limits, menu option numbers |
| `AtmMessages` | User-facing text |

## Where to change common things

- **Demo account / PIN limit / menu numbers** → `AtmConfig`
- **Wording of prompts and messages** → `AtmMessages`
- **Money rules** → `Account` and `TransactionService`
- **Add a new menu operation** → follow the steps in `AtmController` javadoc

## Design notes for maintainers

- UI code does not own balance rules; transaction results are returned as `TransactionResult`.
- Dependencies are constructor-injected from `FinalProject` (no global `Scanner`).
- Non-numeric menu/amount input is handled safely and mapped to the existing invalid-input behavior.
