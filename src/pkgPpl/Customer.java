package pkgPpl;

import java.util.ArrayList;
import pkgBank.Account;
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

    //todo: Check correct initializing variables
    public Customer(String name, String address, int phone, Teller tel, Account acc) throws ErrorObjeto {
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
        accList.add(acc);
        //A customer must have at least one Teller to interact with
        avTeller.add(tel);
    }

    public void generalInquiry() {
        //Displays on stdout Customer data formated
    }
    
    public void depositMoney() {
        //Add money on Customer's account
    }

    public int withdrawMoney() {
        //Discounts money from Customer's account
        //And returns new account's balance
        return 1;
    }

    public void openAccount(Account acc) {
        //Creates new account on customer
        
    }

    public void closeAccount() {
        //Drops account from customer's accounts list
    }

    //Auxiliar method
    private boolean isTellerAvailable(Teller t) {
        //Check if teller is on customers list
        //May be accomplished functionally too. But isnt consistent 
        //with actual paradigm
        
        /*
        This alg with O(n) may be improved to O(1) if available teller list is
        implemented with a HashMap<int, Teller> where int TellerId instead of 
        an ArrayList
        Also this HashMap can be implemented on Teller.customerAt attribute
        and the O time analisys will be the same
        */
        for (Teller iTel : avTeller) {
            if (iTel.equals(t)) {
                return true;
            }
        }

        return false;
    }

    public void getOneTeller() {
        //Get one "random" teller from
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
        }
    }

    public void RequestCard() {
        //Request for a new credit card
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
