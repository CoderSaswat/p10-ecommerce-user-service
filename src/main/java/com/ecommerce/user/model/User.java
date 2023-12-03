package com.ecommerce.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document

public class User {
    @Id
    private UUID id;
    private String name;
    private String password;
    private String email;
}
