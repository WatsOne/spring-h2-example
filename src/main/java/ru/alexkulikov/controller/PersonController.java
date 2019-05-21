package ru.alexkulikov.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.alexkulikov.entity.Person;

@RestController
@RequestMapping("person")
public class PersonController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping ("/add")
    @Transactional
    public String add() {
	Person person = new Person("Petya");
	em.persist(person);
	return "Done";
    }

    @GetMapping("/all")
    public List<Person> hello() {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Person> query = cb.createQuery(Person.class);
	Root<Person> root = query.from(Person.class);
	query.select(root);

	return em.createQuery(query).getResultList();
    }
}
