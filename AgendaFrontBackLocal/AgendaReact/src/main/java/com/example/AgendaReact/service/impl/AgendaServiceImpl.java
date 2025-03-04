package com.example.AgendaReact.service.impl;

import com.example.AgendaReact.model.AgendaVO;
import com.example.AgendaReact.model.AgendaDto;
import com.example.AgendaReact.repository.AgendaRepository;
import com.example.AgendaReact.service.AgendaService;
import com.example.AgendaReact.util.AgendaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl implements AgendaService {
    @Autowired
    private AgendaRepository AgendaRepository;

    @Override
    public List<AgendaVO> getAllAgenda(){
        List<AgendaDto> agendaDtoList = AgendaRepository.findAll();
        return agendaDtoList.stream()
                .map(AgendaMapper::AgendaMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AgendaVO> getAgendaById(String id) {
        Optional<AgendaDto> AgendaOptional = AgendaRepository.findById(id);

        return AgendaOptional.map(AgendaMapper::AgendaMapperEntityToDto);
    }

    @Override
    public List<AgendaVO> findByfisrtNameContaining(String firstName) {
        List<AgendaDto> AgendaOptional = AgendaRepository.findByfirstNameContaining(firstName);

        return AgendaMapper.AgendaListMapperEntityToDto(AgendaOptional);
    }

    @Override
    public AgendaVO save(AgendaVO AgendaVO) {
        AgendaDto AgendaDto = AgendaMapper.AgendaMapperDtoToEntity(AgendaVO);
        AgendaDto savedAgendaEntity = AgendaRepository.save(AgendaDto);
        return AgendaMapper.AgendaMapperEntityToDto(savedAgendaEntity);
    }

    @Override
    public AgendaVO updateAgenda(AgendaVO Agenda) {
        Optional<AgendaDto> existingAgendaOptional = AgendaRepository.findById(Agenda.getId());

        if (existingAgendaOptional.isPresent()) {
            AgendaDto existingAgenda = existingAgendaOptional.get();
            existingAgenda.setFirstName(Agenda.getFirstName());
            existingAgenda.setLastName(Agenda.getLastName());
            existingAgenda.setStreet(Agenda.getStreet());
            existingAgenda.setCity(Agenda.getCity());
            existingAgenda.setBirthday(Agenda.getBirthday());
            existingAgenda.setPostalCode(Agenda.getPostalCode());
            existingAgenda.setTutorials(Agenda.getTutorials());

            AgendaDto updatedAgenda = AgendaRepository.save(existingAgenda);

            return AgendaMapper.AgendaMapperEntityToDto(updatedAgenda);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity deleteAgenda(String id) {
        try {
            Optional<AgendaDto> existingAgendaOptional = AgendaRepository.findById(id);
            if (existingAgendaOptional.isPresent()) {
                AgendaRepository.deleteById(id);
                return ResponseEntity.ok("Agenda eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el Agenda");
        }
    }

    @Override
    public ResponseEntity deleteAllAgenda() {
        AgendaRepository.deleteAll();
        ResponseEntity.ok("Agenda eliminado exitosamente");
        return ResponseEntity.ok().build();
    }



}
