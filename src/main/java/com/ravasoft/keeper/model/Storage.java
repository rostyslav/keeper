package com.ravasoft.keeper.model;

import com.ravasoft.keeper.dictionary.StorageType;

public class Storage {

    private Integer id;

    private StorageType type;

    private String path;

    public Storage(StorageType type, String path) {
        this.type = type;
        this.path = path;
    }

    public Storage(Integer id, StorageType type, String path) {
        this.id = id;
        this.type = type;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StorageType getType() {
        return type;
    }

    public void setType(StorageType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
