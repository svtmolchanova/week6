package com.itacademy.molchanova.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {

    private String name;
    private Integer creationYear;
    private String authorName;
    private String genreName;

}
