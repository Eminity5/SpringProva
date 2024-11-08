package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Book> getBookByName(String name){
        return bookRepository.findBookContaining(name);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public void deleteBook(int bookId) {
       bookRepository.deleteById(bookId);
    }

    public void deleteBooks(List<Integer> bookIds){
        bookRepository.deleteAllById(bookIds);
    }
}
