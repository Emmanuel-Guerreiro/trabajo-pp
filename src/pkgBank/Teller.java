package pkgBank;

import java.util.ArrayList;
import pkgBank.loan.Loan;
import pkgBank.loan.LoanTypes;
import pkgPpl.Customer;

/**
 *
 * @author emma
 */
public class Teller {

    private static int tellCount;
    private final int id;
    private int bankId; //This isnt final because on some cases, this can be initialized later on 
    private String name;
    private ArrayList<Customer> customerAt;

    //TODO: End this constructor
    //Used just if at least one bank has been defined
    public Teller(Bank b) {
        System.out.println("Initializing Teller instance");
        this.id = tellCount++;
        this.bankId = b.getId();

    }

    //This is used to initialize a Teller before any bank has been defined
    public Teller() {
        System.out.println("Initializing Teller instance");
        this.id = tellCount++;
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

        int status = c.closeAccount(acc.getId());

        return status;
    }

    /*
    * TODO: - On error should throw an exeption 
    *       <Check if this exception is one of java's library or make a new one>
    *
    *       - Should be void or Loan type?
    *
     */
    public void loanRequest(Customer c, Account acc, Loan l) {
        //Gives Add a new loan to a customer. And returns it

        //Is more verbose but cleanear than all inside condition
        int idx = custIdx(c);                             //Check customer is attended by this teller
        boolean isThisC = acc.getCustomer() == c.getId(); //Check if this acc if from this client
        boolean isThisL = l.getCustomer() == c.getId();   //Check if this loan is for this client
        boolean isThisLC = l.getAccount() == acc.getId(); //Check if this loan is for this account

        if (idx != -1 && isThisC && isThisL && isThisLC) {
            //If all ok will add it.
            //WIll add the loan to client's list.
            //WIll increment account credit

            //Is everything checked before calling the method. 
            //In consecuence, isnt necessary to handle possible errors
            c.addLoan(l);
            acc.incCredit(l.getAmount());
        } else {
            //Should throw the exception here
        }

    }

    public void loanRequest(Customer c, Account account, LoanTypes t, float amount) {
        //Checks the customer is attended by this teller
        //Checks if the accounts belongs to the client
        if (custIdx(c) != -1 && account.getCustomer() == c.getId()) {
            Loan l = new Loan(c, account, t, amount);

            c.addLoan(l);
            account.incCredit(amount);

        } else {
            //SHould throw the exception here
        }

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
        this.bankId = bId;
    }
}
