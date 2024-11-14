package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    @NotBlank(message = "You must provide the name")
    private String name;

    @Column
    private Date dateOfBirth;

    private transient int age = determineAge();
   // public int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

    //@JsonManagedReference
    @OneToMany(mappedBy = "author")
    @Column(name="written_books")
    @JsonIgnore
    private List<Book> WrittenBooks;

    @Lob
    @Column
    private byte[] certificate;

    @Column(name="tax_code")
    private String taxCode;



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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public int determineAge(){
        if(dateOfBirth != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOfBirth);

            return (Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR));
        } else {
            return 0;
        }
    }

    public void certificateToFile() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("C:/Users/Winet/IdeaProjects/demo/pdffiles/"+ name + "Certificate.pdf")) {
            fos.write(certificate);
        }
    }
}


