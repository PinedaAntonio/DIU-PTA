package com.example.AgendaReact.controller.impl;

import com.example.AgendaReact.controller.AgendaAPI;
import com.example.AgendaReact.model.AgendaVO;
import com.example.AgendaReact.repository.AgendaRepository;
import com.example.AgendaReact.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AgendaController implements AgendaAPI {
    @Autowired
    private AgendaService AgendaService;
    @Autowired
    private AgendaRepository AgendaRepository;
    @Override
    @GetMapping("/Agenda")
    public List<AgendaVO> getAllAgenda(){return AgendaService.getAllAgenda();}

    @Override
    @GetMapping("/Agenda/{id}")
    public Optional<AgendaVO> getAgendaById(@PathVariable String id) {
        return AgendaService.getAgendaById(id);
    }

    @Override
    @GetMapping("/Agenda/title/{title}")
    public List<AgendaVO> findByNameContaining(@PathVariable String firstName) {
        return AgendaService.findByNameContaining(firstName);
    }



    @Override
    @PostMapping("/Agenda")
    public AgendaVO save(@RequestBody AgendaVO AgendaVO) {
        return AgendaService.save(AgendaVO);
    }

    @Override
    @PutMapping("/Agenda/{id}")
    public AgendaVO updateAgenda(@RequestBody AgendaVO Agenda, @PathVariable String id) {
        return AgendaService.updateAgenda(Agenda);
    }

    @Override
    @DeleteMapping("/Agenda/{id}")
    public ResponseEntity deleteAgenda(@PathVariable String id) {
        return AgendaService.deleteAgenda(id);
    }

    @Override
    @DeleteMapping("/Agenda")
    public ResponseEntity deleteAllAgenda() {
        return AgendaService.deleteAllAgenda();
    }
}
