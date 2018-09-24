package be.leerstad.address.service.repository.impl;

import be.leerstad.address.business.Person;
import be.leerstad.address.service.repository.IPersonRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyPersonRepository implements IPersonRepository {
    private Map<Integer, Person> myPersons;

    public DummyPersonRepository() {
        this.myPersons = new HashMap<>();
        //TODO remove dummydata
        fillDummyData();
    }

    @Override
    public boolean add(Person person) {
        if (person == null)
            return false;
        myPersons.put(person.getId(), person);

        return true;
    }

    @Override
    public boolean remove(Person person) {
        if (person == null)
            return false;
        myPersons.remove(person.getId());
        return true;
    }

    @Override
    public Person getByID(int i) {
        return myPersons.get(i);
    }

    @Override
    public List<Person> getAll() {
        List<Person> result = new ArrayList<>(myPersons.values());
        return result;
    }

    private void fillDummyData() {
        Person p = new Person(1, "Jeroen", "Herman");
        p.setBirthday(LocalDate.of(1981, 06, 6));
        add(p);
        p = new Person(2, "An", "Loquet");
        p.setBirthday(LocalDate.of(1983, 06, 6));
        add(p);
        p = new Person(3, "Elina", "De Borger");
        p.setBirthday(LocalDate.of(2013, 03, 31));
        add(p);
        p = new Person(4, "Xander", "Herman");
        p.setBirthday(LocalDate.of(2009, 03, 25));
        add(p);
    }
}
