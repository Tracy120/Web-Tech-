package auca.ac.rw.question1_library_api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.question1_library_api.model.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {

    
    private static List<Book> books = new ArrayList<>();

    
    static {
        books.add(new Book(1L, "Clean Code", "Robert Martin", "978-0132350884", 2008));
        books.add(new Book(2L, "The Pragmatic Programmer", "David Thomas", "978-0201616224", 1999));
        books.add(new Book(3L, "Refactoring", "Martin Fowler", "978-0201485677", 1999));
    }

    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }

    
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchResults.add(book);
            }
        }
        if (searchResults.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(searchResults);
        }
        return ResponseEntity.ok(searchResults);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

   
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        
        Long newId = books.size() > 0 ? books.get(books.size() - 1).getId() + 1 : 1L;
        book.setId(newId);
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                books.remove(book);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
