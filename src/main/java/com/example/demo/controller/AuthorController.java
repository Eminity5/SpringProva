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
    public Author getAuthorById(@PathVariable int authorId){
        return authorService.getAuthorById(authorId);
    }

    @GetMapping("/authors/searchByName")
    public List<Author> getAuthorByName(@RequestParam String name){
        return authorService.getAuthorByName(name);
    }

    @PostMapping("/author")
    public String addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return "Added successfully";
    }

    @PostMapping("/authors")
    public String addAuthor(@RequestBody List<Author> authors){
        authorService.addAuthors(authors);
        return "Added successfully";
    }

    @PutMapping("/author")
    public String updateAuthor(@RequestBody Author author){
        authorService.updateAuthor(author);
        return "Updated successfully";
    }

    @PutMapping("/authors")
    public String updateAuthors(@RequestBody List<Author> authors){
        authorService.updateAuthors(authors);
        return "Updated successfully";
    }

    @DeleteMapping("/authors/{authorId}")
    public String deleteAuthor(@PathVariable int authorId){
        authorService.deleteAuthor(authorId);
        return "Deleted successfully";
    }

    @DeleteMapping("/authors")
    public String deleteAuthors(@RequestParam List<Integer> authorIds){
        authorService.deleteAuthors(authorIds);
        return "Deleted successfully";
    }

}
