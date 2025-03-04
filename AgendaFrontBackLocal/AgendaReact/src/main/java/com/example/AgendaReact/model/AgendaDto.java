package com.example.AgendaReact.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Builder


public class AgendaDto {
    private String id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private Integer postalCode;
    private LocalDate birthday;
    private String[] tutorials;
}
