package auca.ac.rw.question3_restaurant_api.controller;

import auca.ac.rw.question3_restaurant_api.model.MenuItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

   
    private List<MenuItem> menu = new ArrayList<>();

    public MenuController() {
        
        menu.add(new MenuItem(1L, "Spring Rolls", "Vegetable rolls", 2500.0, "Appetizer", true));
        menu.add(new MenuItem(2L, "Chicken Wings", "Spicy buffalo wings", 4500.0, "Appetizer", true));
        menu.add(new MenuItem(3L, "Beef Burger", "Cheese and beef", 6000.0, "Main Course", true));
        menu.add(new MenuItem(4L, "Grilled Tilapia", "Whole fish with sides", 8000.0, "Main Course", false)); // Out of stock
        menu.add(new MenuItem(5L, "Chocolate Cake", "Dark chocolate", 3500.0, "Dessert", true));
        menu.add(new MenuItem(6L, "Fruit Salad", "Seasonal fruits", 2500.0, "Dessert", true));
        menu.add(new MenuItem(7L, "Mango Juice", "Fresh juice", 2000.0, "Beverage", true));
        menu.add(new MenuItem(8L, "Iced Coffee", "Cold brew", 2500.0, "Beverage", true));
    }

    @GetMapping
    public List<MenuItem> getAllMenu() {
        return menu;
    }

    
    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null; 
    }

    
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

    
    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem newItem) {
        menu.add(newItem);
        return newItem;
    }

    
    @PutMapping("/{id}/availability")
    public String toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
               
                item.setAvailable(!item.isAvailable()); 
                return "Availability updated for: " + item.getName();
            }
        }
        return "Item not found";
    }

    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        boolean removed = menu.removeIf(item -> item.getId().equals(id));
        if (removed) {
            return "Item deleted successfully";
        }
        return "Item not found";
    }
}