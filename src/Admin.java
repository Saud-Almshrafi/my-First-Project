public class Admin extends User {

    // Declaration of the limit of items the admin can purchase
    int limtOfCart;

    // Constructor for the Admin class, initializes name, email, userID, and limtOfCart,
    public Admin(String name, String email, int userID , int limtOfCart) {
        super(name, email, userID);
        this.limtOfCart = limtOfCart;
        
    }

    // Method to display the admin's profile information.
    @Override
    public void profileDisplay() {

        // Displaying admin's name and email
        System.out.println("Admin name: " + name);
        System.out.println("Admin email: " + email);

        // Displaying the role and the maximum number of products that can purchase.
        System.out.println("Admin Role: the customer is abel to purchase a maximum of " + limtOfCart + " products");
    
    }

    @Override
    public void checkout() {
        System.out.println("Admin can't buy anything");
    }

}
