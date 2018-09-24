package be.leerstad.address;


import be.leerstad.address.service.manager.PersonManager;


public class Runner {
    public Runner() {
    }

    public static void main(String[] args) {
        PersonManager mgt = new PersonManager();

        System.out.println(mgt.getAllPersons());
    }
}
