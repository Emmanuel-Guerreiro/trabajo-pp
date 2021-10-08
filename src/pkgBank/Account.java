package pkgBank;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author emma
 */
public class Account {

    private static int accountsCount;
    /*
    * About id:
    * - Will be setted automatically on object instantiation
    * - Cant be modified. Just readed. So, is setted as final
     */
    private final int id;
    private final int customerId;

    public Account(int c) {
        System.out.println("Initializing Account instance");
        //This will ensure that every Account has it's own id
        this.id = ++accountsCount;
        this.customerId = c;
    }

    
    //Getters
    public int getId() {
        return id;
    }

    public int getCustomer() {
        return customerId;
    }

    
    public static void getCount() {
        System.out.println("Cuentas creadas: " + accountsCount);
    }
}
