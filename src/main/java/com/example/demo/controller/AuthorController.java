package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public Object getAuthorById(@PathVariable int authorId) throws IOException {
        return authorService.getAuthorById(authorId);
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
    public String addAuthor(@Valid @RequestPart("author") Author author, @RequestPart(value= "certificateDocument", required = false) MultipartFile certificateDocument, BindingResult result) throws IOException {
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return errors.toString();
        }
        return authorService.addAuthor(author, certificateDocument, result);
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
    public String updateAuthor(@Valid @RequestPart("author") Author author, @RequestPart(value= "certificateDocument", required = false) MultipartFile certificateDocument, BindingResult result) throws IOException{
        errors.clear();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return errors.toString();
        }
        return authorService.updateAuthor(author, certificateDocument, result);
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
