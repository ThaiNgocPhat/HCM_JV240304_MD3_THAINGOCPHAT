package models;

public class Product {
    private int productId;
    private String productName;
    private String productDes;
    private double productPrice;
    private int quantity;
    private Category category;
    private static int autoId = 1;

    public Product() {
    }

    public Product(String productName, String productDes, double productPrice, int quantity, Category category) {
        this.productId = autoId++;
        this.productName = productName;
        this.productDes = productDes;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(String name, String des, double price, double price1, String category) {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Product.autoId = autoId;
    }
}
