package com.example.tutorials.Tutorials.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class TutorialsVO {

    private String id;
    private String title;
    private String description;
    private Boolean published;

}

