

import javax.swing.JOptionPane;

public class Electronics extends Product
{

    // Stores the warranty period of the electronic product.
    private int warrantyPeriod;

    // Stores the power supply information of the electronic product.
    private String powerSupply;


    // Constructor for Electronics class,
    public Electronics(int productId, String name, double price, String brand, int warrantyPeriod, String powerSupply) 
    {

      // Calling the constructor of the supwrclass (Product) to initialize common attributes.
      super(productId, name, price, brand);

      // Seeting the warranty period
      this.warrantyPeriod = warrantyPeriod;

      // Setting the power supply information.
      this.powerSupply = powerSupply;
    }


    // Getter method for rrtrieving the warranty period of the electronic product.
    public int getWarrantyPeriod()
    {
        return warrantyPeriod;
    }

    // Getter method for retrieving the power supply information of the electronic product.
    public String getPowerSupply()
    {
        return powerSupply;
    }

    // Method to display details of the electronic product.
    @Override
    public void displayDetails(){   

        try {
        	
          // Writing product details to a file
          writerPR.write("Product ID: " + getProductId());
            writerPR.write("\n" + "Name: " + getName());
              writerPR.write("\n" + "Price: $" + getPrice());
              writerPR.write("\n" + "Brand: " + getBrand());
            writerPR.write("\n"  + "Warranty Period: " + getWarrantyPeriod() + " months" );
          writerPR.write("\n" + "Power Supply: " + getPowerSupply());
          if (ThePriceAfterDiscount() > 0) {
            // Checking if there's a discount applied and writing the discounted price.
            writerPR.write("\n" + "The price after discount: " + ThePriceAfterDiscount());
          }
            
            writerPR.write("\n" + "********************" + "\n");

        

         } catch (Exception e) {
          // Displaying an error message using a dialog box if an exception occurs.
         JOptionPane.showMessageDialog(null, e);
       }

    }

    // Method to calculate the discount for the electronic product.
    @Override
    public double calculateDiscount() 
    {
    	return  getPrice()* 0.15;
    }
    
    // Method to calculate the price after applying the discount.
    public  double ThePriceAfterDiscount() 
    {
    	return 	getPrice()-(getPrice()* 0.15);
    }

    
    
    
}
