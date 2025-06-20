package com.cache.LibraryApp.serviceImpl;

import com.cache.LibraryApp.repository.BookRepository;
import com.cache.LibraryApp.model.Book;
import com.cache.LibraryApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value = "Book", key = "#id")
    public String getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(Book::getName).orElseThrow(()-> new RuntimeException("Book Not Found"));
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @CachePut(value = "Book", key = "#id")
    public String updateBook(Integer id, String bookName) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
        existingBook.setName(bookName);
        bookRepository.save(existingBook);
        return bookName;
    }

    @Override
    @CacheEvict(value = "Book", key = "#id")
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
