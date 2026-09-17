/**
 * Performs deposit, withdrawal, and balance operations without any console I/O.
 * Returns {@link TransactionResult} so the UI layer (or a test) can decide
 * what message to show.
 */
public class TransactionService {

    private final Account account;

    public TransactionService(Account account) {
        this.account = account;
    }

    public double getBalance() {
        return account.getBalance();
    }

    public TransactionResult deposit(double amount) {
        if (!account.deposit(amount)) {
            return TransactionResult.INVALID_AMOUNT;
        }
        return TransactionResult.SUCCESS;
    }

    /**
     * Validates amount and funds, then withdraws in one step.
     * Callers do not need separate pre-checks for normal use.
     */
    public TransactionResult withdraw(double amount) {
        if (amount <= 0) {
            return TransactionResult.INVALID_AMOUNT;
        }

        if (!account.hasSufficientFunds(amount)) {
            return TransactionResult.INSUFFICIENT_FUNDS;
        }

        account.withdraw(amount);
        return TransactionResult.SUCCESS;
    }
}
