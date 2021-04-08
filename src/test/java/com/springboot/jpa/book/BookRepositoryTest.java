package com.springboot.jpa.book;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void test() {
        Book book = new Book();
        book.setTitle("spring");
        book.setContent("data");
        Book newBook = bookRepository.save(book);

        assertTrue(bookRepository.contains(newBook));

        Optional<Book> ring = bookRepository.findOne(QBook.book.title.contains("ring"));
        assertTrue(ring.isPresent());
        Optional<Book> jpa = bookRepository.findOne(QBook.book.title.contains("jpa"));
        assertTrue(jpa.isEmpty());
    }

}