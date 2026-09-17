/**
 * Holds account username, PIN, and balance.
 * Contains only account data rules — no console I/O — so it stays easy to test
 * and reuse when the UI changes.
 */
public class Account {

    private final String username;
    private final String pin;
    private double balance;

    public Account(String username, String pin, double balance) {
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    // --- Identity checks ---

    public boolean matchesUsername(String inputUsername) {
        return username.equals(inputUsername);
    }

    public boolean matchesPin(String inputPin) {
        return pin.equals(inputPin);
    }

    // --- Balance access ---

    public double getBalance() {
        return balance;
    }

    public boolean hasSufficientFunds(double amount) {
        return amount <= balance;
    }

    // --- Balance changes ---

    /**
     * @return true if amount is positive and the balance was updated
     */
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        return true;
    }

    /**
     * @return true if amount is valid, funds are available, and the balance was updated
     */
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}
