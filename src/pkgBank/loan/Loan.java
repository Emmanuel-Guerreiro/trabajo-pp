package pkgBank.loan;

import pkgBank.Account;
import pkgBank.Customer;

/**
 *
 * @author emma
 */

public class Loan {
    private static int loanCount;
    /*
     * About id: - Will be setted automatically on object instantiation About all
     * attributes: All attributes where defined as because, shouldnt be modified
     * once are created (Emulating a real world situation)
     */
    private final int id;
    // LoanTypes is an enum defined on Type file (this package)
    private final LoanTypes type;
    private final int accountId;
    private final int customerId;
    // Added by myself. Extends Loan and Account functionality
    private final float amount;

    public Loan(Customer customer, Account account, LoanTypes t, float amount) throws IllegalArgumentException {
        System.out.println("Initializing Loan instance");
        // This exception will prevent Customer-account incompatibilites
        if (customer.getId() != account.getCustomer()) {
            String f = String.format("Account n°: %x does not belongs to %s", account.getId(), customer.getName());
            throw new IllegalArgumentException(f);
        }

        this.id = ++loanCount;
        this.customerId = customer.getId();
        this.accountId = account.getId();
        // Check Types definition for possible values. Those can be extended
        this.type = t;
        this.amount = amount;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public int getCustomer() {
        return this.customerId;
    }

    public int getAccount() {
        return this.accountId;
    }

    public float getAmount() {
        return this.amount;
    }

    public LoanTypes getType() {
        return this.type;
    }
}
