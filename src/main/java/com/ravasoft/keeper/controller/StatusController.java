package com.ravasoft.keeper.controller;

import com.ravasoft.keeper.model.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public Status process() {
        return new Status();
    }

}
