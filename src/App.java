import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App {
    class CartItem {
        Product product;
        int quantity;

        CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        double getTotalPrice() {
            return product.getPrice() * quantity;
        }

        double getTotalPriceAfterDiscount() {
            if (product instanceof Electronics) {
                return ((Electronics) product).ThePriceAfterDiscount() * quantity;
            } else if (product instanceof Clothing) {
                return ((Clothing) product).ThePriceAfterDiscount() * quantity;
            }
            return getTotalPrice();
        }

        double getTotalDiscount() {
            return getTotalPrice() - getTotalPriceAfterDiscount();
        }
    }

    private ArrayList<CartItem> cart = new ArrayList<>();
    private JLabel cartStatusLabel = new JLabel("Cart: 0 items");

    public static void main(String[] args) {
        new App().showMainMenu();
    }

    private void updateCartStatus() {
        int totalItems = cart.stream().mapToInt(item -> item.quantity).sum();
        cartStatusLabel.setText("Cart: " + totalItems + " items");
    }

    public void showMainMenu() {
        JFrame mainMenu = createFrame("Main Menu");

        JLabel welcomeLabel = new JLabel("<html>Welcome to <b>MyStore</b>!<br>Find the best products just for you.</html>", SwingConstants.CENTER);
        welcomeLabel.setBounds(100, 50, 500, 100);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(0xff8c00));
        mainMenu.add(welcomeLabel);

        JButton electronicsButton = new JButton("Electronics");
        electronicsButton.setBounds(260, 200, 200, 50);
        electronicsButton.setBackground(new Color(0x81d4fa));

        JButton clothingButton = new JButton("Clothing");
        clothingButton.setBounds(260, 300, 200, 50);
        clothingButton.setBackground(new Color(0x81d4fa));

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(260, 400, 200, 50);
        viewCartButton.setBackground(new Color(0xff8c00));

        mainMenu.add(electronicsButton);
        mainMenu.add(clothingButton);
        mainMenu.add(viewCartButton);

        electronicsButton.addActionListener(e -> {
            mainMenu.dispose();
            showElectronicsPage();
        });

        clothingButton.addActionListener(e -> {
            mainMenu.dispose();
            showClothingPage();
        });

        viewCartButton.addActionListener(e -> showCart());

        mainMenu.setVisible(true);
    }

    public void showElectronicsPage() {
        JFrame electronicsFrame = createFrame("Electronics");

        cartStatusLabel.setBounds(20, 20, 200, 30);
        cartStatusLabel.setForeground(new Color(0xff8c00));
        electronicsFrame.add(cartStatusLabel);

        addProduct(electronicsFrame, new Electronics(1, "Laptop", 1999, "HP", 12, "Type-C"), 100, 150, ".\\photo\\Macbook.png");
        addProduct(electronicsFrame, new Electronics(2, "iPhone", 3999, "Apple", 24, "Lightning"), 350, 150, ".\\photo\\iPhone.png");
        addProduct(electronicsFrame, new Electronics(3, "AirPods", 899, "Apple", 12, "Wireless"), 600, 150, ".\\photo\\AirPod.png");

        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 520, 120, 40);
        backButton.setBackground(new Color(0xff8c00));
        electronicsFrame.add(backButton);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(600, 520, 120, 40);
        checkoutButton.setBackground(new Color(0xff8c00));
        electronicsFrame.add(checkoutButton);

        backButton.addActionListener(e -> {
            electronicsFrame.dispose();
            showMainMenu();
        });

        checkoutButton.addActionListener(e -> showInvoice());

        electronicsFrame.setVisible(true);
    }

    public void showClothingPage() {
        JFrame clothingFrame = createFrame("Clothing");

        cartStatusLabel.setBounds(20, 20, 200, 30);
        cartStatusLabel.setForeground(new Color(0xff8c00));
        clothingFrame.add(cartStatusLabel);

        addProduct(clothingFrame, new Clothing(4, "T-shirt", 59, "Nike", "L", "Grey"), 100, 150, ".\\photo\\T-shirt.png");
        addProduct(clothingFrame, new Clothing(5, "shows", 249, "Adidas", "42", "Black"), 350, 150, ".\\photo\\Nike.png");
        addProduct(clothingFrame, new Clothing(6, "Jeans", 79, "Levis", "M", "Blue"), 600, 150, ".\\photo\\Shorts.png");

        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 520, 120, 40);
        backButton.setBackground(new Color(0xff8c00));
        clothingFrame.add(backButton);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(600, 520, 120, 40);
        checkoutButton.setBackground(new Color(0xff8c00));
        clothingFrame.add(checkoutButton);

        backButton.addActionListener(e -> {
            clothingFrame.dispose();
            showMainMenu();
        });

        checkoutButton.addActionListener(e -> showInvoice());

        clothingFrame.setVisible(true);
    }

    private void addProduct(JFrame frame, Product product, int x, int y, String imagePath) {
        JLabel productImage = new JLabel();
        productImage.setBounds(x, y, 120, 120);

        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getIconWidth() == -1) {
            System.out.println("Image not found for " + imagePath);
        }
        Image img = icon.getImage().getScaledInstance(productImage.getWidth(), productImage.getHeight(), Image.SCALE_SMOOTH);
        productImage.setIcon(new ImageIcon(img));

        JLabel productLabel = new JLabel(product.getName() + " - $" + product.getPrice(), SwingConstants.LEFT);
        productLabel.setBounds(x, y + 130, 200, 30);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantitySpinner.setBounds(x, y + 170, 40, 30);

        JButton addButton = new JButton("Add");
        addButton.setBounds(x + 50, y + 170, 80, 30);
        addButton.setBackground(new Color(0xb3e5fc));

        addButton.addActionListener(e -> {
            int quantity = (int) quantitySpinner.getValue();
            cart.add(new CartItem(product, quantity));
            JOptionPane.showMessageDialog(frame, product.getName() + " (x" + quantity + ") added to cart!");
            updateCartStatus();
        });

        frame.add(productImage);
        frame.add(productLabel);
        frame.add(quantitySpinner);
        frame.add(addButton);
    }

    private JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon bgIcon = new ImageIcon(".\\res\\background.png");
                    Image bgImage = bgIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(bgImage, 0, 0, null);
                } catch (Exception e) {
                    System.out.println("Error loading background: " + e.getMessage());
                }
            }
        };

        backgroundPanel.setBounds(0, 0, 800, 600);
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        return frame;
    }

    private void showCart() {
        JFrame cartFrame = new JFrame("Your Cart");
        cartFrame.setSize(400, 400);
        cartFrame.setLayout(new BorderLayout());

        JTextArea cartArea = new JTextArea();
        cartArea.setEditable(false);

        if (cart.isEmpty()) {
            cartArea.setText("Your cart is empty!");
        } else {
            StringBuilder cartText = new StringBuilder();
            for (CartItem item : cart) {
                cartText.append(item.product.getName())
                        .append(" x")
                        .append(item.quantity)
                        .append(" = $")
                        .append(item.getTotalPrice())
                        .append("\n");
            }
            cartArea.setText(cartText.toString());
        }

        JScrollPane scrollPane = new JScrollPane(cartArea);

        JButton removeButton = new JButton("Remove All");
        removeButton.addActionListener(e -> {
            cart.clear();
            updateCartStatus();
            cartArea.setText("Your cart is now empty!");
        });

        cartFrame.add(scrollPane, BorderLayout.CENTER);
        cartFrame.add(removeButton, BorderLayout.SOUTH);
        cartFrame.setVisible(true);
    }

    private void showInvoice() {
        JFrame invoiceFrame = new JFrame("Invoice");
        invoiceFrame.setSize(480, 480);
        invoiceFrame.setLayout(new BorderLayout());
        invoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea invoiceArea = new JTextArea();
        invoiceArea.setEditable(false);

        if (cart.isEmpty()) {
            invoiceArea.setText("Your cart is empty!");
        } else {
            StringBuilder invoiceText = new StringBuilder("Invoice:\n\n");
            double totalBeforeDiscount = 0;
            double totalAfterDiscount = 0;

            for (CartItem item : cart) {
                double totalPrice = item.getTotalPrice();
                double totalPriceAfterDiscount = item.getTotalPriceAfterDiscount();
                double discount = item.getTotalDiscount();

                invoiceText.append(item.product.getName())
                        .append(" x")
                        .append(item.quantity)
                        .append(" = $")
                        .append(totalPrice)
                        .append("\n  Discount: $")
                        .append(discount)
                        .append("\n  After Discount: $")
                        .append(totalPriceAfterDiscount)
                        .append("\n\n");

                totalBeforeDiscount += totalPrice;
                totalAfterDiscount += totalPriceAfterDiscount;
            }

            invoiceText.append("Total Before Discount: $").append(totalBeforeDiscount).append("\n");
            invoiceText.append("Total After Discount: $").append(totalAfterDiscount).append("\n");
            invoiceArea.setText(invoiceText.toString());
        }

        invoiceFrame.add(new JScrollPane(invoiceArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> invoiceFrame.dispose());
        invoiceFrame.add(closeButton, BorderLayout.SOUTH);

        invoiceFrame.setVisible(true);
    }
}
