package com.sonify.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Set;

@Document(collection = "roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private String name;
    
    // Remove or modify this if it's causing circular reference issues
    // @DBRef(lazy = true)
    // private Set<User> users;
}
