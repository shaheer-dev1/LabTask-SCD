import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Console input and output for the ATM.
 * Depends on {@link AtmConfig} and {@link AtmMessages} so display text and
 * option numbers can be maintained in one place.
 */
public class ConsoleUI {

    private final Scanner scanner;

    public ConsoleUI(Scanner scanner) {
        this.scanner = scanner;
    }

    // --- Screen messages ---

    public void showWelcome() {
        System.out.println(AtmMessages.WELCOME_LINE_1);
        System.out.println(AtmMessages.WELCOME_LINE_2);
    }

    public void showMenu() {
        System.out.println(AtmMessages.MENU_HEADER);
        System.out.println(AtmConfig.OPTION_CHECK_BALANCE + AtmMessages.MENU_CHECK_BALANCE);
        System.out.println(AtmConfig.OPTION_WITHDRAW + AtmMessages.MENU_WITHDRAW);
        System.out.println(AtmConfig.OPTION_DEPOSIT + AtmMessages.MENU_DEPOSIT);
        System.out.println(AtmConfig.OPTION_EXIT + AtmMessages.MENU_EXIT);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showBalance(double balance) {
        System.out.println(AtmMessages.BALANCE_PREFIX + balance);
    }

    public void showNewBalance(double balance) {
        System.out.println(AtmMessages.NEW_BALANCE_PREFIX + balance);
    }

    public void showWithdrawalSuccess(double remainingBalance) {
        System.out.println(AtmMessages.WITHDRAW_SUCCESS);
        System.out.println(AtmMessages.REMAINING_BALANCE_PREFIX + remainingBalance);
    }

    public void showGoodbye() {
        System.out.println(AtmMessages.GOODBYE);
    }

    // --- User input ---

    /**
     * Reads the menu choice. Non-numeric input is cleared and treated as invalid
     * instead of crashing the program.
     */
    public int readMenuChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException exception) {
            scanner.next();
            return AtmConfig.INVALID_MENU_CHOICE;
        }
    }

    public String readUsername() {
        System.out.print(AtmMessages.PROMPT_NAME);
        return scanner.next().toLowerCase();
    }

    public void promptForPin() {
        System.out.print(AtmMessages.PROMPT_PIN);
    }

    public String readPin() {
        return scanner.next();
    }

    public void promptRetryPin() {
        System.out.print(AtmMessages.PROMPT_RETRY_PIN);
    }

    public double readDepositAmount() {
        return readAmount(AtmMessages.PROMPT_DEPOSIT);
    }

    public double readWithdrawAmount() {
        return readAmount(AtmMessages.PROMPT_WITHDRAW);
    }

    public boolean askYesNo(String prompt) {
        System.out.print(prompt);
        String answer = scanner.next().toLowerCase();
        return AtmConfig.AFFIRMATIVE_ANSWER.equals(answer);
    }

    public void close() {
        scanner.close();
    }

    /**
     * Shared amount reader for deposit and withdraw prompts (DRY).
     * Non-numeric input returns {@link AtmConfig#INVALID_AMOUNT}.
     */
    private double readAmount(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException exception) {
            scanner.next();
            return AtmConfig.INVALID_AMOUNT;
        }
    }
}
