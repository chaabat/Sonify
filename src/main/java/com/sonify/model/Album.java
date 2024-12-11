package com.sonify.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "albums")
public class Album {
    @Id
    private String id;
    
    private String titre;
    private String artiste;
    private Integer annee;
    
    private List<Chanson> chansons = new ArrayList<>();
    
 
}
