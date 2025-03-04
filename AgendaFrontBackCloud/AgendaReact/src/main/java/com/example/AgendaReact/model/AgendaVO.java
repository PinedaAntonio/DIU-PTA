package com.example.AgendaReact.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document

public class AgendaVO {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private Integer postalCode;
    private LocalDate birthday;
    private String[] tutorials;
}
