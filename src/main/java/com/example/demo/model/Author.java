package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String age;

    //@JsonManagedReference
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> WrittenBooks;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @JsonIgnore
    public List<Book> getWrittenBooks() {
        return WrittenBooks;
    }

    @JsonIgnore
    public void setWrittenBooks(List<Book> writtenBooks) {
        WrittenBooks = writtenBooks;
    }
}


