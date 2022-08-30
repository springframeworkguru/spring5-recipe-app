package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Recipe {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	  	private String description;
	    private Integer prepTime;
	    private Integer cookTime;
	    private Integer servings;
	    private String source;
	    private String url;
	    private String directions;
	    //todo add
	   //private Difficulty difficulty;
	    private Byte[] image;
	    
	    @OneToOne
	    private Notes notes;  //recipe and notes one to one relation
	    
	    
	    
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
