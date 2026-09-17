/**
 * Runs the ATM session: welcome, login, menu loop, and operation routing.
 *
 * <p><b>How to add a new ATM operation later:</b>
 * <ol>
 *   <li>Add a menu option constant in {@link AtmConfig}</li>
 *   <li>Add a menu line in {@link ConsoleUI#showMenu()}</li>
 *   <li>Add a {@code case} in {@link #handleMenuChoice(int)}</li>
 *   <li>Implement a private {@code performXxx()} method here</li>
 * </ol>
 * Business rules for money should go in {@link TransactionService} / {@link Account},
 * not in this controller.
 */
public class AtmController {

    private final ConsoleUI ui;
    private final Authenticator authenticator;
    private final TransactionService transactionService;
    private final ReceiptPrinter receiptPrinter;
    private final Account account;

    public AtmController(
            ConsoleUI ui,
            Authenticator authenticator,
            TransactionService transactionService,
            ReceiptPrinter receiptPrinter,
            Account account) {
        this.ui = ui;
        this.authenticator = authenticator;
        this.transactionService = transactionService;
        this.receiptPrinter = receiptPrinter;
        this.account = account;
    }

    public void start() {
        ui.showWelcome();

        if (!authenticator.authenticate(account)) {
            ui.showMessage(AtmMessages.USER_NOT_FOUND);
            return;
        }

        runSession();
    }

    private void runSession() {
        while (true) {
            ui.showMenu();
            int menuChoice = ui.readMenuChoice();

            if (menuChoice == AtmConfig.OPTION_EXIT) {
                endSession();
                return;
            }

            handleMenuChoice(menuChoice);

            if (!ui.askYesNo(AtmMessages.PROMPT_ANOTHER_OPERATION)) {
                endSession();
                return;
            }
        }
    }

    private void handleMenuChoice(int menuChoice) {
        switch (menuChoice) {
            case AtmConfig.OPTION_CHECK_BALANCE:
                performCheckBalance();
                break;
            case AtmConfig.OPTION_WITHDRAW:
                performWithdraw();
                break;
            case AtmConfig.OPTION_DEPOSIT:
                performDeposit();
                break;
            default:
                ui.showMessage(AtmMessages.INVALID_MENU_INPUT);
                break;
        }
    }

    private void performCheckBalance() {
        ui.showBalance(transactionService.getBalance());
    }

    private void performDeposit() {
        double depositAmount = ui.readDepositAmount();
        TransactionResult result = transactionService.deposit(depositAmount);

        if (result == TransactionResult.SUCCESS) {
            ui.showNewBalance(transactionService.getBalance());
        } else {
            ui.showMessage(AtmMessages.INVALID_DEPOSIT);
        }
    }

    private void performWithdraw() {
        double withdrawAmount = ui.readWithdrawAmount();
        TransactionResult result = transactionService.withdraw(withdrawAmount);

        switch (result) {
            case SUCCESS:
                ui.showWithdrawalSuccess(transactionService.getBalance());
                offerReceipt(withdrawAmount);
                break;
            case INVALID_AMOUNT:
                ui.showMessage(AtmMessages.INVALID_WITHDRAWAL);
                break;
            case INSUFFICIENT_FUNDS:
                ui.showMessage(AtmMessages.INSUFFICIENT_BALANCE);
                break;
            default:
                break;
        }
    }

    private void offerReceipt(double withdrawAmount) {
        if (ui.askYesNo(AtmMessages.PROMPT_PRINT_RECEIPT)) {
            receiptPrinter.printWithdrawalReceipt(
                    withdrawAmount,
                    transactionService.getBalance());
        }
    }

    private void endSession() {
        ui.showGoodbye();
    }
}
