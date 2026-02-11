package auca.ac.rw.question3_restaurant_api.model;

public class MenuItem {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category; // e.g., "Appetizer", "Main Course"
    private boolean available;

    // Default Constructor (Required for Spring to handle data)
    public MenuItem() {
    }

    // Full Constructor (Used to create our sample data easily)
    public MenuItem(Long id, String name, String description, Double price, String category, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    // --- Getters and Setters (Allow the app to read and update these values) ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}