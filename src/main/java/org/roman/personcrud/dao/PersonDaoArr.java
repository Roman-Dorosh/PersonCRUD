package org.roman.personcrud.dao;

import org.roman.personcrud.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class PersonDaoArr implements CRUD {

    private static List<Person> people;
    private static int ID;

    static {
        people = new ArrayList<>();

        people.add(new Person(++ID, 18, "Roman", "roman@gmail.com"));
        people.add(new Person(++ID, 31, "Vlad", "vlad@gmail.com"));
        people.add(new Person(++ID, 30, "Anna", "anna@gmail.com"));
        people.add(new Person(++ID, 27, "Lesha", "lesha@gmail.com"));
    }

    @Override
    public List<Person> listObject() {
        return people;
    }

    @Override
    public Person getObject(int id) {
        Person person = null;

        for (Person value : people) {
            if (value.getId() == id) {
                person = value;
            }
        }

        return person;
    }

    @Override
    public void addObject(Person person) {
        person.setId(++ID);
        people.add(person);
    }

    @Override
    public void updateObject(int id, Person person) {
        for (Person value : people) {
            if (value.getId() == id) {
                value.setAge(person.getAge());
                value.setName(person.getName());
                value.setEmail(person.getEmail());
            }
        }
    }

    @Override
    public void deleteObject(int id) {
        people.removeIf(value -> value.getId() == id);
    }
}