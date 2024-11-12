package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="name", unique = true)
    @NotBlank(message = "You must provide a name")
    private String name;

    @Column(name="pages")
    @Min(value = 1, message = "Pages must be at least 1")
    private int pages = 1;

    @Column(name="type")
    private String type = "Unknown";

    @Column(name="price")
    @Min(value = 0, message = "Price must be at least 0")
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

    public void setPages(/*@Min(0) */int pages) {
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

    public void setPrice(/*@Min(0) */float price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
