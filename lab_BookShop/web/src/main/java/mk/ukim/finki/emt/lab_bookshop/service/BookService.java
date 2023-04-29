package mk.ukim.finki.emt.lab_bookshop.service;

import mk.ukim.finki.emt.lab_bookshop.model.Book;
import mk.ukim.finki.emt.lab_bookshop.model.dto.BookDto;
import mk.ukim.finki.emt.lab_bookshop.model.enumerations.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Page<Book> findAllWithPagination(Pageable pageable);
    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    List<Category> getAllCategories();

    void deleteById(Long id);

    void markAsTaken(Long id);


}
