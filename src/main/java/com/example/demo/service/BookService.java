package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.validator.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BeanPropertyBindingResult;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private BookValidator bookValidator;

    Set<String> errors = new HashSet<>();


    public List<Object> getBookList(){
        List<Object> l = new ArrayList<>();
        l.addAll(bookRepository.findAll());

        if(l.isEmpty()){
            l.add("No Elements found");
            return l;
        } else{
            return l;
        }
    }

    public Object getBookById(int bookId){
        if(bookRepository.existsById(bookId)){
            return bookRepository.findById(bookId).get();
        } else {
            return "Element doesn't exist by id of "+ bookId;
        }
    }

    public List<Object> getBookByName(String name){
        List<Object> l = new ArrayList<>();
        l.addAll(bookRepository.findBooksByName(name.toUpperCase()));

        if(l.isEmpty()){
            l.add("No Elements found");
            return l;
        } else{
            return l;
        }
    }

    public List<Book> getBookByType(String type){
        return bookRepository.findBookByType(type);
    }

    public List<Book> getBookByPrice(float price){
        return bookRepository.findLowerPricedBook(price);
    }

    public String addBook(Book book, BindingResult result) {
        bookValidator.validate(book, result);

        if (result.hasErrors()){
            result.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return errors.toString();
        } else {
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"); //specifico il formato
            String formattedDate = LocalDateTime.now().format(myFormatObj); //prendi il giorno e lo formatti
            LocalDateTime localDateTimeFormatted = LocalDateTime.parse(formattedDate, myFormatObj); //trasformi da string a localdatetime
            book.setDateTime(localDateTimeFormatted);

            bookRepository.save(book);
            return  "Added successfully";
        }
    }

    public String addBooks(List<Book> books, BindingResult result) {
        for (Book book : books){
            BindingResult bookResult = new BeanPropertyBindingResult(book, "book");
            bookValidator.validate(book, bookResult);

            if(bookResult.hasErrors()){
                bookResult.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            } else{
                bookRepository.save(book);
            }
        }
        if(!errors.isEmpty()){
            return errors.toString() + (" Some elements present errors and were not added");
        } else {
            return  "Added successfully";
        }
    }

    public String updateBook(Book book) {
        if(bookRepository.existsById(book.getId())){
            bookRepository.save(book);
            return "Updated successfully";
        } else {
            return "Element does not Exist";
        }
    }

    public String updateBooks(List<Book> books) {
        StringBuilder elementsNotFounded = new StringBuilder();

        for(Book b : books){
            if(bookRepository.existsById(b.getId())){
                bookRepository.save(b);
            }else{
                elementsNotFounded.append(b.getId() + " ");
            }
        }

        if (elementsNotFounded.isEmpty()){
            return "Updated all elements successfully";
        }else{
            return "Elements " + elementsNotFounded.toString() + "were not found";
        }
    }

    public String deleteBook(int bookId) {
        if(bookRepository.existsById(bookId)){
            bookRepository.deleteById(bookId);
            return "Deleted successfully";
        } else{
            return "Element doesn't exist by id of " + bookId;
        }
    }

    public String deleteBooks(List<Integer> bookIds){
        boolean atLeastOneElementWasNotFound = false;
        for(Integer id : bookIds){
            if (bookRepository.existsById(id)){
                bookRepository.deleteById(id);
            }else{
                atLeastOneElementWasNotFound = true;
            }
        }
        if(!atLeastOneElementWasNotFound) {
            return "Deleted successfully";
        } else {
            return "Some elements were not found";
        }

    }
}
