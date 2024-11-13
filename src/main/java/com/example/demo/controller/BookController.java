package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {

    Set<String> errors = new HashSet<>();

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Object> getBookList(){
        return bookService.getBookList();
    }

    @GetMapping("/books/{bookId}")
    public Object getBookById(@PathVariable int bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/books/searchByName")
    public List<Object> getBookByName(@RequestParam String name){
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
    public String addBook(@Valid @RequestBody Book book, BindingResult result){
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return errors.toString();
        } else {
            return bookService.addBook(book, result);
        }
    }

    @PostMapping("/books")
    public String addBooks(@Valid @RequestBody List<Book> books, BindingResult result){
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error ->
                    errors.add(error.getDefaultMessage()));
            return errors.toString();
        } else {
            return bookService.addBooks(books, result);
        }
    }

    @PutMapping("/book")
    public String updateBook(@Valid @RequestBody Book book, BindingResult result){
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error ->
                    errors.add(error.getDefaultMessage()));
            return errors.toString();
        } else {
            return bookService.updateBook(book);
        }
    }

    @PutMapping("/books")
    public String updateBooks(@Valid @RequestBody List<Book> books, BindingResult result){
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error ->
                    errors.add(error.getDefaultMessage()));
            return errors.toString();
        } else {
            return bookService.updateBooks(books);
        }
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
