package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAuthorList() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(new Author());
    }

    public List<Author> getAuthorByName(String name) {
        return authorRepository.findAuthorsByName(name.toUpperCase());
    }

    public List<Author> getAuthorByAge(int age) {
        return authorRepository.findAuthorByAge(age);
    }

    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Added successfully";
    }

    public String addAuthors(List<Author> authors) {
        authorRepository.saveAll(authors);
        return "Added successfully";
    }

    public String updateAuthor(Author author) {
        if(authorRepository.existsById(author.getId())){
            authorRepository.save(author);
            return "Updated successfully";
        }else {
            return "Element does not Exist";
        }
    }

    public String updateAuthors(List<Author> authors) {
        List<Integer> elementsNotFounded = new ArrayList<>();

        for(Author a : authors){
            if(authorRepository.existsById(a.getId())){
                authorRepository.save(a);
            }else{
                elementsNotFounded.add(a.getId());
            }
        }

        return elementsNotFounded.isEmpty() ?  "Elements " + elementsNotFounded.toString() + "were not found" : "Updated successfully";
    }

    public String deleteAuthor(int authorId) {
        authorRepository.deleteById(authorId);
        return "Deleted successfully";
    }

    public String deleteAuthors(List<Integer> authorIds) {
        authorRepository.deleteAllById(authorIds);
        return "Deleted successfully";
    }
}
