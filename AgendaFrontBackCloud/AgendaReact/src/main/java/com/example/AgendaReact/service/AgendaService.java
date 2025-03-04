package com.example.AgendaReact.service;

import com.example.AgendaReact.model.AgendaVO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    List<AgendaVO> getAllAgenda(); //funciona
    Optional<AgendaVO> getAgendaById(String id); //funciona
    List<AgendaVO> findByfisrtNameContaining(String firstName);
    AgendaVO save(AgendaVO Agenda); //funciona
    AgendaVO updateAgenda(AgendaVO Agenda); //funciona
    ResponseEntity deleteAgenda(String id); //funciona
    ResponseEntity deleteAllAgenda(); //funciona
}
