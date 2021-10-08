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
public class Teller {
    
    
    private int id;
    private String name;
    private ArrayList<Customer> customerAt;

    public Teller(){
        
    }
    
    public void addCustomer(Customer c){
        this.customerAt.add(c);
    }


}
