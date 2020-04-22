package guru.springframework.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
//@EqualsAndHashCode(callSuper = false)
@Entity
public class Category extends BaseEntity{

    private String description;

    @ManyToMany(mappedBy = "categories") // mappedBy = "categories" , the name of the attribute in Recipe
    private Set<Recipe> recipes;

}
