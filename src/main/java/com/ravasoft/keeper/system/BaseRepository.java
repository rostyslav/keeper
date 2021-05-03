package com.ravasoft.keeper.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseRepository<T> {

    protected JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    protected abstract RowMapper<T> getMapper();

    protected T findOne(String sql, Object[] args) {
        T t;
        try {
            t = jdbc.queryForObject(sql, getMapper(), args);
        } catch (EmptyResultDataAccessException ex) {
            t = null;
        }
        return t;
    }
}