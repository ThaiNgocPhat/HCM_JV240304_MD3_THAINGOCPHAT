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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Main {
    private static CategoryService categoryService = new CategoryService();
    private static ProductService productService = new ProductService();
    private static UserService userService = new UserService();
    private static OrderService orderService = new OrderService();
    private static IOFile<Object> productIO;

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
                    viewProducts();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    viewOrders();
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
            System.out.println("2. Find Category by ID");
            System.out.println("3. View Categories");
            System.out.println("4. Update Category");
            System.out.println("5. Delete Category");
            System.out.println("6. Back to Admin Menu");
            int choice = Integer.parseInt(InputUtil.getString("Enter choice: "));
            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    findById();
                    break;
                case 3:
                    viewCategories();
                    break;
                case 4:
                    updateCategory();
                    break;
                case 5:
                    deleteCategory();
                    break;
                case 6:
                    System.out.println("Back to Admin Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageProducts() throws IOException, ClassNotFoundException {
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
        // Input user details
        String name = InputUtil.getString("Enter your name: ");
        String email = InputUtil.getString("Enter your email: ");
        String password = InputUtil.getString("Enter your password: ");
        String phone = InputUtil.getString("Enter your phone number: ");
        String address = InputUtil.getString("Enter your address: ");
        // Initialize IOFile object and target file
        IOFile<User> userIO = new IOFile<>();
        File file = new File(IOFile.USER_PATH);
        File dir = file.getParentFile();
        // Ensure the directory exists or create it
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created: " + dir.getPath());
            } else {
                System.err.println("Failed to create directory: " + dir.getPath());
                return;
            }
        }
        // Ensure the file exists or create it
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getPath());
                }
            } catch (IOException e) {
                System.err.println("Failed to create file: " + file.getPath());
                e.printStackTrace();
                return;
            }
        }
        // Read existing users from file
        List<User> users;
        try {
            users = userIO.readFromFile(IOFile.USER_PATH);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read users from file");
            e.printStackTrace();
            return;
        }
        // Generate new user ID
        int newId = users.isEmpty() ? 1 : users.get(users.size() - 1).getId() + 1;
        // Create a new user object with the generated ID
        User user = new User(newId, name, email, password, phone, address);
        // Add the new user to the list
        users.add(user);
        // Write the updated user list back to the file
        try {
            userIO.writeToFile(IOFile.USER_PATH, users);
            System.out.println("User registered successfully!");
        } catch (IOException e) {
            System.err.println("Failed to write users to file");
            e.printStackTrace();
        }
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
    public static void addCategory() {
        try {
            // Nhập thông tin danh mục từ người dùng
            System.out.println("---------- Thêm Danh Mục ----------");
            String name = InputUtil.getString("Nhập tên danh mục: ");

            // Kiểm tra tên không được để trống
            if (name == null || name.trim().isEmpty()) {
                System.err.println("Tên danh mục không được để trống.");
                return;
            }

            // Tạo đối tượng danh mục
            Category category = new Category();
            category.setCategoryName(name);

            // Đảm bảo service đã được khởi tạo
            if (categoryService == null) {
                System.err.println("Dịch vụ danh mục chưa được khởi tạo.");
                return;
            }

            // Lấy danh sách danh mục hiện tại
            List<Category> categories = categoryService.getAll();

            // Tìm id lớn nhất và tăng lên 1 để làm id cho danh mục mới
            int maxId = 0;
            for (Category cat : categories) {
                int catId = Integer.parseInt(cat.getCategoryId());
                if (catId > maxId) {
                    maxId = catId;
                }
            }
            // Tăng id lớn nhất lên 1 để làm id cho danh mục mới
            String newCategoryId = String.valueOf(maxId + 1);
            category.setCategoryId(Integer.parseInt(newCategoryId));

            // Thêm danh mục vào service
            categoryService.create(category);
            System.out.println("Đã thêm danh mục thành công!");

            // Lưu danh mục vào file
            IOFile<Category> categoryIOFile = new IOFile<>();
            File file = new File(IOFile.CATEGORY_PATH);
            File dir = file.getParentFile();

            // Tạo thư mục nếu chưa tồn tại
            if (!dir.exists()) {
                if (dir.mkdirs()) {
                    System.out.println("Đã tạo thư mục: " + dir.getPath());
                } else {
                    System.err.println("Không thể tạo thư mục: " + dir.getPath());
                    return;
                }
            }

            // Tạo file nếu chưa tồn tại
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("Đã tạo file: " + file.getPath());
                    }
                } catch (IOException e) {
                    System.err.println("Không thể tạo file: " + file.getPath());
                    e.printStackTrace();
                    return;
                }
            }

            // Đọc danh sách danh mục từ file
            try {
                categories = categoryIOFile.readFromFile(IOFile.CATEGORY_PATH);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Không thể đọc danh mục từ file");
                e.printStackTrace();
                return;
            }

            // Thêm danh mục mới vào danh sách
            categories.add(category);

            // Ghi danh sách danh mục đã cập nhật vào file
            try {
                categoryIOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
                System.out.println("Đã lưu danh sách danh mục vào file thành công!");
            } catch (IOException e) {
                System.err.println("Không thể ghi danh sách danh mục vào file");
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi thêm danh mục");
            e.printStackTrace();
        }
    }




    //Find by id
    public static void findById() {
        String id = InputUtil.getString("Enter category ID to find: ");
        Category category = categoryService.read(id);
        if (category != null) {
            System.out.println("ID: " + category.getCategoryId() + ", Name: " + category.getCategoryName());
        } else {
            System.out.println("Category with ID " + id + " not found.");
        }
    }



//    View all Category
public static void viewCategories() {
    List<Category> categories = categoryService.getAll();
    if (categories.isEmpty()) {
        System.out.println("Không có danh mục nào.");
    } else {
        System.out.println("ID\tTên");
        for (Category category : categories) {
            System.out.println(category.getCategoryId() + "\t" + category.getCategoryName());
        }
    }
}



    //Update Category
    public static void updateCategory() {
        IOFile<Category> categoryIOFile = new IOFile<>();
        File file = new File(IOFile.CATEGORY_PATH);
        // Đảm bảo file tồn tại
        if (!file.exists()) {
            System.out.println("File danh mục không tồn tại.");
            return;
        }
        try {
            List<Category> categories = categoryIOFile.readFromFile(IOFile.CATEGORY_PATH);
            String id = InputUtil.getString("Nhập ID danh mục cần cập nhật: ");
            Category category = categoryService.read(id);
            if (category != null) {
                String name = InputUtil.getString("Nhập tên danh mục mới: ");
                category.setCategoryName(name);
                categoryService.update(category);
                // Cập nhật danh mục trong danh sách
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getCategoryId().equals(id)) {
                        categories.set(i, category);
                        break;
                    }
                }
                // Ghi danh sách đã cập nhật vào file
                categoryIOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
                System.out.println("Đã cập nhật danh mục thành công và lưu vào file!");
            } else {
                System.out.println("Không tìm thấy danh mục");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc hoặc ghi file: " + e.getMessage());
            e.printStackTrace();
        }
    }



    //Delete Category
    public static void deleteCategory() {
        String id = InputUtil.getString("Nhập ID danh mục cần xóa: ");
        Category category = categoryService.read(id);
        if (category != null) {
            // Xóa danh mục
            categoryService.delete(id);
            System.out.println("Đã xóa danh mục thành công.");

            // Tùy chọn, xóa khỏi file nếu cần
            IOFile<Category> categoryIOFile = new IOFile<>();
            try {
                List<Category> categories = categoryIOFile.readFromFile(IOFile.CATEGORY_PATH);
                categories.removeIf(c -> c.getCategoryId().equals(id));
                categoryIOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
                System.out.println("Đã xóa danh mục khỏi file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Lỗi khi cập nhật file sau khi xóa: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Không tìm thấy danh mục có ID " + id);
        }
    }




    //ADD PRODUCT
    public static void addProduct() {
        try {
            System.out.println("---------- Add Product ----------");

            // Validate and get product name
            String productName = InputUtil.getString("Enter product name: ");
            if (productName.isEmpty()) {
                System.err.println("Product name cannot be empty. Please try again.");
                return;
            }

            // Validate and get product description
            String productDescription = InputUtil.getString("Enter product description: ");
            if (productDescription.isEmpty()) {
                System.err.println("Product description cannot be empty. Please try again.");
                return;
            }

            // Validate and get product quantity
            int quantity;
            while (true) {
                try {
                    quantity = Integer.parseInt(InputUtil.getString("Enter quantity: "));
                    if (quantity <= 0) {
                        System.err.println("Quantity must be greater than zero. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input for quantity, please enter a valid integer.");
                }
            }

            // Validate and get product price
            double productPrice;
            while (true) {
                try {
                    productPrice = Double.parseDouble(InputUtil.getString("Enter product price: "));
                    if (productPrice <= 0) {
                        System.err.println("Product price must be greater than zero. Please try again.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input for product price, please enter a valid number.");
                }
            }

            // Ensure categories are loaded or created
            List<Category> categories = categoryService.getAll();
            if (categories.isEmpty()) {
                System.out.println("No categories found. Please add categories first.");
                return;
            }

            // Display available categories for user selection
            System.out.println("Available categories:");
            for (Category category : categories) {
                System.out.println("- ID: " + category.getCategoryId() + ", Name: " + category.getCategoryName());
            }

            // Prompt user to enter category ID
            String categoryIdInput = InputUtil.getString("Enter category ID for the product: ");
            Optional<Category> selectedCategory = categories.stream()
                    .filter(category -> category.getCategoryId().equals(categoryIdInput))
                    .findFirst();

            if (!selectedCategory.isPresent()) {
                System.err.println("Invalid category ID. Please try again.");
                return;
            }

            // Create a new product object with the collected information
            Product product = new Product(productName, productDescription, productPrice, quantity, categoryIdInput);

            // Add the new product to the list
            List<Product> products = productService.getAllProducts();
            products.add(product);

            // Save the updated list of products
            productService.saveProducts(products);

            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }






    //DISPLAY PRODUCT
    public static void viewProducts(){
        List<Product> products = productService.getAll();
        for(Product product : products){
            System.out.println("ID: " + product.getProductId() + ", Name:" + product.getProductDes() + ", Descripton:" + product.getProductDes()
            + ", Quantity: " + product.getQuantity() + ", Price: " + product.getProductPrice() + ", Category: " + product.getCategory());
        }
    }

    //UPDATE PRODUCT
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
    public static void placeOrder() {
        //tạo đơn hàng mới
        Order order = new Order();
        order.setUserId(InputUtil.getString("Enter user ID: "));
        List<Product> products = productService.getAll();
        System.out.println("Products available:");
        for (Product product : products) {
            System.out.println("ID: " + product.getProductId() + ", Name: " + product.getProductName() + ", Price: " + product.getProductPrice());
        }
        int productId = Integer.parseInt(InputUtil.getString("Enter product ID to add to order: "));
        Product product = productService.read(String.valueOf(productId));
        if (product != null) {
            order.addProduct(product);
            orderService.create(order);
            System.out.println("Order placed successfully. Order ID: " + order.getOrderId());
        } else {
            System.out.println("Product not found");
        }
    }
    public static void viewOrders(){
        List<Order> orders = orderService.getAll();
        for(Order order : orders){
            System.out.println("ID: " + order.getOrderId() + ", User ID: " + order.getUserId() + ", Total Price: " + order.getTotalAmount()
            + ", Date: " + order.getDate());
        }
    }
}

