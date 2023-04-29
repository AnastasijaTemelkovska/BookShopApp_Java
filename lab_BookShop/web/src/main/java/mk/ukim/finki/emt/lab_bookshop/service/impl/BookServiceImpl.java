package mk.ukim.finki.emt.lab_bookshop.service.impl;

import mk.ukim.finki.emt.lab_bookshop.model.Author;
import mk.ukim.finki.emt.lab_bookshop.model.Book;
import mk.ukim.finki.emt.lab_bookshop.model.dto.BookDto;
import mk.ukim.finki.emt.lab_bookshop.model.enumerations.Category;
import mk.ukim.finki.emt.lab_bookshop.model.exeptions.NoAvailableCopiesLeftException;
import mk.ukim.finki.emt.lab_bookshop.model.exeptions.AuthorNotFoundException;
import mk.ukim.finki.emt.lab_bookshop.model.exeptions.BookNotFoundException;
import mk.ukim.finki.emt.lab_bookshop.repository.AuthorRepository;
import mk.ukim.finki.emt.lab_bookshop.repository.BookRepository;
import mk.ukim.finki.emt.lab_bookshop.repository.CountryRepository;
import mk.ukim.finki.emt.lab_bookshop.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }


    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(name);
        book.setCategory(category);
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }


    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));


    }

    @Override
    public List<Category> getAllCategories() {
        return  List.of(Category.values());
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);

    }

    @Override
    public void markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
        } else {
            throw new NoAvailableCopiesLeftException();
        }

    }
}
