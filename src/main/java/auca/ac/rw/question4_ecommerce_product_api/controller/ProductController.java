package auca.ac.rw.question4_ecommerce_product_api.controller;

import auca.ac.rw.question4_ecommerce_product_api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "iPhone 13", "Apple smartphone", 999.0, "Electronics", 10, "Apple"));
        products.add(new Product(2L, "Galaxy S21", "Samsung smartphone", 899.0, "Electronics", 15, "Samsung"));
        products.add(new Product(3L, "MacBook Pro", "M1 Laptop", 1999.0, "Computers", 5, "Apple"));
        products.add(new Product(4L, "Dell XPS", "Windows Laptop", 1200.0, "Computers", 8, "Dell"));
        products.add(new Product(5L, "Nike Air Max", "Running shoes", 120.0, "Fashion", 50, "Nike"));
        products.add(new Product(6L, "Adidas Boost", "Comfort sneakers", 140.0, "Fashion", 0, "Adidas"));
        products.add(new Product(7L, "Sony Headphones", "Noise canceling", 350.0, "Electronics", 20, "Sony"));
        products.add(new Product(8L, "Kindle", "E-reader", 130.0, "Electronics", 30, "Amazon"));
        products.add(new Product(9L, "Jeans", "Blue denim", 60.0, "Fashion", 100, "Levis"));
        products.add(new Product(10L, "Coffee Maker", "Drip coffee", 45.0, "Home", 12, "Philips"));
    }

    // GET ALL
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int limit) {

        if (page < 0 || limit <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "page must be >= 0 and limit must be > 0");
        }

        int start = page * limit;
        int end = Math.min(start + limit, products.size());
        if (start >= products.size()) return new ArrayList<>();
        return products.subList(start, end);
    }

    // GET BY ID (returns 404 if not found)
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        for (Product p : products) {
            if (productId.equals(p.getProductId())) return p;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + productId);
    }

    // POST (CREATE) - requires productId and avoids duplicates
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product p) {

        if (p.getProductId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productId is required");
        }

        for (Product existing : products) {
            if (p.getProductId().equals(existing.getProductId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists: " + p.getProductId());
            }
        }

        products.add(p);
        return p;
    }

    // PUT (UPDATE) - updates fields, keeps ID from URL
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product body) {

        Product existing = null;
        for (Product p : products) {
            if (productId.equals(p.getProductId())) {
                existing = p;
                break;
            }
        }

        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + productId);
        }

        existing.setName(body.getName());
        existing.setDescription(body.getDescription());
        existing.setPrice(body.getPrice());
        existing.setCategory(body.getCategory());
        existing.setStockQuantity(body.getStockQuantity());
        existing.setBrand(body.getBrand());

        return existing;
    }

    // DELETE (returns 404 if not found)
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        boolean removed = products.removeIf(p -> productId.equals(p.getProductId()));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + productId);
        }
    }
}
