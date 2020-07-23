package guru.springframework.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String diretions;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    Set<Ingredient> ingredients = new HashSet<Ingredient>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

   /* @ManyToMany
    @JoinTable(name="recipe_category",
                joinColumns = @JoinColumn(name="recipe_id"),
                inverseJoinColumns = @JoinColumn(name="category_id"))*/
  //  private Set<Category> categories = new HashSet<Category>();

    @Enumerated(EnumType.STRING)
    private Difficulty diffculty;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDiretions() {
        return diretions;
    }

    public void setDiretions(String diretions) {
        this.diretions = diretions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

        public Notes getNotes() {
            return notes;
        }

        public void setNotes(Notes notes) {
            this.notes = notes;
        }

            public Set<Ingredient> getIngredients() {
                return ingredients;
            }

            public void setIngredients(Set<Ingredient> ingredients) {
                this.ingredients = ingredients;
            }

    public Difficulty getDiffculty() {
        return diffculty;
    }

    public void setDiffculty(Difficulty diffculty) {
        this.diffculty = diffculty;
    }
/*
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    */
}
