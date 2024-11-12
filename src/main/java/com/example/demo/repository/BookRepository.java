package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE UPPER(b.name) LIKE %:name%")
    List<Book> findBooksByName(@Param("name") String name);

    @Query("SELECT b FROM Book b WHERE UPPER(b.name) = :name")
    Book findBookByName(@Param("name") String name);

    @Query("SELECT b FROM Book b WHERE UPPER(b.type) LIKE %:type%")
    List<Book> findBookByType(@Param("type") String type);

    @Query("SELECT b FROM Book b WHERE b.price < :price")
    List<Book> findLowerPricedBook(@Param("price") float price);
}
