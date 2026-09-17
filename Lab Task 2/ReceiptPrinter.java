/**
 * Builds and prints a withdrawal transaction receipt.
 * Receipt text comes from {@link AtmMessages} for easier wording updates.
 */
public class ReceiptPrinter {

    public void printWithdrawalReceipt(double withdrawAmount, double remainingBalance) {
        System.out.println(AtmMessages.RECEIPT_HEADER);
        System.out.printf("Transaction Amount: Rs. %.2f%n", withdrawAmount);
        System.out.printf("Balance After Transaction: Rs. %.2f%n", remainingBalance);
        System.out.println(AtmMessages.RECEIPT_THANKS);
        System.out.println(AtmMessages.RECEIPT_FOOTER);
    }
}
