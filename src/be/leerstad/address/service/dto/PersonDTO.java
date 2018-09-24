package be.leerstad.address.service.dto;


public class PersonDTO {
    private String age;
    private String name;
    private String street;

    public PersonDTO() {
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
