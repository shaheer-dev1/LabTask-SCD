/**
 * User-facing text used by the ATM.
 * Keeps messages in one place so wording can be updated without
 * hunting through controller, auth, and UI logic.
 */
public final class AtmMessages {

    private AtmMessages() {
        // Utility class — prevent instantiation
    }

    // --- Session ---
    public static final String WELCOME_LINE_1 = "Welcome to ATM!";
    public static final String WELCOME_LINE_2 = "Login to your Account";
    public static final String GOODBYE = "Thanks for using ATM. Goodbye!";
    public static final String USER_NOT_FOUND = "User not found!";
    public static final String INVALID_MENU_INPUT = "Invalid input!";

    // --- Authentication ---
    public static final String WRONG_USERNAME = "Wrong username!";
    public static final String LOGIN_SUCCESS = "Login successful!";
    public static final String PIN_ATTEMPTS_LIMIT = "Attempts limit reached!";
    public static final String PROMPT_NAME = "Enter your name: ";
    public static final String PROMPT_PIN = "Please enter your PIN: ";
    public static final String PROMPT_RETRY_PIN = "Wrong PIN! Please try again: ";

    // --- Menu ---
    public static final String MENU_HEADER = "\nEnter: -";
    public static final String MENU_CHECK_BALANCE = " to Check Balance";
    public static final String MENU_WITHDRAW = " to Withdraw Money";
    public static final String MENU_DEPOSIT = " to Deposit Money";
    public static final String MENU_EXIT = " to Exit";

    // --- Transactions ---
    public static final String PROMPT_DEPOSIT = "Enter amount you want to deposit: ";
    public static final String PROMPT_WITHDRAW = "Enter amount you want to withdraw: ";
    public static final String INVALID_DEPOSIT = "Invalid deposit amount!";
    public static final String INVALID_WITHDRAWAL = "Invalid withdrawal amount!";
    public static final String INSUFFICIENT_BALANCE = "Insufficient balance!";
    public static final String WITHDRAW_SUCCESS = "Your transaction is successful.";
    public static final String BALANCE_PREFIX = "Your balance is: Rs. ";
    public static final String NEW_BALANCE_PREFIX = "Your new balance is: Rs. ";
    public static final String REMAINING_BALANCE_PREFIX = "Remaining balance: Rs. ";

    // --- Prompts ---
    public static final String PROMPT_ANOTHER_OPERATION =
            "\nDo you want to perform another operation? (Yes/No): ";
    public static final String PROMPT_PRINT_RECEIPT =
            "Do you want to print Receipt? (Yes/No): ";

    // --- Receipt ---
    public static final String RECEIPT_HEADER = "\n--- Transaction Receipt ---";
    public static final String RECEIPT_THANKS = "Thank you for using our ATM!";
    public static final String RECEIPT_FOOTER = "----------------------------";
}
