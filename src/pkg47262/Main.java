/**
 * Por favor leer README.md o README.txt antes de empezar.
 *
 * @author Emmanuel Guerreiro - Legajo 47262
 */
package pkg47262;

import pkgBank.Account;
import pkgBank.Bank;
import pkgBank.Card;
import pkgBank.Customer;
import pkgBank.Teller;
import pkgBank.loan.Loan;
import pkgBank.loan.LoanTypes;
import pkgExceptions.ErrorObjeto;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // If --automatic or -a flag is set. WIll instanciate automatically diverses
        // clases and show the relations on stdout

        if ("--automatic".equals(args[0]) || "-a".equals(args[0])) {
            autoRun();
        }

        // On other case, instances can be defined arbitrarially and tested
    }

    private static void autoRun() {
        // Initialize Bank, customer and teller

        // Can be initialized up to 3 customers
        try {
            System.out.println("---------------------------");
            System.out.println("Bank, Teller and customer will be instantiated");
            Teller tel0 = new Teller("Teller 0");
            // THis will initialize also a new account on cus0
            Customer cus0 = new Customer("Customer 0", "Avenida siempre viva", 261000000, tel0);
            Bank bank0 = new Bank("Bank 0", "Calle 1", tel0, cus0);

            // Add new Customer to this bank
            Customer c1 = new Customer("Customer 1", "Calle 2", 1233331, tel0);
            bank0.addCustomer(c1);

            System.out.println("---------------------------");
            System.out.println("Teller data:");
            tel0.provideInfo();
            System.out.println("Client data:");
            cus0.generalInquiry();

            System.out.println("---------------------------");
            System.out.println("Interaction with client's account ");
            // Open a new account on cus0 with credit = 0;
            Account accCus0 = cus0.openAccount();
            // Deposit and withdraw money on this new account
            System.out.println("Adding $100 to cus0");
            cus0.depositMoney(accCus0, 100);
            System.out.println("accCus0 credit: $" + accCus0.getCredit());
            System.out.println("Withdraw $50 to cus0");
            cus0.withdrawMoney(accCus0, 50);
            System.out.println("accCus0 credit: $" + accCus0.getCredit());
            System.out.println("Withdraw $60 to cus0");
            try {
                cus0.withdrawMoney(accCus0, 60);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("---------------------------");
            // For a new loan must apply from a customer. And pass it the account
            // and the teller involved.
            // The loan can be constructed before hand, and pass it or pass the
            // type and amount to be instantiated inside the method
            // A try-catch is useful, because if teller, acc or loan doesnt belongs
            // to the client, will throw IllegalArgumentException
            try {
                System.out.println("Interaction with loans");
                // LoanTypes is an enum defined on loan pkg.
                Loan loanCus0 = new Loan(cus0, accCus0, LoanTypes.PERSONAL, 1000);
                cus0.applyForLoan(tel0, accCus0, loanCus0);
                System.out.println("---------------------------");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Interaction with cards");
            // Will create a new card (class added by myself) and create issues on it
            Card cardC0 = new Card(cus0);
            tel0.issueCard(cardC0, "Fail on new payment");
            System.out.println(cardC0.getIssues());
            System.out.println("---------------------------");
        } catch (ErrorObjeto e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("---------------------------");
            System.out.println("Done");
            System.out.println("Closing...");
            System.out.println("---------------------------");
            System.exit(0);
        }

    }

}
