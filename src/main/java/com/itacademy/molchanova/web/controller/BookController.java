package com.itacademy.molchanova.web.controller;

import com.itacademy.molchanova.service.BookService;
import com.itacademy.molchanova.web.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(MappingPath.REPORTS)
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public void getReport(@RequestParam(value = "authors") String authorNames,
                          @RequestParam(value = "genres") String genreNames,
                          @RequestParam(value = "startYear") Integer startYear,
                          @RequestParam(value = "endYear") Integer endYear) throws IOException {
        SearchFilter filter = new SearchFilter();
        filter.setAuthors(authorNames);
        filter.setGenres(genreNames);
        filter.setStartYear(startYear);
        filter.setEndYear(endYear);
        bookService.getReport(filter);
    }

}
