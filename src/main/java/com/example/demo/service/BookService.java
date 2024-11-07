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

    List<Book> bookList = new ArrayList<>(Arrays.asList(
            new Book(1, "Harry Potter e la Pietra filosofale", 180, "Fantasy", 16.80f),
            new Book(2, "Harry Potter e la Camera dei segreti", 220, "Mistery", 17.20f),
            new Book(3, "Harry Potter e il Prigioniero di Azkaban", 310, "Adventure", 23.0f)));

    @Autowired
    BookRepository bookRepository;

    public List<Book> getBookList(){
        return bookList;
    }

    public Book getBookById(int bookId){
        return bookList.stream()
                .filter(b -> b.getId() == bookId)
                .findFirst()
                .get();
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void updateBook(Book book) {
        for(int x = 0; x < bookList.size(); x++){
            if (book.getId() == bookList.get(x).getId()){
                bookList.set(x, book);
            }
        }
    }

    public void deleteBook(int bookId) {
        for(int x = 0; x < bookList.size(); x++){
            if (bookId == bookList.get(x).getId()){
                bookList.remove(bookList.get(x));
            }
        }
    }
}
