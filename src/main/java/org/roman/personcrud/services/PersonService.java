package org.roman.personcrud.services;

import org.roman.personcrud.dao.CRUD;
import org.roman.personcrud.model.Person;
import org.roman.personcrud.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService implements CRUD {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> listObject() {
        return personRepository.findAll();
    }

    @Override
    public Person getObject(int id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    @Override
    @Transactional
    public void addObject(Person person) {
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void updateObject(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void deleteObject(int id) {
        personRepository.deleteById(id);
    }
}