package mk.ukim.finki.emt.lab_bookshop.service;

import mk.ukim.finki.emt.lab_bookshop.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long countryId);

    void deleteById(Long id);
}
