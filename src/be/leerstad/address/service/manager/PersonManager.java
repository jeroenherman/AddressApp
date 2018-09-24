package be.leerstad.address.service.manager;

import be.leerstad.address.business.Person;
import be.leerstad.address.service.dto.PersonDTO;
import be.leerstad.address.service.mapper.PersonMapper;
import be.leerstad.address.service.repository.IPersonRepository;
import be.leerstad.address.service.repository.impl.DummyPersonRepository;
import be.leerstad.address.service.repository.impl.JDBCWaiterRepo;

import java.util.ArrayList;
import java.util.List;

public class PersonManager {
    private IPersonRepository myRepo = new DummyPersonRepository();
    private PersonMapper personMapper = new PersonMapper();

    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> dtos = new ArrayList<>();

        for (Person p : myRepo.getAll()) {
            dtos.add(personMapper.mapToDTO(p));
        }
        return dtos;
    }
}
