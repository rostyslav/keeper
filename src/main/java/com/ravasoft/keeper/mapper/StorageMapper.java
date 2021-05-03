package com.ravasoft.keeper.mapper;

import com.ravasoft.keeper.dictionary.StorageType;
import com.ravasoft.keeper.model.Storage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageMapper implements RowMapper<Storage> {

    @Override
    public Storage mapRow(ResultSet rs, int i) throws SQLException {
        return new Storage(
                rs.getInt("id"),
                StorageType.valueOf(rs.getString("type")),
                rs.getString("path")
        );
    }

}
