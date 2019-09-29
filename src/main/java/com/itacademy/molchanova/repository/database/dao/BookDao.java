package com.itacademy.molchanova.repository.database.dao;

import com.itacademy.molchanova.service.dto.BookDto;
import com.itacademy.molchanova.web.SearchFilter;

import java.util.List;

public interface BookDao {

    List<BookDto> getBookList(SearchFilter searchFilter);

}
