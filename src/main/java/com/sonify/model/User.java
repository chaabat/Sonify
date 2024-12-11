package com.sonify.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String login;
    private String password;
    private Boolean active;
    @DBRef
    private Set<Role> roles;
}