package guru.springframework.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;

	@ManyToOne
	private Recipe recipeInIngredientEntity;

	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure uom;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipeInIngredientEntity() {
		return recipeInIngredientEntity;
	}

	public void setRecipeInIngredientEntity(Recipe recipeInIngredientEntity) {
		this.recipeInIngredientEntity = recipeInIngredientEntity;
	}

	public UnitOfMeasure getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasure uom) {
		this.uom = uom;
	}

}
