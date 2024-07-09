package models;//package models;
//
//import java.io.Serializable;
//
//public class User implements Serializable {
//    private int id;
//    private String name;
//    private String email;
//    private String password;
//    private String phone;
//    private String address;
//    private static int autoId = 1;
//    public User() {
//    }
//
//    public User(String name, String email, String password, String phone, String address) {
//        this.id = autoId++;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.phone = phone;
//        this.address = address;
//    }
//
//    public String getId() {
//        return String.valueOf(id);
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public static int getAutoId() {
//        return autoId;
//    }
//
//    public static void setAutoId(int autoId) {
//        User.autoId = autoId;
//    }
//
//}



import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 494741974462062790L; // Use the value from the error message

    private int id;  // New id field
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    public User(int id, String name, String email, String password, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Getters and setters (if needed)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(boolean status) {
    }
}
