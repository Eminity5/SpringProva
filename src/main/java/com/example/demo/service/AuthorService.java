package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private AuthorValidator authorValidator;

    Set<String> errors = new HashSet<>();


    public List<Object> getAuthorList() {
        List<Object> l = new ArrayList<>();
        l.addAll(authorRepository.findAll());

        if(l.isEmpty()){
            l.add("No Elements found");
            return l;
        } else{
            return l;
        }
    }

    public Object getAuthorById(int id) throws IOException {
        if(authorRepository.existsById(id)){
            Author author = authorRepository.findById(id).get();

            author.setAge(author.determineAge());
            if(author.getCertificate() != null){
                author.certificateToFile();
            }

            return author;
        } else {
            return "Element doesn't exist by id of "+ id;
        }
    }

    public List<Object> getAuthorByName(String name) {
        List<Object> l = new ArrayList<>(authorRepository.findAuthorsByName(name.toUpperCase()));

        if(l.isEmpty()){
            l.add("No Elements found");
            return l;
        } else{
            return l;
        }
    }

    /*public List<Object> getAuthorByAge(int age) {
        List<Object> l = new ArrayList<>();
        l.addAll(authorRepository.findAuthorByAge(age));

        if(l.isEmpty()){
            l.add("No Elements found");
            return l;
        } else{
            return l;
        }
    }*/

    public String addAuthor(Author author, MultipartFile certificateDocument, BindingResult result) throws IOException {
        authorValidator.validate(author, result);

        if(result.hasErrors()){
            result.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return errors.toString();
        } else {
            if(certificateDocument != null){
                try{
                    // author.setPdfDocument(Files.readAllBytes(pdfFile.toPath()));
                    author.setCertificate(certificateDocument.getBytes());

                } catch (IOException e){
                    throw new RuntimeException("Error during PDF loading");
                }
            }
            authorRepository.save(author);

            return  "Added successfully";
        }
    }

    public String addAuthors(List<Author> authors) {
        for (Author author : authors){
            BindingResult authorResult = new BeanPropertyBindingResult(author, "author");
            authorValidator.validate(author, authorResult);

            if(authorResult.hasErrors()){
                authorResult.getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            } else{
                authorRepository.save(author);
            }
        }
        if(!errors.isEmpty()){
            return errors.toString() + (" Some elements present errors and were not added");
        } else {
            return  "Added successfully";
        }
    }

    public String updateAuthor(Author author, MultipartFile certificateDocument, BindingResult result) throws IOException{

        if(certificateDocument != null){
            try{
                // author.setPdfDocument(Files.readAllBytes(pdfFile.toPath()));
                author.setCertificate(certificateDocument.getBytes());
            } catch (IOException e){
                throw new RuntimeException("Error during PDF loading");
            }
        }

        if(authorRepository.existsById(author.getId())){
            Author authorFromDataBase = authorRepository.findById(author.getId()).get();

            keepOldChange(author, authorFromDataBase);

            authorRepository.save(authorFromDataBase);
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
        if(authorRepository.existsById(authorId)){
            authorRepository.deleteById(authorId);
            return "Deleted successfully";
        } else {
            return "Element doesn't exist by id of " + authorId;
        }
    }

    public String deleteAuthors(List<Integer> authorIds) {
        boolean atLeastOneElementWasNotFound = false;
        for(Integer id : authorIds){
            if (authorRepository.existsById(id)){
                authorRepository.deleteById(id);
            }else{
                atLeastOneElementWasNotFound = true;
            }
        }
        if(!atLeastOneElementWasNotFound) {
            return "Deleted successfully";
        } else {
            return "Some elements were not found";
        }
    }

    private void keepOldChange(Author author, Author authorFromDataBase){
        try{
            if(author.getName() != null){
                authorFromDataBase.setName(author.getName());
            }
        }catch (Exception e){}

        try{
            if(author.getDateOfBirth() != null){
                authorFromDataBase.setDateOfBirth(author.getDateOfBirth());
            }
        }catch (Exception e){}

        try{
            if(author.getCertificate() != null){
                authorFromDataBase.setCertificate(author.getCertificate());
            }
        }catch (Exception e){}

        try{
            if(author.getTaxCode() != null){
                authorFromDataBase.setTaxCode(author.getTaxCode());
            }
        }catch (Exception e){}
    }
}
