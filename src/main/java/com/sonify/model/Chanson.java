package com.sonify.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "chansons")
public class Chanson {
    @Id
    private String id;
    private String titre;
    private Integer duree;  
    private Integer trackNumber;
    private String albumId;
    
     
    private Album album;
 
}
