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

    @PostMapping("/books")
    public String addBook(@RequestBody Book book){
        bookService.addBook(book);
        return "Added successfully";
    }

}
