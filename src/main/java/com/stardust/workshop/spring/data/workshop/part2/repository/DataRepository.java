package com.stardust.workshop.spring.data.workshop.part2.repository;


import java.util.List;

public interface DataRepository<T> {

    void createAll();

    List<T> queryAll();

    void removeAll();
}
