/**
 * Central place for values that may change during maintenance.
 * Update account defaults, PIN limits, and menu option numbers here
 * instead of searching through multiple classes.
 */
public final class AtmConfig {

    private AtmConfig() {
        // Utility class — prevent instantiation
    }

    // --- Default demo account ---
    public static final String DEFAULT_USERNAME = "huzaifa";
    public static final String DEFAULT_PIN = "1234";
    public static final double DEFAULT_BALANCE = 500000;

    // --- Authentication ---
    public static final int MAX_PIN_ATTEMPTS = 3;

    // --- Menu options (keep in sync with ConsoleUI.showMenu) ---
    public static final int OPTION_CHECK_BALANCE = 1;
    public static final int OPTION_WITHDRAW = 2;
    public static final int OPTION_DEPOSIT = 3;
    public static final int OPTION_EXIT = 4;

    // --- Input conventions ---
    public static final String AFFIRMATIVE_ANSWER = "yes";

    /** Returned by input readers when the user types a non-numeric value. */
    public static final int INVALID_MENU_CHOICE = -1;
    public static final double INVALID_AMOUNT = -1.0;
}
