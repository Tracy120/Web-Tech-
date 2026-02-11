package auca.ac.rw.question4_ecommerce_product_api.controller;

import auca.ac.rw.question4_ecommerce_product_api.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
     
        products.add(new Product(1L, "iPhone 13", "Apple smartphone", 999.0, "Electronics", 10, "Apple"));
        products.add(new Product(2L, "Galaxy S21", "Samsung smartphone", 899.0, "Electronics", 15, "Samsung"));
        products.add(new Product(3L, "MacBook Pro", "M1 Laptop", 1999.0, "Computers", 5, "Apple"));
        products.add(new Product(4L, "Dell XPS", "Windows Laptop", 1200.0, "Computers", 8, "Dell"));
        products.add(new Product(5L, "Nike Air Max", "Running shoes", 120.0, "Fashion", 50, "Nike"));
        products.add(new Product(6L, "Adidas Boost", "Comfort sneakers", 140.0, "Fashion", 0, "Adidas")); // Out of stock
        products.add(new Product(7L, "Sony Headphones", "Noise canceling", 350.0, "Electronics", 20, "Sony"));
        products.add(new Product(8L, "Kindle", "E-reader", 130.0, "Electronics", 30, "Amazon"));
        products.add(new Product(9L, "Jeans", "Blue denim", 60.0, "Fashion", 100, "Levis"));
        products.add(new Product(10L, "Coffee Maker", "Drip coffee", 45.0, "Home", 12, "Philips"));
    }

   
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int limit) {
        int start = page * limit;
        int end = Math.min((start + limit), products.size());
        if (start >= products.size()) return new ArrayList<>();
        return products.subList(start, end);
    }


    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) return p;
        }
        return null;
    }

    
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        List<Product> results = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(key) || p.getDescription().toLowerCase().contains(key)) {
                results.add(p);
            }
        }
        return results;
    }

   
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) results.add(p);
        }
        return results;
    }

    
    @GetMapping("/brand/{brand}")
    public List<Product> getByBrand(@PathVariable String brand) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getBrand().equalsIgnoreCase(brand)) results.add(p);
        }
        return results;
    }

   
    @GetMapping("/price-range")
    public List<Product> getByPrice(@RequestParam double min, @RequestParam double max) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) results.add(p);
        }
        return results;
    }

    @GetMapping("/in-stock")
    public List<Product> getInStock() {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.getStockQuantity() > 0) results.add(p);
        }
        return results;
    }

    
    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        products.add(p);
        return p;
    }

   
    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable Long productId, @RequestBody Product p) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.set(i, p);
                return "Updated successfully";
            }
        }
        return "Product not found";
    }

    @PatchMapping("/{productId}/stock")
    public String updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return "Stock updated to " + quantity;
            }
        }
        return "Product not found";
    }

    
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
        if (removed) return "Deleted successfully";
        return "Product not found";
    }
}