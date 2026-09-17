import java.util.Scanner;

/**
 * Application entry point.
 * Creates components from {@link AtmConfig}, wires dependencies, and starts the ATM.
 */
public class FinalProject {

    public static void main(String[] args) {
        Account account = new Account(
                AtmConfig.DEFAULT_USERNAME,
                AtmConfig.DEFAULT_PIN,
                AtmConfig.DEFAULT_BALANCE);

        ConsoleUI ui = new ConsoleUI(new Scanner(System.in));
        Authenticator authenticator = new Authenticator(ui, AtmConfig.MAX_PIN_ATTEMPTS);
        TransactionService transactionService = new TransactionService(account);
        ReceiptPrinter receiptPrinter = new ReceiptPrinter();

        AtmController atm = new AtmController(
                ui,
                authenticator,
                transactionService,
                receiptPrinter,
                account);

        atm.start();
        ui.close();
    }
}
