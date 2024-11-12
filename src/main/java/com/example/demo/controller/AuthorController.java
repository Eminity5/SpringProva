package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class AuthorController {

    Set<String> errors = new HashSet<>();

    @Autowired
    AuthorService authorService;

    @GetMapping("/authors")
    public List<Object> getAuthorList(){
        return authorService.getAuthorList();
    }

    @GetMapping("/authors/{authorId}")
    public Object getAuthorById(@PathVariable int id){
        return authorService.getAuthorById(id);
    }

    @GetMapping("/authors/searchByName")
    public List<Object> getAuthorByName(@RequestParam String name){
        return authorService.getAuthorByName(name);
    }

    @GetMapping("/authors/searchByAge")
    public List<Object> getAuthorByAge(@RequestParam int age){
        return authorService.getAuthorByAge(age);
    }

    @PostMapping("/author")
    public String addAuthor(@Valid @RequestBody Author author, BindingResult result){
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error ->
                    errors.add(error.getDefaultMessage()));
            return errors.toString();
        }
        return authorService.addAuthor(author, result);
    }

    @PostMapping("/authors")
    public String addAuthor(@Valid @RequestBody List<Author> authors, BindingResult result){
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error ->
                    errors.add(error.getDefaultMessage()));
            return errors.toString();
        } else {
            return authorService.addAuthors(authors);
        }
    }

    @PutMapping("/author")
    public String updateAuthor(@RequestBody Author author){
        return authorService.updateAuthor(author);
    }

    @PutMapping("/authors")
    public String updateAuthors(@RequestBody List<Author> authors){
        return authorService.updateAuthors(authors);
    }

    @DeleteMapping("/authors/{authorId}")
    public String deleteAuthor(@PathVariable int authorId){
        return authorService.deleteAuthor(authorId);
    }

    @DeleteMapping("/authors")
    public String deleteAuthors(@RequestParam List<Integer> authorIds){
        return authorService.deleteAuthors(authorIds);
    }

}
