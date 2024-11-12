package com.example.demo.validator;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AuthorValidator implements Validator {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Author.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Author author = (Author) target;

        Author author1 = authorRepository.findAuthorByName(author.getName().toUpperCase());
        if(author1 != null){
            errors.rejectValue("name", "name.Exist", "Author already exist");
        }

    }
}
