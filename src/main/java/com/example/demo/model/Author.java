package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name="age")
    @Min(value = 0, message = "Age must be at least 0")
    private int age;

    //@JsonManagedReference
    @OneToMany(mappedBy = "author")
    @Column(name="WrittenBooks")
    @JsonIgnore
    private List<Book> WrittenBooks;

    // attestato in pdf



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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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


