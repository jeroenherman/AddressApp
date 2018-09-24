package be.leerstad.address.service.mapper;

import be.leerstad.address.business.Person;
import be.leerstad.address.service.dto.PersonDTO;
import service.AbstractMapper;


import java.time.LocalDate;
import java.time.Period;

public class PersonMapper extends AbstractMapper<Person, PersonDTO> {
    @Override
    public PersonDTO mapToDTO(Person person) {
        if (person == null)
            return null;
        PersonDTO personDTO = new PersonDTO() {
            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this.getName()).append(" ").append(this.getStreet()).append(" ").append(this.getAge());
                return sb.toString();
            }
        };
        personDTO.setName(person.getFirstName() + " " + person.getLastName());
        int age = Period.between(person.getBirthday(), LocalDate.now()).getYears();
        personDTO.setAge(String.valueOf(age));
        personDTO.setStreet(person.getStreet());

        return personDTO;
    }

    @Override
    public Person mapToObj(PersonDTO personDTO) {
        if (personDTO == null)
            return null;
        Person person = new Person();
        person.setStreet(personDTO.getStreet());
        String[] fullName = personDTO.getName().trim().split(" ");
        person.setFirstName(fullName[0]);
        person.setLastName(fullName[1]);
        // Birthday can not be calculated from age alone.
        return person;

    }
}
