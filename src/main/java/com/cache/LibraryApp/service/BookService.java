package com.cache.LibraryApp.service;

import com.cache.LibraryApp.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    String getBookById(Integer id);

    void createBook(Book book);

    String updateBook(Integer id, String bookName);

    void deleteBook(Integer id);
}
