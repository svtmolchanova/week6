package com.itacademy.molchanova.web;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchFilter {

    String authors;
    String genres;
    Integer startYear;
    Integer endYear;

}
