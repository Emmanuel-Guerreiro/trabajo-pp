/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgBank;

import java.util.ArrayList;
import pkgExceptions.ErrorObjeto;
import pkgPpl.Customer;

/**
 *
 * @author emma
 */
public class Bank {

    //BankCount will initialize on 1, because is easier to check if 
    //a customer already has a bank
    private static int bankCount = 1;
    
    private final int bankId;
    private final String name;
    private String location;
    private ArrayList<Customer> customerList;
    private ArrayList<Teller> tellerList;

    //Cant initialize more than 3 banks
    public Bank(String n, String l, Teller t, Customer c) throws ErrorObjeto {
        System.out.println("Initializing Bank instance");
        if (Bank.bankCount >= 3) {
            throw new ErrorObjeto("Bank");
        }
        this.bankId = bankCount++;
        this.name = n;
        this.location = l;
        customerList.add(c);
        tellerList.add(t);
    }

    public int addCustomer(Customer c) {
        //Add's new customer to the list, and set customer bank id to this id
        boolean isAlready = false;
        for (Customer iC : customerList) {
            if (iC.getId() == c.getId()) {
                isAlready = true;
            }
        }

        //If a client has a bank already, c.bankId won be 0
        if (!isAlready && c.getBank() == 0) {
            this.customerList.add(c);
            c.setBank(bankId);
            return 1;
        }

        return -1;
    }

    public int addTeller(Teller t) {
        boolean isAlready = false;
        for (Teller iT : tellerList) {

            if (iT.getId() == t.getId()) {
                isAlready = true;
            }
        }
        //If a teller has a bank already, t.bankId wont be 0
        if (!isAlready && t.getBank() == 0) {
            this.tellerList.add(t);
            t.setBank(this.bankId);
            return 1;
        }

        return -1;
    }

    /*All setters and getters*/
    public int getId() {
        return this.bankId;
    }

    public String getName() {
        return this.name;
    }

    public void setLocation(String l) {
        this.location = l;
    }

    public String getLocation() {
        return this.location;
    }

}
