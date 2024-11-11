package com.example.demo.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.apache.bcel.classfile.Unknown;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="Title", unique = true, nullable = false)
    private String name;

    @Column(name="Pages")
    private int pages = 0;

    @Column(name="Type")
    private String type = "Unknown";

    @Column(name="Price")
    private float price = 0;

    // @JsonBackReference
    @JoinColumn(name = "author_id")
    @ManyToOne
    private Author author;



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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
