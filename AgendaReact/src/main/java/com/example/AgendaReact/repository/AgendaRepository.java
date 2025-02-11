package com.example.AgendaReact.repository;

import com.example.AgendaReact.model.AgendaDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends MongoRepository<AgendaDto,String> {
    List<AgendaDto>  findAll();
    Optional<AgendaDto> getAgendaById();
    List<AgendaDto> findByfirstNameContaining(String firstName);
}
