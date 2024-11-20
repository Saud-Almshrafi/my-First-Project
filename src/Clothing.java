import javax.swing.JOptionPane;

public class Clothing extends Product
{
    // Size of the clothing
    private String size;

    // Color of the clothing
    private String color;


    // Constructor to initialize Clothing object with specific attributes
    public Clothing(int productId, String name, double price, String brand, String size, String color)
    {

        // Call superclass constructor to initialize common attributes
        super(productId, name, price, brand);

        // Initialize specific attributes of Clothing
        this.size = size;
        this.color = color;
    }

    
    // Getter method for retrieving size of the clothing
    public String getSize() 
    {
       return size;
    }

    public String getColor()
    {
        return color;
    }

    // Method to display details of the clothing
    public void displayDetails()
    {

        try {
            
              writerPR.write("Product ID: " + getProductId());
                writerPR.write("\n" + "Name: " + getName());
                 writerPR.write("\n" + "Price: $" + getPrice());
                 writerPR.write("\n" + "Brand: " + getBrand());
                writerPR.write("\n"  + "Size: " + getSize() );
              writerPR.write("\n" + "Color: " + getColor());
              if (ThePriceAfterDiscount() > 0) {
                writerPR.write("\n" + "The price after discount: " + ThePriceAfterDiscount());
              }

              writerPR.write("\n" + "********************" + "\n");
    
            
    
             } catch (Exception e) {
             // Show error message in case of an exception
             JOptionPane.showMessageDialog(null, e);
           }

    }

    // Method to calculate the discount for the clothing
    public double calculateDiscount()
    {
        return getPrice()*0.20;
    }

    // Method to calculate the price after applying the discount
    public  double ThePriceAfterDiscount() 
    {
    	return getPrice()-(getPrice()*0.20);
    }

}