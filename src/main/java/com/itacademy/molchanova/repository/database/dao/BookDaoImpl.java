package com.itacademy.molchanova.repository.database.dao;

import com.itacademy.molchanova.service.dto.BookDto;
import com.itacademy.molchanova.web.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Repository
public class BookDaoImpl implements BookDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<BookDto> getBookList(SearchFilter filter) {
        String sql;
        List<BookDto> books;
        if (Objects.nonNull(filter.getAuthors())) {
            sql = "SELECT b.name as name, b.creation_year as creation_year, g.name as genre_name, a.name as author_name FROM book as b " +
                    "left join genre as g on g.id = b.genre_id " +
                    "left join author as a on a.id = b.author_id where a.name in (?)";
            books = (List<BookDto>) jdbcTemplate.query(sql, new Object[]{filter.getAuthors()},
                    (rs, row) -> new BookDto(rs.getString("name"), rs.getInt("creation_year"),
                            rs.getString("genre_name"), rs.getString("author_name")));
        } else if (Objects.nonNull(filter.getGenres())) {
            sql = "SELECT b.name as name, b.creation_year as creation_year, g.name as genre_name, a.name as author_name FROM book as b " +
                    "left join genre as g on g.id = b.genre_id " +
                    "left join author as a on a.id = b.author_id where g.name in (?)";
            books = (List<BookDto>) jdbcTemplate.queryForObject(sql, new Object[]{filter.getGenres()},
                    (rs, row) -> new BookDto(rs.getString("name"), rs.getInt("creation_year"),
                            rs.getString("genre_name"), rs.getString("author_name")));
        } else {
            sql = "SELECT b.name as name, b.creation_year as creation_year, g.name as genre_name, a.name as author_name FROM book as b " +
                    "left join genre as g on g.id = b.genre_id " +
                    "left join author as a on a.id = b.author_id where b.creation_year between ? and ?";
            books = (List<BookDto>) jdbcTemplate.queryForObject(sql, new Object[]{filter.getStartYear(), filter.getEndYear()},
                    (rs, row) -> new BookDto(rs.getString("name"), rs.getInt("creation_year"),
                            rs.getString("genre_name"), rs.getString("author_name")));
        }

        return books;
    }
}
