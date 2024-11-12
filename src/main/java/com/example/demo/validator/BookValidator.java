package com.example.demo.validator;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Autowired
    BookRepository bookRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        Book book1 = bookRepository.findBookByName(book.getName().toUpperCase());
        if(book1 != null){
            errors.rejectValue("name", "name.Exist", "Book already exist");
        }

    }
}

