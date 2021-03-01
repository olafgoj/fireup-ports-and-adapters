package pl.olafgoj.bookstore.books.domain.ports;

import pl.olafgoj.bookstore.books.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    boolean existsById(long id);

    void deleteById(long id);
}
