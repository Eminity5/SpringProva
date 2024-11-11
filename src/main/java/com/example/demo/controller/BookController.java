package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Book> getBookList(){
        return bookService.getBookList();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable int bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/books/searchByName")
    public List<Book> getBookByName(@RequestParam String name){
        return bookService.getBookByName(name);
    }

    @GetMapping("/books/searchByType")
    public List<Book> getBookByType(@RequestParam String type){
        return bookService.getBookByType(type);
    }

    @GetMapping("/books/searchByPrice")
    public List<Book> getBookByPrice(@RequestParam float price){
        return bookService.getBookByPrice(price);
    }

    @PostMapping("/book")
    public String addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PostMapping("/books")
    public String addBooks(@RequestBody List<Book> books){
        return bookService.addBooks(books);
    }

    @PutMapping("/book")
    public String updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @PutMapping("/books")
    public String updateBooks(@RequestBody List<Book> books){
        return bookService.updateBooks(books);
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable int bookId){
        return bookService.deleteBook(bookId);
    }

    @DeleteMapping("/books")
    public String deleteBooks(@RequestParam List<Integer> bookIds){
        return bookService.deleteBooks(bookIds);
    }

}
