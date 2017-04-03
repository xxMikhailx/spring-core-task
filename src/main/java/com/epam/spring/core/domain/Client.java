package com.epam.spring.core.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client {
    private String id;
    private String fullName;

    public Client() {
    }

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    @Value("${id}")
    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    @Value("${name}")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
