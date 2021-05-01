package com.ravasoft.keeper.model;

import java.time.LocalDateTime;

public class Status {

    private LocalDateTime currentDateTime;

    public Status() {
        this.currentDateTime = LocalDateTime.now();
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

}
