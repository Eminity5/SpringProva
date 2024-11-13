package com.example.demo.repository;

import com.example.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a WHERE UPPER(a.name) LIKE %:name%")
    List<Author> findAuthorsByName(@Param("name") String name);

    @Query("SELECT a FROM Author a WHERE UPPER(a.name) = :name")
    Author findAuthorByName(@Param("name") String name);

    @Query("SELECT a FROM Author a WHERE a.age = :age")
    List<Author> findAuthorByAge(@Param("age") int age);
}
