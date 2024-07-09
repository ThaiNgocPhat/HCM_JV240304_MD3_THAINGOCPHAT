package services;

import interfaces.CRUD;
import models.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService implements CRUD<Product> {
    private Map<String, Product> products = new HashMap<>();
    @Override
    public void create(Product product) {
        products.put(String.valueOf(product.getProductId()), product);
    }

    @Override
    public Product read(String id) {
        return products.get(id);
    }

    @Override
    public void update(Product product) {
        products.put(String.valueOf(product.getProductId()), product);
    }

    @Override
    public void delete(String id) {
        products.remove(id);
    }

    @Override
    public List<Product> getAll() {
        return products.values().stream().collect(Collectors.toList());
    }
}
