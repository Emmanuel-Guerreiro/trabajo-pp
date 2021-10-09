package pkgPpl;

import java.util.ArrayList;
import java.util.HashMap;
import pkgBank.Account;
import pkgBank.Card;
import pkgBank.loan.Loan;
import pkgBank.Teller;
import pkgExceptions.ErrorObjeto;

/**
 *
 * @author emma
 */
public class Customer {

    private static int customerCount;
    private int id;
    private String name;
    private String address;
    private int phoneNo;
    private ArrayList<Teller> avTeller;
    private ArrayList<Account> accList;
    private ArrayList<Loan> avLoans;
    private ArrayList<Card> cards;

    //todo: Check correct initializing variables
    public Customer(String name, String address, int phone, Teller tel) throws ErrorObjeto {
        //This is the minimum amount of attributes to be initialized on new 
        //customer instance

        System.out.println("Initializing Customer instance");

        if (Customer.customerCount > 3) {
            throw new ErrorObjeto("Customer");
        }

        this.id = ++customerCount;

        this.name = name;
        this.address = address;
        this.phoneNo = phone;
        //A customer must have at least one account
        Account fAcc = new Account(this.id, 0);
        accList.add(fAcc);
        //A customer must have at least one Teller to interact with
        avTeller.add(tel);
    }

    public void generalInquiry() {
        //Todo: complete this. Needs to get array list length
        //Displays on stdout Customer data formated
        String f = String.format("Cliente n°: %x \n Nombre: %s Cuentas: \n "
                + "Creditos pedidos:", 
                this.id, this.name );
        
        System.out.println(f);
    }

    public float depositMoney(Account acc, float inc) {
        //Add money on Customer's account
        if (acc.getCustomer() == this.id) {
            float newC = acc.incCredit(inc);
            return newC;
        }
        return -1;
    }

    //Todo: check if this may throw an exception
    public float withdrawMoney(Account acc, float disc) {
        //Discounts money from Customer's account
        //And returns new account's balance

        if (acc.getCustomer() == this.id) {
            try {
                float newC = acc.decCredit(disc);
                return newC;
            } catch (IllegalArgumentException e) {
                return -1;
            }
        }

        return -1;
    }

    public Account openAccount(Account acc) {
        /*Creates new account on customer from an account object
        
        Checks if isnt already on the customer's list, and if the account
        has already other customer
         */
        if (!isAccAv(acc) && acc.getCustomer() == this.id) {
            accList.add(acc);
        } else {
            System.out.println("xd");
        }
        return acc;
    }

    public Account openAccount() {
        //This will open a new account with credit 0 and returns it
        Account acc = new Account(this, 0);
        accList.add(acc);

        return acc;
    }

    public Account openAccount(float credit) {
        Account acc = new Account(this, credit);
        accList.add(acc);

        return acc;
    }

    public int closeAccount(int accId) {
        //Drops account from customer's accounts list
        //This algorithm which is O(n) can be optimized if the accList
        //is implemented with a HashMap instead of an ArrayList
        int idx = isAccWIdx(accId);
        if (idx != -1) {
            accList.remove(idx);
            return 1;
        }
        System.out.println("This account doesnt belongs to " + this.name);
        return -1;
    }

    public void availableTellers() {
        //Get one "random" teller from
        System.out.println("Available Tellers for customer " + this.name);
        int c = 0;
        for (Teller iTel : avTeller) {
            String tf = String.format("%x : Name: %s  Id: %x",
                    ++c, iTel.getName(), iTel.getId());
            System.out.println(tf);
        }
    }

    //todo: Should return an int?
    public void applyForLoan(Teller t, Loan l) {
        //Receive's a teller, check if its on customers list
        //Send request to Teller, and adds it to LoanList
        boolean isTellerAv = isTellerAvailable(t);
        if (isTellerAv) {
            t.loanRequest(this, l);
        } else {
            //Format string sending that teller.name doesnt isnt available
            //for this customer
            String fs1 = String.format("Teller %s isnt available for this customer",
                    t.getName());
            System.out.println(fs1);
        }
    }

    public Card RequestCard() {
        //Request for a new credit card
        Card nCard = new Card(this);

        return nCard;
    }

    //Auxiliar methods
    private boolean isAccAv(Account acc) {
        /*
        Check if account is on customers list
        May be accomplished functionally too. But isnt consistent 
        with actual paradigm
         */

 /* 
        This alg with O(n) may be improved to O(1) if available acc list is
        implemented with a HashMap<int, Account>, where int is accountId; instead
        of an ArrayList
         */
        for (Account iAcc : accList) {
            if (iAcc.getId() == acc.getId()) {
                return true;
            }
        }
        return false;
    }

    private int isAccWIdx(int id) {
        int idx = 0;
        for (Account iAcc : accList) {
            if (iAcc.getId() == id) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    private boolean isTellerAvailable(Teller t) {
        //The same comment as in isAccAv
        for (Teller iTel : avTeller) {
            if (iTel.getId() == t.getId()) {
                return true;
            }
        }

        return false;
    }

    //Setters and getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPhone() {
        return this.phoneNo;
    }

    public void setPhone(int p) {
        this.phoneNo = p;
    }

}
