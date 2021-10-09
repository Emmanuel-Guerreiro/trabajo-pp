/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgBank;

import java.util.ArrayList;
import pkgPpl.Customer;

/**
 *
 * @author emma
 */
public class Bank {

    private int bankId;
    final private String name; //Check if has sense to be final
    private String location;
    private ArrayList<Customer> customerList;
    private ArrayList<Teller> tellerList;

    
    //Todo: Add Teller and Customer on constructors. Must be at least one of each
    public Bank(int id, String n, String l) {
        System.out.println("Initializing Bank instance");
        this.bankId = id;
        this.name = n;
        this.location = l;
    }

    public Bank(int id, String n, String l, Teller t) {
        System.out.println("Initializing Bank instance");
        this.bankId = id;
        this.name = n;
        this.location = l;
        this.tellerList.add(t);
    }

    public void addCustomer(Customer c) {
        this.customerList.add(c);
    }

    public void addTeller(Teller t) {
        this.tellerList.add(t);
    }

    /*All setters and getters*/
    public void setBankId(int i) {
        this.bankId = i;
    }

    //todo: Check this. Just return if has been initialized
    public int getBankId(int i) {
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
