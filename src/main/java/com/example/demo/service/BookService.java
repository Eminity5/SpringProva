package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getBookList(){
        return bookRepository.findAll();
    }

    public Book getBookById(int bookId){
        return bookRepository.findById(bookId).orElse(new Book());
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void addBook(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(int bookId) {
       bookRepository.deleteById(bookId);
    }
}
