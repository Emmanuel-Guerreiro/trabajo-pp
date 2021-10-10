package pkgBank;

import java.util.ArrayList;
import pkgBank.Account;
import pkgBank.Bank;
import pkgBank.Card;
import pkgBank.loan.Loan;
import pkgBank.Teller;
import pkgBank.loan.LoanTypes;
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

    public Customer(String name, String address, int phone, Bank bank, Teller tel) throws ErrorObjeto {
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

    public float withdrawMoney(Account acc, float disc) {
        //Discounts money from Customer's account
        //And returns new account's balance

        if (acc.getCustomer() == this.id) {
            try {
                float newC = acc.decCredit(disc);
                return newC;
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println(e.getMessage());
                return -1;
            }
        }

        return -1;
    }

    public Account openAccount(Account acc) throws IllegalArgumentException {
        /**
         * Creates new account on customer from an account object
         *
         * Will be used from Teller's openAccount method
         *
         * Checks if is not already on the customer's list, and if the account
         * has already other customer
         *
         * @param acc
         * @returns acc
         */
        if (!isAccAv(acc) && acc.getCustomer() == this.id) {
            accList.add(acc);
        } else {
            String f = String.format("Account n° %x does not belongs to customer %s",
                    acc.getId(), this.id);
            throw new IllegalArgumentException(f);
        }
        return acc;
    }

    public Account openAccount() {
        /**
         * Creates a new account with credit 0 and adds it to client's list
         *
         * Not exception thrown because there si no conflictive argument
         *
         * @returns acc: return the new account created
         */
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

    public void closeAccount(int accId) throws IllegalArgumentException {
        //Drops account from customer's accounts list
        //This algorithm which is O(n) can be optimized if the accList
        //is implemented with a HashMap instead of an ArrayList
        int idx = isAccWIdx(accId);
        if (idx == -1) {
            String f = String.format("Account n°: %x does not belongs to this customer",
                    accId);
            throw new IllegalArgumentException(f);
        }

        //If arrived here, there is no conflict to remove the item
        accList.remove(idx);

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

   
    public Loan applyForLoan(Teller t, Account acc, Loan l) {
        /*Teller.loanRequest can throw IllegalArgumentException
        There's no need for account, loan and customer checking. Is all done 
        in the method*/
        try {
            t.loanRequest(this, acc, l);
            return l;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            //This null may be carefully handled
            //Isnt the best solution, but didnt came up with another idea
            return null;
        }
    }

    public Loan applyForLoan(Teller t, Account acc, LoanTypes type, float amount) {
        try {
            //Both methods can throw the same exception. So isnt necessary
            //to make nested try-catch blocks
            Loan nl = new Loan(this, acc, type, amount);
            t.loanRequest(this, acc, nl);
            
            return nl;         
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            //This null may be carefully handled
            //Isnt the best solution, but didnt came up with another idea
            return null;
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

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
