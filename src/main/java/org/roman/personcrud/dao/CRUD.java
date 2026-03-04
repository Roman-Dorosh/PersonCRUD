package org.roman.personcrud.dao;

import org.roman.personcrud.model.Person;

import java.util.List;

public interface CRUD {

    List<Person> listObject();

    Person getObject(int id);

    void addObject(Person person);

    void updateObject(int id, Person person);

    void deleteObject(int id);
}