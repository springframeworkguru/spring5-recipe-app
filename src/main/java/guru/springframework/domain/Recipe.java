package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    
    //private Difficulty difficulty;
    @OneToMany(cascade = ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;
    
    @Lob
    private Byte[] image;
    
    @OneToOne(cascade = ALL)
    private Notes notes;
    
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
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getDirections() {
        return directions;
    }
    
    public void setDirections(String directions) {
        this.directions = directions;
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
}
