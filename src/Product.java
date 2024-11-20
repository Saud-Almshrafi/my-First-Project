import java.io.BufferedWriter;

public abstract class Product {
    private int productId;
    private String name;
    private double price;
    private String brand;

    static BufferedWriter writerPR;
    
    
    
    public Product(int productId, String name, double price, String brand) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.brand = brand;
    }
     

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public abstract void displayDetails();
    public abstract double calculateDiscount();
    public abstract double ThePriceAfterDiscount();

    
}