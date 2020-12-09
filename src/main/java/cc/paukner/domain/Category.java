package cc.paukner.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"}) // override @Data, exclude this property to break circular reference
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    // If only @ManyToMany specified on both sides, we'll get two tables category_recipes and recipe_categories
    // with columns {category,recipes}_id vs. {categories,recipe}_id
    @ManyToMany(mappedBy = "categories") // name of property on other side
    private Set<Recipe> recipes;

}
