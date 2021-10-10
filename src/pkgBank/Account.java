package pkgBank;

/**
 *
 * @author emma
 */
public class Account {

    private static int accountsCount;
    /*
    * About id:
    * - Will be setted automatically on object instantiation
    * - Cant be modified. Just readed. In consequence, is setted as final
     */
    private final int id;
    private final int customerId;
    //Added to complement Teller's and Customer's functionality
    private float credit;

    public Account(int cId, float credit) { 
        //This is implemented on Customer constructor
        System.out.println("Initializing Account instance");
        //This will ensure that every Account has it's own id
        this.id = ++accountsCount;
        this.customerId = cId;
        this.credit = credit;
    }

    public Account(Customer cus, float credit) {
        System.out.println("Initializing Account instance");
        //This will ensure that every Account has it's own id
        this.id = ++accountsCount;
        this.customerId = cus.getId();
        this.credit = credit;
    }

    
    //Todo: check correct implementation of exception throwing
    public float incCredit(float newCredit) {
        //Will accept a non negative float, and then increment to acc credit
        //If arg is negative, will throw IllegalArgumentException 
        if (newCredit >= 0) {
            this.credit += newCredit;
            return this.credit;
        }
        throw new IllegalArgumentException(Float.toString(newCredit));
    }
    
    //Todo: Check exception throwing too
    //todo: If dec > credit should return another exception?
    public float decCredit(float dec){
        //Will accept a non negative float, and then decrement to add credit
        //if arg is negative, will throw IllegalArgumentException
        if(dec >= 0 && this.credit >= dec){
            this.credit -= dec;
            return this.credit;
        }
        throw new IllegalArgumentException(Float.toString(dec));
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
