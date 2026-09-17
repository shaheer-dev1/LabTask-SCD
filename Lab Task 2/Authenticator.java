/**
 * Handles login only: username check, then PIN with a limited number of attempts.
 * The attempt limit comes from configuration so it can be changed in one place.
 */
public class Authenticator {

    private final ConsoleUI ui;
    private final int maxPinAttempts;

    public Authenticator(ConsoleUI ui, int maxPinAttempts) {
        this.ui = ui;
        this.maxPinAttempts = maxPinAttempts;
    }

    public boolean authenticate(Account account) {
        if (!checkUsername(account)) {
            return false;
        }

        return checkPin(account);
    }

    private boolean checkUsername(Account account) {
        String enteredUsername = ui.readUsername();

        if (!account.matchesUsername(enteredUsername)) {
            ui.showMessage(AtmMessages.WRONG_USERNAME);
            return false;
        }

        return true;
    }

    private boolean checkPin(Account account) {
        ui.promptForPin();

        for (int attempt = 1; attempt <= maxPinAttempts; attempt++) {
            String enteredPin = ui.readPin();

            if (account.matchesPin(enteredPin)) {
                ui.showMessage(AtmMessages.LOGIN_SUCCESS);
                return true;
            }

            if (attempt == maxPinAttempts) {
                ui.showMessage(AtmMessages.PIN_ATTEMPTS_LIMIT);
                return false;
            }

            ui.promptRetryPin();
        }

        return false;
    }
}
