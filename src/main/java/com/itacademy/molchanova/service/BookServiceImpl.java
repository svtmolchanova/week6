package com.itacademy.molchanova.service;

import com.itacademy.molchanova.repository.database.dao.BookDao;
import com.itacademy.molchanova.service.dto.BookDto;
import com.itacademy.molchanova.web.SearchFilter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    static String[] Headers_string = {"Book name", "Creation year", "Genre name", "Author name"};

    @Override
    public void getReport(SearchFilter filter) throws IOException {
        File file = new File("book.csv");
        FileWriter fileWriter = new FileWriter(file);
        try (CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(Headers_string))) {
            List<BookDto> bookDtos = getList(filter);
            for (BookDto r : bookDtos) {
                printer.printRecord(r.getName(), r.getCreationYear(), r.getAuthorName(), r.getGenreName());
            }
        }
    }

    @Override
    public List<BookDto> getList(SearchFilter searchFilter) {
        List<BookDto> bookDtos = bookDao.getBookList(searchFilter);
        return bookDtos;
    }
}
