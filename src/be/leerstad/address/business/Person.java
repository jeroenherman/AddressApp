package be.leerstad.address.business;

import java.time.*;

public class Person {

    private String firstName;
    private String lastName;
    private Address address;

    private LocalDate birthday;
    private Integer id;

    public Person(Integer id, String firstName, String lastName) {
        this.address = new Address();
        this.firstName = firstName;
        this.lastName = lastName;
        this.setStreet("some street");
        this.setPostalCode(new Integer(1234));
        this.setCity(new String("some city"));
        this.birthday = LocalDate.of(1999, 2, 21);
        this.id = id;


    }

    public Person() {
        this(null, null, null);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return address.getStreet();
    }

    public Integer getPostalCode() {
        return address.getZipCode();
    }

    public String getCity() {
        return address.getCity();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreet(String street) {
        this.address.setStreet(street);
    }

    public void setPostalCode(Integer postalCode) {
        this.address.setZipCode(postalCode);
    }

    public void setCity(String city) {
        this.address.setCity(city);
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}