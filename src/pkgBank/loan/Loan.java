/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    * About id:
    * - Will be setted automatically on object instantiation
    * About all attributes: 
    * All attributes where defined as because, shouldnt be modified once are created
    * (Emulating a real world situation)
    */
    private final int id;
    //LoanTypes is an enum defined on Type file (this package)
    private final LoanTypes type;
    private final int accountId;
    private final int customerId;
    //Added by myself. Extends Loan and Account functionality
    private final float amount;
    
    //Todo: set types as enum or smth similiat to narrow down options
    public Loan(Customer customer, Account account, LoanTypes t, float amount){
        System.out.println("Initializing Loan instance");
        this.id = ++loanCount;
        //todo: set customerId with customer.getId method
        this.customerId = 2;
        this.accountId = account.getId();
        //Check Types definition for possible values. Those can be extended 
        this.type = t;
        this.amount = amount;
    }
    
    //getters
    public int getId(){
        return this.id;
    }
    
    public int getCustomer(){
        return this.customerId;
    }
    
    public int getAccount(){
        return this.accountId;
    }
    
    public float getAmount(){
        return this.amount;
    }
    
    public LoanTypes getType(){
        return this.type;
    }
}
