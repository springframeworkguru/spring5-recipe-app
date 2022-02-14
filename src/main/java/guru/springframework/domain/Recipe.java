package guru.springframework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer prepTime;
	private Integer cookTime;
	private Integer savings;
	private String source;
	private String url;
	private String description;
	@Lob
	private byte image[] ;
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;
	@ManyToMany()
	@JoinTable(name = "recipe_category",
	joinColumns =@JoinColumn(name="recipe_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set <Category> categories = new HashSet<>();
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
	private Set <Ingredient> ingredients=new HashSet<>();
	@Enumerated(value=EnumType.STRING)
	private Difficulty difficulty ;
	@Lob
    private String directions;
	
	
	
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
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Notes getNotes() {
		return notes;
	}
	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getSavings() {
		return savings;
	}
	public void setSavings(Integer savings) {
		this.savings = savings;
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Set <Category> getCategory() {
		return categories;
	}
	public void setCategory(Set <Category> categories) {
		this.categories = categories;
	}
	
	
	
	
	

}
