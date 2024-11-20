import java.io.BufferedWriter;
import java.util.ArrayList;

public abstract class User {

    String name;
    String email;
    int userID;
    ArrayList<Product> cart = new ArrayList<>();
    BufferedWriter writerUS;

    public User(String name , String email , int userID ){
        this.name = name;
        this.email = email;
        this.userID = userID;
        
    }

    public abstract void profileDisplay();

    public void addToCart(Product item){
        cart.add(item);
    }

    public void rmvFromCart(Product item){
        cart.remove(item);
    }

    public abstract void checkout();

}
