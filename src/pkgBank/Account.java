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

    public float incCredit(float newCredit) throws IllegalArgumentException{
        //Will accept a non negative float, and then increment to acc credit
        //If arg is negative, will throw IllegalArgumentException 
        if (newCredit < 0) {
            throw new IllegalArgumentException(Float.toString(newCredit));
        }

        this.credit += newCredit;
        return this.credit;
    }

    public float decCredit(float dec) 
            throws IllegalArgumentException, ArithmeticException {
        //Will accept a non negative float, and then decrement to add credit
        //if arg is negative, will throw IllegalArgumentException
        if (dec < 0) {
            throw new IllegalArgumentException(Float.toString(dec));
        } else if (this.credit < dec) {
            throw new ArithmeticException("Cant discound this amount");
        }
        
        this.credit -= dec;
        return this.credit;
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

    public float getCredit(){
        return this.credit;
    }
    
}
