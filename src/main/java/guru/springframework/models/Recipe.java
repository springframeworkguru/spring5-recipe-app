package guru.springframework.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // get the id value from the DB
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;

    @Lob // create a large field , default is 255 char
    private String directions;

    @Enumerated(value = EnumType.STRING) // specify to use the String value of the Enum
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable( name = "recipe_category",
                joinColumns = @JoinColumn(name = "recipe_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    // Map: One Recipe -> Many Ingredient, While the Recipe owns the Ingredient entity
    // cascade = CascadeType.ALL , Delete a Recipe will delete all Ingredient
    // cascade = CascadeType.ALL , Any change to Recipe.ingredients will be saved to ingredients Table
    // mappedBy = "recipe" The Ingredient entity there will be a Recipe field to rough back to Recipe
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>(); // Unique set on Ingredient !!

    @Lob // Large Object, hint JPA to expect big byte[] here
    private byte[] image;

    // Recipe is owner of Notes, so by deleting Recipe we also delete his Notes
    // CascadeType.ALL , any update to the Recipe.notes will be persistent to Notes table
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes; // 1-1

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
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
        notes.setRecipe(this);
        this.notes = notes;
    }
}
