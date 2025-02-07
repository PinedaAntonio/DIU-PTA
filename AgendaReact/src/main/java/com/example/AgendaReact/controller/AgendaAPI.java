package com.example.AgendaReact.controller;

import com.example.AgendaReact.model.AgendaVO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AgendaAPI {
    List<AgendaVO> getAllAgenda();
    Optional<AgendaVO> getAgendaById(String id);
    List<AgendaVO> findByNameContaining(String firstName);
    AgendaVO save(AgendaVO Agenda);
    AgendaVO updateAgenda(AgendaVO Agenda, String id);
    ResponseEntity deleteAgenda(String id);
    ResponseEntity deleteAllAgenda();
}
