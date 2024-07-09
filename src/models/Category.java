package models;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private int categoryId;
    private String categoryName;
    private int categoryQuantity;
    private static int autoId = 1;
    public Category() {
    }

    public Category(String categoryName, int categoryQuantity) {
        this.categoryId = autoId++;
        this.categoryName = categoryName;
        this.categoryQuantity = categoryQuantity;
    }

    public String getCategoryId() {
        return String.valueOf(categoryId);
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryQuantity() {
        return categoryQuantity;
    }

    public void setCategoryQuantity(int categoryQuantity) {
        this.categoryQuantity = categoryQuantity;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Category.autoId = autoId;
    }
}
