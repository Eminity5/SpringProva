package com.example.demo.service;

import com.example.demo.model.Author;
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

    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    public void updateAuthors(List<Author> authors) {
        authorRepository.saveAll(authors);
    }

    public void deleteAuthor(int authorId) {
        authorRepository.deleteById(authorId);
    }

    public void deleteAuthors(List<Integer> authorIds) {
        authorRepository.deleteAllById(authorIds);
    }
}
