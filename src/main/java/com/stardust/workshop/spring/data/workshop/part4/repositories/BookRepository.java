package com.stardust.workshop.spring.data.workshop.part4.repositories;

import com.stardust.workshop.spring.data.workshop.part4.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends DataRepository<Book>{

    @Override
    protected String getEntityClassName() {
        return Book.class.getName();
    }

    @Override
    protected String getEntityName() {
        return "Book";
    }
}
