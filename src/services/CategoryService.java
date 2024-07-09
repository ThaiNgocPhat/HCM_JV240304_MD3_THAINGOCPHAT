package services;

import interfaces.CRUD;
import models.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryService implements CRUD<Category> {
    private Map<String, Category> categories = new HashMap<>();
    @Override
    public void create(Category category) {
        categories.put(category.getCategoryId(), category);
    }

    @Override
    public Category read(String id) {
        return categories.get(id);
    }

    @Override
    public void update(Category category) {
        categories.put(category.getCategoryId(), category);
    }

    @Override
    public void delete(String id) {
        categories.remove(id);
    }

    @Override
    public List<Category> getAll() {
        return categories.values().stream().collect(Collectors.toList());
    }
}
