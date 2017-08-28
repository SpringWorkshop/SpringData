package com.stardust.workshop.spring.data.workshop.part4.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Author")
@Table(name = "authors")
public class Author {
    private long id;
    private String name;
    private String gender;
    private List<Book> books = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder val = new StringBuilder();
        val.append("id=" + this.getId() + ", name=" + this.getName() + ", gender=" + this.getGender());
        getBooks().stream().forEach(book->val.append("\r\n  " + book.toString()));
        return val.toString();
    }
}
