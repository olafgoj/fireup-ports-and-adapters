package pl.olafgoj.bookstore.books.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String writer;

    public Book() {
    }

    public Book(final String title, final String writer) {
        this.title = title;
        this.writer = writer;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setWriter(final String writer) {
        this.writer = writer;
    }
}
