/**
 * Result of a deposit or withdrawal attempt.
 * Lets callers (UI or tests) react without embedding console messages
 * inside the transaction logic.
 */
public enum TransactionResult {
    SUCCESS,
    INVALID_AMOUNT,
    INSUFFICIENT_FUNDS
}
