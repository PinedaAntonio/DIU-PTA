package com.example.agenda.Modelo.repository;


import com.example.agenda.Modelo.ExcepcionPerson;
import com.example.agenda.Modelo.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO>ObtenerListaPersonas() throws ExcepcionPerson;

    void addPersona(PersonVO var1) throws ExcepcionPerson;

    void deletePersona(Integer var1) throws ExcepcionPerson;

    void editPersona(PersonVO var1) throws ExcepcionPerson;

    int lastId() throws ExcepcionPerson;
}
