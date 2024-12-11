package com.sonify.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Document(collection = "roles")
@Data
public class Role {
    @Id
private String id;
private String name;
}
