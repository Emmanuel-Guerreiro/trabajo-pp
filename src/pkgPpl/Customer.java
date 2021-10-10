package pkgPpl;

import java.util.ArrayList;
import pkgBank.Account;
import pkgBank.Bank;
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
    private int bankId;
    private int id;
    private String name;
    private String address;
    private int phoneNo;
    private ArrayList<Teller> avTeller;
    private ArrayList<Account> accList;
    private ArrayList<Loan> avLoans;
    private ArrayList<Card> cards;

    //todo: Check correct initializing variables
    //TOdo: Add a new constructor which adds bank on initialization
    /*This is the minimum amount of attributes to be initialized on new 
    customer instance*/
    public Customer(String name, String address, int phone, Teller tel) throws ErrorObjeto {
        /**
         * This constructor is recommended to be used before any bank is defined
         * If there's already one, use the next constructor
         *
         * @param name
         * @param address
         * @param phone
         * @param tel
         *
         * @throws pkgExceptions.ErrorObjeto
         */

        System.out.println("Initializing Customer instance");

        //Just can be 3 customers (id between 0 and 2)
        if (Customer.customerCount >= 2) {
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

      public Customer(String name, String address, int phone,Bank bank ,Teller tel) throws ErrorObjeto {
        /**
         * This constructor is recommended to be used before any bank is defined
         * If there's already one, use the next constructor
         *
         * @param name
         * @param address
         * @param phone
         * @param tel
         *
         * @throws pkgExceptions.ErrorObjeto
         */

        System.out.println("Initializing Customer instance");

        //Just can be 3 customers (id between 0 and 2)
        if (Customer.customerCount >= 2) {
            throw new ErrorObjeto("Customer");
        }
        
                this.id = ++customerCount;

        this.name = name;
        this.address = address;
        this.phoneNo = phone;
        this.bankId = bank.getId();
        //A customer must have at least one account
        Account fAcc = new Account(this.id, 0);
        accList.add(fAcc);
        //A customer must have at least one Teller to interact with
        avTeller.add(tel);
      }
    
    
    public void generalInquiry() {
        //Displays on stdout Customer data formated
        String f = String.format("Cliente n°: %x \n Nombre: %s Cuentas: %x \n "
                + "Creditos pedidos: %x",
                this.id, this.name, accList.size(), avLoans.size());

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

    //todo: THis should throw an exception
    public Account openAccount(Account acc) {
        /*Creates new account on customer from an account object
        
        Will be used from Teller's openAccount method
        
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

    //TOdo: throws same exception as open account on failure
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
    //Todo: overload this with, but receiven loan initializing data. And create
    //it on inside the method
    public void applyForLoan(Teller t, Account acc, Loan l) {
        //Receive's a teller, check if its on customers list
        //Send request to Teller, and adds it to LoanList
        boolean isTellerAv = isTellerAvailable(t);
        //todo: Check in here if loan has this client id?
        //Should access to teller's method or can be done directly?
        if (isTellerAv) {
            t.loanRequest(this, acc, l);
        } else {
            //Format string sending that teller.name doesnt isnt available
            //for this customer
            String fs1 = String.format("Teller %s isnt available for this customer",
                    t.getName());
            System.out.println(fs1);
        }
    }

    //Used on Teller class for new loan on this client's list
    public int addLoan(Loan l) {
        //Checking inside, will prevent problems. May be redundant on some cases
        //but is better

        //THis may be cleaner on a new function, but is redundant. Is the unique
        //place where is checked
        boolean inside = false;
        for (Loan iL : avLoans) {
            if (iL.getId() == l.getId()) {
                inside = true;
            }
        }

        if (inside && l.getCustomer() == this.id) {
            avLoans.add(l);
            return 1;
        }

        return -1;
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

    public void setBank(int bId) {
        this.bankId = bId;
    }

    public int getBank() {
        return this.bankId;
    }
    
    public ArrayList<Card> getCards(){
       return this.cards;
    }
}
