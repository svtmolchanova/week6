package com.itacademy.molchanova;

import com.itacademy.molchanova.service.BookService;
import com.itacademy.molchanova.web.SearchFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportTest {

    @Autowired
    private BookService bookService;

    @Test
    public void checkReport() throws IOException {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setAuthors("Herbert");
        bookService.getReport(searchFilter);
    }


}
