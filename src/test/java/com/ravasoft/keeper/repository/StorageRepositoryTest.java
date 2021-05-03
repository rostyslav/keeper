package com.ravasoft.keeper.repository;

import com.ravasoft.keeper.dictionary.StorageType;
import com.ravasoft.keeper.model.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StorageRepositoryTest {

    private Storage storage;

    private static final String TEST_PATH = "TEST_PATH";

    @Autowired
    protected JdbcTemplate jdbc;

    @Autowired
    protected StorageRepository storageRepository;

    protected Storage storageCreate() {

        @SuppressWarnings("UnnecessaryLocalVariable")
        Storage storage = storageRepository.create(
                new Storage(StorageType.FILE, TEST_PATH)
        );

        return storage;
    }

    @BeforeEach
    public void before() {
        jdbc.execute("START TRANSACTION");
        storage = storageCreate();
    }

    @Test
    public void create() {
        assertNotNull(storage);
        assertNotNull(storage.getId());
        assertEquals(StorageType.FILE, storage.getType());
        assertEquals(TEST_PATH, storage.getPath());
    }

    @Test
    public void findByPath() {
        Storage foundStorage = storageRepository.findByPath(storage.getPath());
        assertNotNull(foundStorage);
        assertEquals(storage.getId(), foundStorage.getId());
        assertEquals(storage.getType(), foundStorage.getType());
        assertEquals(storage.getPath(), foundStorage.getPath());
    }

    @AfterEach
    public void after() {
        jdbc.execute("ROLLBACK");
    }

}
