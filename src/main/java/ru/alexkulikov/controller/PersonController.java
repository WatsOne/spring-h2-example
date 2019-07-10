package ru.alexkulikov.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexkulikov.entity.Car;
import ru.alexkulikov.entity.Person;

@RestController
@RequestMapping("person")
public class PersonController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/addPerson")
    @Transactional
    public String add(@QueryParam("name") String name, @QueryParam("age") Integer age) {
        Person person = new Person(name);
        person.setAge(age);
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

    @GetMapping("/uniqueCars")
    public List<String> uniqueCars() {
        return em.createQuery("select distinct c.model from Car c", String.class).getResultList();
    }

    @GetMapping("/ageFiltered")
    public List<Person> ageFiltered() {
        return em.createQuery("select p from Person p where p.age > 18", Person.class).getResultList();
    }

    @GetMapping("/addCar")
    @Transactional
    public String addCar(@QueryParam("person") Long person, @QueryParam("model") String model) {
        Person personObj = em.find(Person.class, person);
        Car car = new Car(model);
        car.setPerson(personObj);
        em.persist(car);
        return "Done";
    }
}
