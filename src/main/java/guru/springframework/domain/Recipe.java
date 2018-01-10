package guru.springframework.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private Integer preTime;
	private Integer cockTime;
	private Integer serving;
	private String source;
	private String url;
	private String direction;
	
	@Lob
	private byte[] image;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Note notes;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="recipe")
	private Set<Ingredient> ingredients;

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

	public Integer getPreTime() {
		return preTime;
	}

	public void setPreTime(Integer preTime) {
		this.preTime = preTime;
	}

	public Integer getCockTime() {
		return cockTime;
	}

	public void setCockTime(Integer cockTime) {
		this.cockTime = cockTime;
	}

	public Integer getServing() {
		return serving;
	}

	public void setServing(Integer serving) {
		this.serving = serving;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Note getNote() {
		return notes;
	}

	public void setNote(Note note) {
		this.notes = note;
	}

	public Note getNotes() {
		return notes;
	}

	public void setNotes(Note notes) {
		this.notes = notes;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
}
