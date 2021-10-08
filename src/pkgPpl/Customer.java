/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgPpl;

import java.util.ArrayList;
import pkgBank.Teller;

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
    private int acctNo;
    private ArrayList<Teller> avTeller;
    
    public Customer() {
        System.out.println("Initializing Customer instance");
        this.id = ++customerCount;
    }
    
    
}
