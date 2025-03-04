package com.example.AgendaReact.util;

import com.example.AgendaReact.model.AgendaVO;
import com.example.AgendaReact.model.AgendaDto;

import java.util.List;
import java.util.stream.Collectors;

public class AgendaMapper {

    public static AgendaDto AgendaMapperDtoToEntity(AgendaVO Agenda){
        return AgendaDto.builder()
                .id(Agenda.getId())
                .firstName(Agenda.getFirstName())
                .lastName(Agenda.getLastName())
                .street(Agenda.getStreet())
                .city(Agenda.getCity())
                .birthday(Agenda.getBirthday())
                .postalCode(Agenda.getPostalCode())
                .tutorials(Agenda.getTutorials())
                .build();
    }

    public static AgendaVO AgendaMapperEntityToDto(AgendaDto AgendaDto){
        return AgendaVO.builder()
                .id(AgendaDto.getId())
                .firstName(AgendaDto.getFirstName())
                .lastName(AgendaDto.getLastName())
                .street(AgendaDto.getStreet())
                .city(AgendaDto.getCity())
                .birthday(AgendaDto.getBirthday())
                .postalCode(AgendaDto.getPostalCode())
                .tutorials(AgendaDto.getTutorials())
                .build();
    }


    public static List<AgendaDto> AgendaListMapperDtoToEntity(List<AgendaVO> agendaVOList) {
        return agendaVOList.stream()
                .map(AgendaMapper::AgendaMapperDtoToEntity)
                .collect(Collectors.toList());
    }


    public static List<AgendaVO> AgendaListMapperEntityToDto(List<AgendaDto> agendaDtoList) {
        return agendaDtoList.stream()
                .map(AgendaMapper::AgendaMapperEntityToDto)
                .collect(Collectors.toList());
    }
}
