package pl.olafgoj.bookstore.books.adapters.booksdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.olafgoj.bookstore.books.domain.model.Book;
import pl.olafgoj.bookstore.books.domain.ports.BookRepository;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BooksPostgresRepository extends CrudRepository<Book, Long>, BookRepository {
}
