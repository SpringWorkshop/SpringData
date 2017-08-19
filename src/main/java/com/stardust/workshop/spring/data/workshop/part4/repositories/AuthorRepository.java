package com.stardust.workshop.spring.data.workshop.part4.repositories;


import com.stardust.workshop.spring.data.workshop.part4.entities.Author;
import org.springframework.stereotype.Repository;


@Repository
public class AuthorRepository extends DataRepository<Author> {
    @Override
    protected String getEntityClassName() {
        return Author.class.getName();
    }

    @Override
    protected String getEntityName() {
        return "Author";
    }
}
