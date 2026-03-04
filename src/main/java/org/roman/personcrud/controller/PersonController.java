package org.roman.personcrud.controller;

import org.roman.personcrud.dao.CRUD;
import org.roman.personcrud.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final CRUD personDAO;

    @Autowired
    public PersonController(CRUD personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String cnListPerson(Model model) {
        model.addAttribute("mdListPerson", personDAO.listObject());
        return "pgListPerson";
    }

    @GetMapping("/{id}")
    public String cnGetPerson(@PathVariable("id") int id,
                              Model model) {
        model.addAttribute("mdGetPerson", personDAO.getObject(id));
        return "pgGetPerson";
    }

    @GetMapping("/add")
    public String cnAddPerson(Model model) {
        model.addAttribute("mdAddPerson", new Person());
        return "pgAddPerson";
    }

/*
    @PostMapping("/add")
    public String cnAdd(@RequestParam("age") int age,
                        @RequestParam("name") String name,
                        @RequestParam("email") String email) {

        Person person = new Person();

        person.setAge(age);
        person.setName(name);
        person.setEmail(email);

        personDAO.addObject(person);

        return "redirect:/";
    }
*/

    @PostMapping("/add")
    public String cnAdd(@ModelAttribute("person") Person person) {
        personDAO.addObject(person);
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String cnUpdatePerson(@PathVariable("id") int id,
                                 Model model) {
        model.addAttribute("mdUpdatePerson", personDAO.getObject(id));
        return "pgUpdatePerson";
    }

/*
    @PatchMapping("/{id}/update")
    public String cnUpdate(@PathVariable("id") int id,
                           @RequestParam("age") int age,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email) {

        Person person = new Person();

        person.setAge(age);
        person.setName(name);
        person.setEmail(email);

        personDAO.updateObject(id, person);

        return "redirect:/{id}";
    }
*/

    @PatchMapping("/{id}/update")
    public String cnUpdate(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person) {
        personDAO.updateObject(id, person);
        return "redirect:/{id}";
    }

    @DeleteMapping("/{id}/delete")
    public String cnDelete(@PathVariable("id") int id) {
        personDAO.deleteObject(id);
        return "redirect:/";
    }
}