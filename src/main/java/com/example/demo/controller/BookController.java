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

    @PostMapping("/book")
    public String addBook(@RequestBody Book book){
        bookService.addBook(book);
        return "Added successfully";
    }

    @PostMapping("/books")
    public String addBooks(@RequestBody List<Book> books){
        bookService.addBooks(books);
        return "Added successfully";
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
        bookService.deleteBook(bookId);
        return "Deleted successfully";
    }

    @DeleteMapping("/books")
    public String deleteBooks(@RequestParam List<Integer> bookIds){
        bookService.deleteBooks(bookIds);
        return "Deleted successfully";
    }

}
