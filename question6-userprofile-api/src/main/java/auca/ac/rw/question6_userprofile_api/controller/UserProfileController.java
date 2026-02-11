package auca.ac.rw.question6_userprofile_api.controller;

import auca.ac.rw.question6_userprofile_api.model.ApiResponse;
import auca.ac.rw.question6_userprofile_api.model.UserProfile;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {
        // Add sample data so we can test immediately
        users.add(new UserProfile(1L, "tracy_u", "tracy@test.com", "Tracy Uwase", 21, "Rwanda", "CS Student", true));
        users.add(new UserProfile(2L, "john_doe", "john@test.com", "John Doe", 25, "USA", "Developer", true));
        users.add(new UserProfile(3L, "jane_smith", "jane@test.com", "Jane Smith", 19, "Canada", "Designer", false));
    }

    // 1. GET ALL USERS (Wrapped in ApiResponse)
    @GetMapping
    public ApiResponse<List<UserProfile>> getAllUsers() {
        return new ApiResponse<>(true, "List of all users fetched successfully", users);
    }

    // 2. GET USER BY ID
    @GetMapping("/{userId}")
    public ApiResponse<UserProfile> getUserById(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                return new ApiResponse<>(true, "User found", user);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    // 3. SEARCH BY COUNTRY
    @GetMapping("/search/country/{country}")
    public ApiResponse<List<UserProfile>> searchByCountry(@PathVariable String country) {
        List<UserProfile> results = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                results.add(user);
            }
        }
        return new ApiResponse<>(true, "Search results for country: " + country, results);
    }

    // 4. SEARCH BY AGE RANGE
    @GetMapping("/search/age")
    public ApiResponse<List<UserProfile>> searchByAge(@RequestParam int min, @RequestParam int max) {
        List<UserProfile> results = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getAge() >= min && user.getAge() <= max) {
                results.add(user);
            }
        }
        return new ApiResponse<>(true, "Users found in age range " + min + "-" + max, results);
    }

    // 5. CREATE USER (POST)
    @PostMapping
    public ApiResponse<UserProfile> createUser(@RequestBody UserProfile user) {
        users.add(user);
        return new ApiResponse<>(true, "User profile created successfully", user);
    }

    // 6. ACTIVATE / DEACTIVATE USER (PATCH)
    @PatchMapping("/{userId}/status")
    public ApiResponse<String> changeStatus(@PathVariable Long userId, @RequestParam boolean active) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(active);
                String status = active ? "activated" : "deactivated";
                return new ApiResponse<>(true, "User has been " + status, null);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    // 7. DELETE USER
    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable Long userId) {
        boolean removed = users.removeIf(u -> u.getUserId().equals(userId));
        if (removed) {
            return new ApiResponse<>(true, "User deleted successfully", null);
        }
        return new ApiResponse<>(false, "User not found", null);
    }
}