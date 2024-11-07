package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Book {

    private int id;
    private String name;
    private int pages;
    private String category;
    private float price;

    public Book() {
    }

    public Book(int id, String name, int pages, String category, float price) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.category = category;
        this.price = price;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
