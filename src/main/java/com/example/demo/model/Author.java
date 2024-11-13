package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
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
    @Column(name="written_books")
    @JsonIgnore
    private List<Book> WrittenBooks;

    @Lob
    @Column(name="certificate")
    private byte[] certificate;


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

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public void certificateToFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("C:/Users/Winet/IdeaProjects/demo/pdffiles/"+ name + "Certificate.pdf")) {
            fos.write(certificate);
        }
    }
}


