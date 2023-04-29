package mk.ukim.finki.emt.lab_bookshop.web.controller;

import mk.ukim.finki.emt.lab_bookshop.model.Author;
import mk.ukim.finki.emt.lab_bookshop.model.Book;
import mk.ukim.finki.emt.lab_bookshop.model.enumerations.Category;
import mk.ukim.finki.emt.lab_bookshop.service.AuthorService;
import mk.ukim.finki.emt.lab_bookshop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public String listBooks(String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.listAll();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "book");
        return "master-template";
    }
    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = Arrays.asList(Category.values());
            model.addAttribute("authors", authors);
            model.addAttribute("categories", categories);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }
        return "redirect:/books?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = Arrays.asList(Category.values());
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }
    @PostMapping("/add")
    public String saveBook(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Category category,
            @RequestParam Long authorId,
            @RequestParam Integer availableCopies) {
        if (id != null) {
            this.bookService.edit(id, name, category, authorId, availableCopies);
        } else {
            this.bookService.save(name, category, authorId, availableCopies);
        }
        return "redirect:/books";
    }
    @PostMapping ("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping("/taken/{id}")
    public String taken(@PathVariable Long id) {
        this.bookService.markAsTaken(id);
        return "redirect:/books";
    }

}
