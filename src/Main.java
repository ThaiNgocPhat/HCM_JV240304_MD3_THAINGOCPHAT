import models.Category;
import models.Order;
import models.Product;
import models.User;
import services.CategoryService;
import services.OrderService;
import services.ProductService;
import services.UserService;
import utils.IOFile;
import utils.InputUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static CategoryService categoryService = new CategoryService();
    private static ProductService productService = new ProductService();
    private static UserService userService = new UserService();
    private static OrderService orderService = new OrderService();
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("1. Admin login");
            System.out.println("2. Register");
            System.out.println("3. Login");
            System.out.println("4. Exit");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    login();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("----------Admin Menu----------");
            System.out.println("1. Manage Categories");
            System.out.println("2. Manage Products");
            System.out.println("3. Manage Users");
            System.out.println("4. Manage Orders");
            System.out.println("5. Logout");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
                    manageCategories();
                    break;
                case 2:
                    manageProducts();
                    break;
                case 3:
                    manageUsers();
                    break;
                case 4:
                    manageOrders();
                    break;
                case 5:
                    System.out.println("Logout successful!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("----------User Menu----------");
            System.out.println("1. View Products");
            System.out.println("2. Place Order");
            System.out.println("3. View Orders");
            System.out.println("4. Logout");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
//                    viewProducts();
                    break;
                case 2:
//                    placeOrder();
                    break;
                case 3:
//                    viewOrders();
                    break;
                case 4:
                    System.out.println("Logout successful!");
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void manageCategories() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("----------Manage Categories----------");
            System.out.println("1. Add Category");
            System.out.println("2. View Categories");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Back to Admin Menu");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    viewCategories();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    System.out.println("Back to Admin Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageProducts() {
        while (true) {
            System.out.println("-----------Manage Products-----------");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back to Admin Menu");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
//                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void manageUsers() {
        while (true) {
            System.out.println("-----------Manage Users-----------");
            System.out.println("1. View Users");
            System.out.println("2. Delete User");
            System.out.println("3. Back to Admin Menu");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
                    viewUsers();
                    break;
                case 2:
                    updateStatus();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageOrders() {
        while (true) {
            System.out.println("----------Manage Orders----------");
            System.out.println("1. View Orders");
            System.out.println("2. Delete Order");
            System.out.println("3. Back to Admin Menu");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    deleteOrder();
                    break;
                case 3:
                    updateStatusOrder();
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //Login mặc định cho Admin
    public static void loginAdmin() throws IOException, ClassNotFoundException {
        System.out.println("----------Welcome Admin----------");
        String adminName = InputUtil.getString("Enter admin name: ");
        String adminPassword = InputUtil.getString("Enter admin password: ");
        if (adminName.equals("admin") && adminPassword.equals("admin")) {
            System.out.println("Login successful!");
            adminMenu();
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    //đăng ký cho user
    public static void register() throws IOException, ClassNotFoundException {
        System.out.println("---------- Register ----------");
        String name = InputUtil.getString("Enter your name: ");
        String email = InputUtil.getString("Enter your email: ");
        String password = InputUtil.getString("Enter your password: ");
        String phone = InputUtil.getString("Enter your phone number: ");
        String address = InputUtil.getString("Enter your address: ");
        System.out.println("Register successful!");
        IOFile<User> userIO = new IOFile<>();
        File file = new File(IOFile.USER_PATH);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getPath());
            } else {
                System.err.println("Failed to create directory: " + dir.getPath());
                return;
            }
        }
        try {
            if (!file.exists() && file.createNewFile()) {
                System.out.println("File created: " + file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        List<User> users = userIO.readFromFile(IOFile.USER_PATH);
        User user = new User(name, email, password, phone, address);
        users.add(user);
        userIO.writeToFile(IOFile.USER_PATH, users);
    }

    //đăng nhập đối với user
    public static void login() throws IOException, ClassNotFoundException {
        System.out.println("----------Welcome User----------");
        String email = InputUtil.getString("Enter your email: ");
        String password = InputUtil.getString("Enter your password: ");
        IOFile<User> userIO = new IOFile<>();
        List<User> users = userIO.readFromFile(IOFile.USER_PATH);
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                userMenu();
                return;
            }
        }
        System.out.println("Login failed. Please try again.");
    }
    //Add Category
    public static void addCategory() throws IOException, ClassNotFoundException {
        String name = InputUtil.getString("Enter category name: ");
        int quantity = Integer.parseInt(InputUtil.getString("Enter quantity: "));
        Category category = new Category(name, quantity);
        categoryService.create(category);
        System.out.println("Category added successfully!");

        // Save the category to Category.txt
        IOFile<Category> categoryIOFile = new IOFile<>();
        File file = new File(IOFile.CATEGORY_PATH);
        File dir = file.getParentFile();

        // Ensure the directory exists
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getPath());
            } else {
                System.err.println("Failed to create directory: " + dir.getPath());
                return;
            }
        }

        // Ensure the file exists
        try {
            if (!file.exists() && file.createNewFile()) {
                System.out.println("File created: " + file.getPath());
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Read existing categories, add new category, and write back to the file
        List<Category> categories = categoryIOFile.readFromFile(IOFile.CATEGORY_PATH);
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
        categoryIOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
        System.out.println("Category saved to file successfully!");
    }


    //View all Category
    public static void viewCategories(){
        List<Category> categories = categoryService.getAll();
        for (Category category : categories){
            System.out.println("ID: " + category.getCategoryId() + ", Name: " + category.getCategoryName() + ", Quantity: " + category.getCategoryQuantity());
        }
    }

    //Update Category
    public static void updateCategory() {
        IOFile<Category> categoryIOFile = new IOFile<>();
        File file = new File(IOFile.CATEGORY_PATH);
        // Ensure the file exists
        if (!file.exists()) {
            System.out.println("Category file does not exist.");
            return;
        }
        try {
            List<Category> categories = categoryIOFile.readFromFile(IOFile.CATEGORY_PATH);
            String id = InputUtil.getString("Enter category ID to update: ");
            Category category = categoryService.read(id);
            if (category != null) {
                String name = InputUtil.getString("Enter new category name: ");
                int quantity = Integer.parseInt(InputUtil.getString("Enter new quantity: "));
                category.setCategoryName(name);
                category.setCategoryQuantity(quantity);
                categoryService.update(category);
                // Update the category in the list
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getCategoryId().equals(id)) {
                        categories.set(i, category);
                        break;
                    }
                }
                // Write updated list back to file
                categoryIOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
                System.out.println("Category updated successfully and saved to file!");
            } else {
                System.out.println("Category not found");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void deleteCategory(){
        String id = InputUtil.getString("Enter category ID to delete: ");
        categoryService.delete(id);
        System.out.println("Deleted successfully");
    }

    public static void addProduct() {
        String productName = InputUtil.getString("Enter product name: ");
        String productDes = InputUtil.getString("Enter description: ");
        int quantity = Integer.parseInt(InputUtil.getString("Enter quantity: "));
        double productPrice = Double.parseDouble(InputUtil.getString("Enter product price: "));
        String category = InputUtil.getString("Enter product category: ");

        Product product = new Product(productName, productDes, productPrice, quantity, category);
        productService.create(product);
        System.out.println("Product added successfully!");

        // Save the product to Product.txt
        IOFile<Product> productIOFile = new IOFile<>();
        File file = new File(IOFile.PRODUCT_PATH);
        File dir = file.getParentFile();

        // Ensure the directory exists
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getPath());
            } else {
                System.err.println("Failed to create directory: " + dir.getPath());
                return;
            }
        }
        // Ensure the file exists
        try {
            if (!file.exists() && file.createNewFile()) {
                System.out.println("File created: " + file.getPath());
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        // Read existing products, add new product, and write back to the file
        try {
            List<Product> products = productIOFile.readFromFile(IOFile.PRODUCT_PATH);
            if (products == null) {
                products = new ArrayList<>();
            }
            products.add(product);
            productIOFile.writeToFile(IOFile.PRODUCT_PATH, products);
            System.out.println("Product saved to file successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void viewProducts(){
        List<Product> products = productService.getAll();
        for(Product product : products){
            System.out.println("ID: " + product.getProductId() + ", Name:" + product.getProductDes() + ", Descripton:" + product.getProductDes()
            + ", Quantity: " + product.getQuantity() + ", Price: " + product.getProductPrice() + ", Category: " + product.getCategory());
        }
    }
    public static void updateProduct() {
        IOFile<Product> productIOFile = new IOFile<>();
        File file = new File(IOFile.PRODUCT_PATH);

        // Ensure the file exists
        if (!file.exists()) {
            System.out.println("Product file does not exist.");
            return;
        }

        try {
            List<Product> products = productIOFile.readFromFile(IOFile.PRODUCT_PATH);

            String id = InputUtil.getString("Enter product ID to update: ");
            Product product = productService.read(id);

            if (product != null) {
                String name = InputUtil.getString("Enter new product name: ");
                String des = InputUtil.getString("Enter new product description: ");
                double price = Double.parseDouble(InputUtil.getString("Enter new product price: "));
                int quantity = Integer.parseInt(InputUtil.getString("Enter new product quantity: "));
                product.setProductName(name);
                product.setProductDes(des);
                product.setProductPrice(price);
                product.setQuantity(quantity);
                productService.update(product);
                // Write updated list back to file
                productIOFile.writeToFile(IOFile.PRODUCT_PATH, products);
                System.out.println("Product updated successfully and saved to file!");
                // Write updated list back to file
                productIOFile.writeToFile(IOFile.PRODUCT_PATH, products);
                System.out.println("Product updated successfully and saved to file!");
            } else {
                System.out.println("Product not found");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void deleteProduct(){
        String id = InputUtil.getString("Enter product ID to delete: ");
        productService.delete(id);
        System.out.println("Product delete successfully. !!!");
    }
    public static void viewUsers(){
        List<User> users = userService.getAll();
        for(User user : users){
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail()
            + ", Phone: " + user.getPhone() + ", Address: " + user.getAddress());
        }
    }
    public static void updateStatus(){
        //nếu trạng thái là false thì không cho đăng nhập
        String id = InputUtil.getString("Enter user ID to update: ");
        User user = userService.read(id);
        if (user!= null){
            boolean status = InputUtil.getBoolean("Enter new status (true/false): ");
            user.setStatus(status);
            userService.update(user);
            System.out.println("Status update successfully. !!!");
        }else{
            System.out.println("User not found");
        }
    }
    public static void viewAllOrders(){
        List<Order> orders = orderService.getAll();
        for(Order order : orders){
            System.out.println("ID: " + order.getOrderId() + ", User ID: " + order.getUserId() + ", Total Price: " + order.getTotalAmount()
            + ", Date: " + order.getDate());
        }
    }
    public static void deleteOrder(){
        String id = InputUtil.getString("Enter order ID to delete: ");
        orderService.delete(id);
        System.out.println("Order delete successfully. !!!");
    }
    public static void updateStatusOrder(){
        //nếu trạng thái là false thì không cho đăng nhập
        String id = InputUtil.getString("Enter order ID to update: ");
        Order order = orderService.read(id);
        if (order!= null){
            boolean status = InputUtil.getBoolean("Enter new status (true/false): ");
            order.setStatus(status);
            orderService.update(order);
            System.out.println("Status update successfully. !!!");
        }else{
            System.out.println("Order not found");
        }
    }
}

