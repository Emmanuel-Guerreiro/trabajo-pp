/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private int id;
    private String name;
    private ArrayList<Customer> customerAt;

    public Teller() {
        this.id = ++tellCount;
    }

    public float collectMoney(float inc, Account a) {
        //Receives an amount and an account and increments
        //account money. Returns new account money
        return 3.2f;
    }

    public Account openAccount(Customer c) {
        //Add a new account to a customer
        Account nAcc = c.openAccount();

        return nAcc;
    }
    
    public Account openAccount(Customer c, Account acc){
        Account nAcc = c.openAccount(acc);
        
        return nAcc;
    }

    public int closeAccount(Customer c,Account acc) {
        //Receives account and pop's it from Customer accounts
        //list.
        //If allOk => Returns 1
        //If !allOk => Returns -1
        
        int status = c.closeAccount(acc.getId());
        
        return status;
    }

    
    
    
    /*
    * TODO: this method will check:
    *        -if the client is on Teller's list
    *        -if the account is of this client
    *        -if the loan is for this client
    *        -if the loan is for this clients' acc
    *
    *       On error should throw an exeption <Check if this exception is 
    *       one of java's library or make a new one>
    *
    */
    public Loan loanRequest(Customer c,Account acc, Loan l) {
        //Gives Add a new loan to a customer. And returns it
        return l;
    }
    public void loanRequest(Customer c, Account account, LoanTypes t, float amount ){
        
    }
    public void provideInfo() {
        //Formats Teller's info and send it to stdout
    }

    public void issueCard() {
        //Add an issue to a card?
    }

    //Auxiliar methods for better interaction
    public int addCustomer(Customer c) {
        //If the customer isnt on the list, will add him and return 1 
        //If already is, will return -1

        this.customerAt.add(c);
        return 1;

    }

    public int delCustomer(Customer c) {
        //If c is attended by this teller, will pop him from
        //the list
        
        int idx = custIdx(c);
        if(idx != -1){
            customerAt.remove(idx);
            return 1;
        }
        
        return idx;
    }
    
    private int custIdx(Customer c){
        /*
        *As explained on Customer isAccAv method, this alg with O(n) may be
        *optimized to O(1) if the relation is implemented with a HashMap insted
        *of an ArrayList
        */
        int idx = 0;
        for(Customer iC : customerAt){
            if(iC.getId() == c.getId()){
                return idx;
            }
            ++idx;
        }
        
        return -1;
    }
    
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
