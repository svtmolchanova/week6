package com.itacademy.molchanova.service;

import com.itacademy.molchanova.service.dto.BookDto;
import com.itacademy.molchanova.web.SearchFilter;

import java.io.IOException;
import java.util.List;


public interface BookService {

    void getReport(SearchFilter filter) throws IOException;

    List<BookDto> getList(SearchFilter filter);
}
