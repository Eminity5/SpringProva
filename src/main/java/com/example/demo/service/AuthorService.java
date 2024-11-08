package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAuthorList() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int authorId) {
        return authorRepository.findById(authorId).orElse(new Author());
    }

    public List<Author> getAuthorByName(String name) {
        return authorRepository.findAuthorContaining(name);
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public void addAuthors(List<Author> authors) {
        authorRepository.saveAll(authors);
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
        StringBuilder elementsNotFounded = new StringBuilder();

        for(Author a : authors){
            if(authorRepository.existsById(a.getId())){
                authorRepository.save(a);
            }else{
                elementsNotFounded.append(a.getId() + " ");
            }
        }

        if (elementsNotFounded.isEmpty()){
            return "Updated all elements successfully";
        }else{
            return "Elements " + elementsNotFounded.toString() + "were not found";
        }
    }

    public void deleteAuthor(int authorId) {
        authorRepository.deleteById(authorId);
    }

    public void deleteAuthors(List<Integer> authorIds) {
        authorRepository.deleteAllById(authorIds);
    }
}
