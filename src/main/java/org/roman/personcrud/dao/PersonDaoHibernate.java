package org.roman.personcrud.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.roman.personcrud.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
public class PersonDaoHibernate implements CRUD {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> listObject() {
        Session session = getCurrentSession();

        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getObject(int id) {
        Session session = getCurrentSession();

        return session.get(Person.class, id);
    }

    @Override
    @Transactional
    public void addObject(Person person) {
        Session session = getCurrentSession();

        session.persist(person);
    }

    @Override
    @Transactional
    public void updateObject(int id, Person person) {
        Session session = getCurrentSession();

        Person getPerson = session.get(Person.class, id);

        getPerson.setAge(person.getAge());
        getPerson.setName(person.getName());
        getPerson.setEmail(person.getEmail());
    }

    @Override
    @Transactional
    public void deleteObject(int id) {
        Session session = getCurrentSession();

        session.remove(session.get(Person.class, id));
    }
}