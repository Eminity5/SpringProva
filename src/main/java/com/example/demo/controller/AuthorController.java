package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAuthorList(){
        return authorService.getAuthorList();
    }

    @GetMapping("/authors/{authorId}")
    public Author getAuthorById(@PathVariable int id){
        return authorService.getAuthorById(id);
    }

    @GetMapping("/authors/searchByName")
    public List<Author> getAuthorByName(@RequestParam String name){
        return authorService.getAuthorByName(name);
    }

    @GetMapping("/authors/searchByAge")
    public List<Author> getAuthorByAge(@RequestParam int age){
        return authorService.getAuthorByAge(age);
    }

    @PostMapping("/author")
    public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @PostMapping("/authors")
    public String addAuthor(@RequestBody List<Author> authors){
        return authorService.addAuthors(authors);
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
