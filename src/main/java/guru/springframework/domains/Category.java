package guru.springframework.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Document
public class Category {
    @Id
    private String id;
    private String name;

    @DBRef
    private Set<Recipe> recipes;
}
