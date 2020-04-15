package guru.springframework.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Recipe extends BaseEntity{

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String directions;

    @Enumerated(value = EnumType.STRING) // specify to use the String value of the Enum
    private Difficulty difficulty;

    // Map: One Recipe -> Many Ingredient, While the Recipe owns the Ingredient entity
    // cascade = CascadeType.ALL , Delete a Recipe will delete all Ingredient
    // mappedBy = "recipe" The Ingredient entity there will be a Recipe field to rough back to Recipe
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients; // Unique set on Ingredient !!

    @Lob // Large Object, hint JPA to expect big byte[] here
    private byte[] image;

    @OneToOne(cascade = CascadeType.ALL) // Recipe is owner of Notes, so by deleting Recipe we also delete his Notes
    private Notes notes; // 1-1

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
