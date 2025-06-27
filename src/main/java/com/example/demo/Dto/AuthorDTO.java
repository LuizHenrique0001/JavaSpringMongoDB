package com.example.demo.Dto;

import com.example.demo.Domain.User;

public class AuthorDTO {

    private String id;
    private String name;

    public AuthorDTO(){}

    public AuthorDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
