package pkgBank;

import java.util.ArrayList;
import pkgBank.loan.Loan;
import pkgBank.loan.LoanTypes;

/**
 *
 * @author emma
 */
public class Teller {

    private static int tellCount;
    private final int id;
    private int bankId; //This isnt final because on some cases, this can be initialized later on 
    private final String name;
    private ArrayList<Customer> customerAt;

    //Used just if at least one bank has been defined
    public Teller(String n, Bank b) {
        System.out.println("Initializing Teller instance");
        this.id = tellCount++;
        this.bankId = b.getId();
        this.name = n;
    }

    //This is used to initialize a Teller before any bank has been defined
    public Teller(String n) {
        System.out.println("Initializing Teller instance");
        this.id = tellCount++;
        this.name = n;
    }

    public float collectMoney(float inc, Account a) {
        //Receives an amount and an account and increments
        //account money. Returns new account money
        if (inc < 0) {
            return -1;
        }
        float nCred = a.incCredit(inc);
        return nCred;
    }

    public Account openAccount(Customer c) {
        //Add a new account to a customer
        Account nAcc = c.openAccount();

        return nAcc;
    }

    public Account openAccount(Customer c, Account acc) {
        Account nAcc = c.openAccount(acc);

        return nAcc;
    }

    public int closeAccount(Customer c, Account acc) {
        //Receives account and pop's it from Customer accounts
        //list.
        //If allOk => Returns 1
        //If !allOk => Returns -1
        try {
            c.closeAccount(acc.getId());
            return 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public Loan loanRequest(Customer c, Account acc, Loan l)
            throws IllegalArgumentException {
        /**
         * Gives Add a new loan to a customer. And returns it If the account is
         * not from this client or the loan is not for this client or the loan
         * is not for this account will throw IllegalArgumentException
         */

        //Is more verbose but cleanear than all inside condition
        int idx = custIdx(c);                             //Check customer is attended by this teller
        boolean isThisC = acc.getCustomer() == c.getId(); //Check if this acc if from this client
        boolean isThisL = l.getCustomer() == c.getId();   //Check if this loan is for this client
        boolean isThisLC = l.getAccount() == acc.getId(); //Check if this loan is for this account

        if (idx == -1) {
            String f = String.format("%s isnt attended by this teller",
                    c.getName());
            throw new IllegalArgumentException(f);
        }
        if (!isThisC) {
            String f = String.format("Account n°: %x doesnt belongs to client %s",
                    acc.getId(), c.getName());
            throw new IllegalArgumentException(f);
        }
        if (!isThisL) {
            String f = String.format("Loan n°: %x doesnt belongs to client %s",
                    acc.getId(), c.getName());
            throw new IllegalArgumentException(f);
        }
        if (!isThisLC) {
            String f = String.format("Loan n°: %x isnt for account %s",
                    l.getId(), acc.getId());
            throw new IllegalArgumentException(f);
        }

        //If arrived up to here, should be all right. An then add it
        c.addLoan(l);
        acc.incCredit(l.getAmount());

        return l;
    }

    public Loan loanRequest(Customer c, Account account, LoanTypes t, float amount)
            throws IllegalArgumentException {
        //Checks the customer is attended by this teller
        //Checks if the accounts belongs to the client

        //This check outside seems more verbose, but avoids repeating calcs
        boolean isAtt = custIdx(c) != -1;
        boolean accBelongs = account.getCustomer() == c.getId();

        if (!isAtt) {
            String f = String.format("%s isnt attended by this teller",
                    c.getName());
            throw new IllegalArgumentException(f);
        } else if (!accBelongs) {
            String f = String.format("Account n°: %x doesnt belongs to client %s",
                    account.getId(), c.getName());
            throw new IllegalArgumentException(f);
        }

        //If arrived up to here. Seems to be all ok
        Loan l = new Loan(c, account, t, amount);

        c.addLoan(l);
        account.incCredit(amount);

        return l;

    }

    public void provideInfo() {
        //Formats Teller's info and send it to stdout
        String f1 = String.format("Teller n°: %x \n Name: %s \n Amount of clients: %x",
                this.id, this.name, this.customerAt.size());
        System.out.println(f1);
    }

    public void issueCard(Card c, String s) {
        //Add an issue to a card?
        c.addIsue(s);
    }

    //Auxiliar methods for better interaction
    public int addCustomer(Customer c) {
        //If the customer isnt on the list, will add him and return 1 
        //If already is, will return -1

        if (custIdx(c) != -1) {
            this.customerAt.add(c);
            return 1;
        }
        return -1;

    }

    public int delCustomer(Customer c) {
        //If c is attended by this teller, will pop him from
        //the list

        int idx = custIdx(c);
        if (idx != -1) {
            customerAt.remove(idx);
            return 1;
        }

        return idx;
    }

    private int custIdx(Customer c) {
        /*
        *As explained on Customer isAccAv method, this alg with O(n) may be
        *optimized to O(1) if the relation is implemented with a HashMap insted
        *of an ArrayList
         */
        int idx = 0;
        for (Customer iC : customerAt) {
            if (iC.getId() == c.getId()) {
                return idx;
            }
            idx++;
        }

        return -1;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getBank() {
        return this.bankId;
    }

    public void setBank(int bId) {
        //BankId just can be defined once. And cant be 0
        if (this.bankId == 0 && bId > 0) {
            this.bankId = bId;
        }
    }
}
