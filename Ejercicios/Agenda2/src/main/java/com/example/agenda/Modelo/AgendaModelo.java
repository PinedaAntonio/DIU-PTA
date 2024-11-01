package com.example.agenda.Modelo;

import com.example.agenda.Modelo.repository.PersonRepository;
import com.example.agenda.Modelo.repository.impl.PersonRepositoryImpl;
import com.example.agenda.Person;
import com.example.agenda.util.PersonUtil;

import java.util.ArrayList;

public class AgendaModelo {
    private ArrayList<PersonVO> personasVO = new ArrayList<>();
    private ArrayList<Person> personas = new ArrayList<>();
    private PersonRepository personRepository = new PersonRepositoryImpl();
    private PersonUtil personUtil;

    // Constructor que inicializa personUtil
    public AgendaModelo() {
        this.personUtil = new PersonUtil();

    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ArrayList<Person> mostrarPersonas() {
        try {
            personasVO = personRepository.ObtenerListaPersonas();
            personas = personUtil.getPersons(personasVO);  // Usa personUtil ya inicializado
        } catch (ExcepcionPerson e) {
            e.printStackTrace();
        }
        return personas;
    }

    public void nuevaPerson(PersonVO personVO) throws ExcepcionPerson {
        personRepository.addPersona(personVO);
    }
    public void editarPerson(PersonVO personVO) throws ExcepcionPerson {
        personRepository.editPersona(personVO);
    }
    public void borrarPerson(PersonVO personVO) throws ExcepcionPerson {
        personRepository.deletePersona(personVO.getId());
    }

    public ArrayList<PersonVO> obtenerPersonas() throws ExcepcionPerson {
        ArrayList<PersonVO> personVOs = personRepository.ObtenerListaPersonas();
        return personVOs;
    }
}


