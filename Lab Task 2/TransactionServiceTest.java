/**
 * Lightweight checks for {@link TransactionService} without a test framework.
 * Run with: {@code java TransactionServiceTest}
 *
 * <p>Helps maintainers verify deposit/withdraw rules after code changes.
 */
public class TransactionServiceTest {

    public static void main(String[] args) {
        int failures = 0;

        failures += checkDepositValid();
        failures += checkDepositInvalid();
        failures += checkWithdrawValid();
        failures += checkWithdrawInvalidAmount();
        failures += checkWithdrawInsufficientFunds();

        if (failures == 0) {
            System.out.println("TransactionServiceTest: all checks passed.");
        } else {
            System.out.println("TransactionServiceTest: " + failures + " check(s) failed.");
        }
    }

    private static int checkDepositValid() {
        TransactionService service = newService(1000);
        TransactionResult result = service.deposit(250);

        return assertCheck(
                "deposit valid amount",
                result == TransactionResult.SUCCESS && service.getBalance() == 1250);
    }

    private static int checkDepositInvalid() {
        TransactionService service = newService(1000);
        TransactionResult result = service.deposit(0);

        return assertCheck(
                "deposit invalid amount",
                result == TransactionResult.INVALID_AMOUNT && service.getBalance() == 1000);
    }

    private static int checkWithdrawValid() {
        TransactionService service = newService(1000);
        TransactionResult result = service.withdraw(400);

        return assertCheck(
                "withdraw valid amount",
                result == TransactionResult.SUCCESS && service.getBalance() == 600);
    }

    private static int checkWithdrawInvalidAmount() {
        TransactionService service = newService(1000);
        TransactionResult result = service.withdraw(-10);

        return assertCheck(
                "withdraw invalid amount",
                result == TransactionResult.INVALID_AMOUNT && service.getBalance() == 1000);
    }

    private static int checkWithdrawInsufficientFunds() {
        TransactionService service = newService(1000);
        TransactionResult result = service.withdraw(5000);

        return assertCheck(
                "withdraw insufficient funds",
                result == TransactionResult.INSUFFICIENT_FUNDS && service.getBalance() == 1000);
    }

    private static TransactionService newService(double balance) {
        Account account = new Account("test", "0000", balance);
        return new TransactionService(account);
    }

    private static int assertCheck(String name, boolean passed) {
        if (passed) {
            System.out.println("PASS: " + name);
            return 0;
        }

        System.out.println("FAIL: " + name);
        return 1;
    }
}
