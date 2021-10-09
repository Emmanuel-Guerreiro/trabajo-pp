/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgBank;

import java.util.ArrayList;
import pkgBank.loan.Loan;
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

    public void openAccount(Customer c) {
        //Add a new account to a customer
    }

    public int closeAccount() {
        //Receives account and pop's it from Customer accounts
        //list.
        //If allOk => Returns 1
        //If !allOk => Returns -1
        return 1;
    }

    public void loanRequest(Customer c, Loan l) {
        //Gives Add a new loan to a customer. And returns it
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

    public void delCustomer(Customer c) {
        //If c is attended by this teller, will pop him from
        //the list
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
