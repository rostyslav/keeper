package com.ravasoft.keeper.repository;

import com.ravasoft.keeper.mapper.StorageMapper;
import com.ravasoft.keeper.model.Storage;
import com.ravasoft.keeper.system.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class StorageRepository extends BaseRepository<Storage> {

    private static Logger log = LoggerFactory.getLogger(Storage.class);

    @Override
    protected RowMapper<Storage> getMapper() {
        return new StorageMapper();
    }

    public Storage create(Storage storage) {

        String sql = """
        INSERT INTO Storage(type, path) VALUES (?, ?)
        """;

        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbc.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
                ps.setString(1, String.valueOf(storage.getType()));
                ps.setString(2, storage.getPath());
                return ps;
            }, holder);
            storage.setId(Objects.requireNonNull(holder.getKey()).intValue());
        } catch (DataIntegrityViolationException dive) {
            log.error(dive.getMessage());
            log.error("DataIntegrityViolationException for Storage with path: {}", storage.getPath());
        }

        return storage;
    }

    public Storage findByPath(String path) {
        String sql = """
        SELECT id, type, path FROM Storage WHERE path = ?
        """;
        return findOne(sql, new Object[]{path});
    }

}
