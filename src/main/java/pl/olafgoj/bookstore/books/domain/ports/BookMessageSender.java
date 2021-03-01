package pl.olafgoj.bookstore.books.domain.ports;

import pl.olafgoj.bookstore.books.domain.model.Book;

public interface BookMessageSender {

    void sendMessageForCreated(Book book);

    void sendMessageForUpdated(Book book);

    void sendMessageForDeleted(Book book);

}
