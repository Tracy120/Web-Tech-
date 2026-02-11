package auca.ac.rw.question5_task_management_api.controller;

import auca.ac.rw.question5_task_management_api.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Complete Java Assignment", "Finish Spring Boot questions", false, "HIGH", "2026-02-15"));
        tasks.add(new Task(2L, "Grocery Shopping", "Buy milk and bread", false, "LOW", "2026-02-12"));
        tasks.add(new Task(3L, "Study for Exam", "Review Web Tech notes", true, "MEDIUM", "2026-02-10"));
    }

   
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    
    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) return task;
        }
        return null;
    }

    
    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                results.add(task);
            }
        }
        return results;
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable String priority) {
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                results.add(task);
            }
        }
        return results;
    }

 
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        tasks.add(task);
        return task;
    }

 
    @PutMapping("/{taskId}")
    public String updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                tasks.set(i, updatedTask);
                return "Task updated successfully";
            }
        }
        return "Task not found";
    }

   
    @PatchMapping("/{taskId}/complete")
    public String markTaskComplete(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return "Task marked as completed";
            }
        }
        return "Task not found";
    }

    
    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        boolean removed = tasks.removeIf(t -> t.getTaskId().equals(taskId));
        if (removed) return "Task deleted successfully";
        return "Task not found";
    }
}