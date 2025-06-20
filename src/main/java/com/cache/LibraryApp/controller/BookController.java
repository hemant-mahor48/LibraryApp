package com.cache.LibraryApp.controller;

import com.cache.LibraryApp.model.Book;
import com.cache.LibraryApp.service.BookService;
import com.cache.LibraryApp.serviceImpl.CacheInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CacheInspectionService cacheInspectionService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<String> getBookById(@PathVariable Integer id){
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<String> createBook(@RequestBody Book book){
        bookService.createBook(book);
        return new ResponseEntity<>("Book Updated successfully in Library", HttpStatus.CREATED);
    }

    @GetMapping("/cacheData")
    public void getCacheData(){
        cacheInspectionService.printCacheContents("Book");
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Integer id, @RequestParam String bookName){
        String name = bookService.updateBook(id, bookName);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book Evicted from Library", HttpStatus.OK);
    }
}
