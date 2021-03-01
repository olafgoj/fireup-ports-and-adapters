package pl.olafgoj.bookstore.books.adapters.messagebroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.olafgoj.bookstore.books.adapters.messagebroker.avro.BookMessage;
import pl.olafgoj.bookstore.books.domain.model.Book;
import pl.olafgoj.bookstore.books.domain.ports.BookMessageSender;

@Component
@RepositoryEventHandler
class KafkaBookMessageSender implements BookMessageSender {

    private final Logger LOG = LoggerFactory.getLogger(KafkaBookMessageSender.class);
    @Value("${app.books.topic}")
    private String topic;

    private final KafkaTemplate<String, BookMessage> kafkaTemplate;

    KafkaBookMessageSender(final KafkaTemplate<String, BookMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @HandleAfterCreate
    public void sendMessageForCreated(final Book book) {
        BookMessage bookMessage = toBookMessage(book);
        LOG.info("Sending event for book #{} creation.", book.getId());
        kafkaTemplate.send(topic, bookMessage);
    }

    @Override
    @HandleAfterSave
    public void sendMessageForUpdated(final Book book) {
        BookMessage bookMessage = toBookMessage(book);
        LOG.info("Sending event for book #{} update.", book.getId());
        kafkaTemplate.send(topic, bookMessage);
    }

    @Override
    @HandleAfterDelete
    public void sendMessageForDeleted(final Book book) {
        BookMessage bookMessage = toBookMessage(book);
        LOG.info("Sending event for book #{} deletion.", book.getId());
        kafkaTemplate.send(topic, bookMessage);
    }

    private BookMessage toBookMessage(final Book book) {
        return BookMessage.newBuilder()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setWriter(book.getWriter())
                .build();
    }
}
