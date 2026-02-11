package auca.ac.rw.question3_restaurant_api.controller;

import auca.ac.rw.question3_restaurant_api.model.MenuItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu") // All endpoints start with this URL
public class MenuController {

    // We use a List to store data since we don't have a database yet
    private List<MenuItem> menu = new ArrayList<>();

    public MenuController() {
        // CHALLENGE: Create at least 8 items
        menu.add(new MenuItem(1L, "Spring Rolls", "Vegetable rolls", 2500.0, "Appetizer", true));
        menu.add(new MenuItem(2L, "Chicken Wings", "Spicy buffalo wings", 4500.0, "Appetizer", true));
        menu.add(new MenuItem(3L, "Beef Burger", "Cheese and beef", 6000.0, "Main Course", true));
        menu.add(new MenuItem(4L, "Grilled Tilapia", "Whole fish with sides", 8000.0, "Main Course", false)); // Out of stock
        menu.add(new MenuItem(5L, "Chocolate Cake", "Dark chocolate", 3500.0, "Dessert", true));
        menu.add(new MenuItem(6L, "Fruit Salad", "Seasonal fruits", 2500.0, "Dessert", true));
        menu.add(new MenuItem(7L, "Mango Juice", "Fresh juice", 2000.0, "Beverage", true));
        menu.add(new MenuItem(8L, "Iced Coffee", "Cold brew", 2500.0, "Beverage", true));
    }

    // 1. Get all items
    @GetMapping
    public List<MenuItem> getAllMenu() {
        return menu;
    }

    // 2. Get specific item by ID
    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null; // Return nothing if not found
    }

    // 3. Get items by Category (e.g., "Dessert")
    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuByCategory(@PathVariable String category) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                results.add(item);
            }
        }
        return results;
    }

    // 4. Get only Available items (?available=true)
    @GetMapping("/available")
    public List<MenuItem> getAvailableItems(@RequestParam boolean available) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.isAvailable() == available) {
                results.add(item);
            }
        }
        return results;
    }

    // 5. Search items by name
    @GetMapping("/search")
    public List<MenuItem> searchMenu(@RequestParam String name) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    // 6. Add a new item
    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem newItem) {
        menu.add(newItem);
        return newItem;
    }

    // 7. Toggle Availability (Switch between true/false)
    @PutMapping("/{id}/availability")
    public String toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                // If it was true, make it false. If false, make it true.
                item.setAvailable(!item.isAvailable()); 
                return "Availability updated for: " + item.getName();
            }
        }
        return "Item not found";
    }

    // 8. Delete an item
    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        boolean removed = menu.removeIf(item -> item.getId().equals(id));
        if (removed) {
            return "Item deleted successfully";
        }
        return "Item not found";
    }
}