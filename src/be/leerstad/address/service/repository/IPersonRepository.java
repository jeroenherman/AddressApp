package be.leerstad.address.service.repository;

import be.leerstad.address.business.Person;
import service.repository.IRepository;

import java.util.List;

public interface IPersonRepository extends IRepository<Person> {
    public List<Person> getAll();
}
