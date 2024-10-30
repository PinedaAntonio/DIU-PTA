package com.example.agenda.util;

import com.example.agenda.Modelo.PersonVO;
import com.example.agenda.Person;

import java.util.ArrayList;

public class PersonUtil {

    public ArrayList<Person> getPersons(ArrayList<PersonVO> listaVO) {
        ArrayList<Person> listaPerson = new ArrayList<>();
        for (PersonVO personVO : listaVO) {
            Person person = new Person();

            // Asignación de campos desde PersonVO a Person
            person.setFirstName(personVO.getFirstName());
            person.setLastName(personVO.getLastName());
            person.setStreet(personVO.getStreet());
            person.setCity(personVO.getCity());
            person.setPostalCode(personVO.getPostalCode());
            person.setBirthday(personVO.getBirthday());

            listaPerson.add(person);

        }return listaPerson;
    }

    public ArrayList<PersonVO> getPersonsVO(ArrayList<Person> listaPersons) {
        ArrayList<PersonVO> listaPersonVO = new ArrayList<>();
        for (Person person : listaPersons) {
            PersonVO personVO = new PersonVO(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getStreet(),
                    person.getCity(),
                    person.getPostalCode(),
                    person.getBirthday()
            );
        }
        return listaPersonVO;
    }

    public PersonVO convertToPersonVO(Person person) {
        return new PersonVO(person.getId(), person.getFirstName(), person.getLastName(),
                person.getStreet(), person.getCity(), person.getPostalCode(), person.getBirthday());
    }

}
