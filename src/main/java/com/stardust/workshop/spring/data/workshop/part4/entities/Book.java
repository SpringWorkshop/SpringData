package com.stardust.workshop.spring.data.workshop.part4.entities;


import javax.persistence.*;

@Entity(name = "Book")
@Table(name = "books")
public class Book {
    private long id;
    private String name;
    private Author author;

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

    @ManyToOne
    @JoinColumn(columnDefinition = "author_id", referencedColumnName = "id")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
