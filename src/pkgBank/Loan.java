/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgBank;

import pkgPpl.Customer;

/**
 *
 * @author emma
 */
public class Loan {
    private static int loanCount;
    /*
    * About id:
    * - Will be setted automatically on object instantiation
    * - Cant be modified. Just readed. So, is setted as final
     */
    private final int id;
    private final String type;
    private final int accountId;
    private final int customerId;
    
    
    public Loan(Customer customer, Account account, String t){
        System.out.println("Initializing Loan instance");
        this.id = ++loanCount;
        //todo: set customerId with customer.getId method
        this.customerId = 2;
        this.accountId = account.getId();
        this.type = t;
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
    
    public String getType(){
        return this.type;
    }
}
