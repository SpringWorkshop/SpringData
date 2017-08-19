package com.stardust.workshop.spring.data.workshop.part2.repositories;


import java.util.List;

public interface DataRepository<T> {

    void createAll();

    void createOne();

    T queryOne(int id);

    List<T> queryAll();

    void removeAll();
}
