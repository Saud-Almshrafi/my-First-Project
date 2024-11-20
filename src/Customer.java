import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Customer extends User {

    // Shipping address of the customer
    String shippingAddress;

    // Payment method chosen by the customer
    String paymentMethod;


    // Constructor to initialize Customer object with specific attributes
    public Customer(String name , String email , int userID , String shippingAddress , String paymentMethod){
        
        // Call superclass constructor to initialize
        super(name , email , userID);
        
        // Initialize specific attributes of Customer
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }
    

    // Method to display profile information of the customer
    @Override
    public void profileDisplay(){
        try{

        // Create a BufferedWriter to write profile information to a file
        writerUS = new BufferedWriter(new FileWriter("profile.txt"));

        // Write profile information to the file
        writerUS.write("Hello dear customer");
          writerUS.write("\n" + "*************your profile*************" + "\n");
            writerUS.write("\n" + "Name : " + name);
              writerUS.write("\n" + "Email : " + email);
            writerUS.write("\n" + "Your ID : " + userID);
          writerUS.write("\n" + "your Address : " + shippingAddress + "\n");
        writerUS.write("\n" + "**************************************");

        // Close the BufferedWriter after writing
        writerUS.close();

        } catch(Exception e){
            // Display error message in case of an
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // Method representing the checkout process for a customer
    @Override
    public void checkout(){

        // Variable to store the total price of products in the cart
        double totelPrice = 0;

        try{

        // Create a BufferedWriter to write item details to a file
        Product.writerPR = new BufferedWriter(new FileWriter("ITEM.txt"));
        }catch(IOException e){

            // Display error message in case of an exception
            JOptionPane.showMessageDialog(null, e);
        }

        // Iterate through the products in the cart
        for (Product product : cart) {
            
            // Display details of each product and calculate the total price
            product.displayDetails();
            totelPrice += product.ThePriceAfterDiscount();
        }

        // Round the total price to an integer
        int t = (int) totelPrice;
        try{
        
        // Write the total price to the file and close the BufferedWriter
        Product.writerPR.write("Totel price: $" + t);
        Product.writerPR.close();
        } catch (Exception e){

            // Display error message in case of an exception
            JOptionPane.showMessageDialog(null, e);
        }
        
       
    }

}

