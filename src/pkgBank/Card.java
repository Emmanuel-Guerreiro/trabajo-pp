package pkgBank;

import pkgPpl.Customer;

/**
 * THis class was not on the original class model Added by myself for
 * convenience
 *
 * @author emma
 */
public class Card {
    private static int cardCount;
    private final int customerId;
    private final int id;
    
    public Card(Customer c){
        this.id = ++cardCount;
        this.customerId = c.getId();
    }
 
    public Card(int cId){
        this.id = ++cardCount;
        this.customerId = cId;
    }
    
    public int getCustomer(){
        return this.customerId;
    }
    
    public int getId(){
        return this.id;
    }
}
