package guru.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String description;
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure uom;

	@ManyToOne
	private Recipe recipe;

	public String getDescription(){
		return description;
	}

	public void setDescription( String description ){
		this.description = description;
	}

	public BigDecimal getAmount(){
		return amount;
	}

	public void setAmount( BigDecimal amount ){
		this.amount = amount;
	}

	public Recipe getRecipe(){
		return recipe;
	}

	public void setRecipe( Recipe recipe ){
		this.recipe = recipe;
	}

	public UnitOfMeasure getUom(){
		return uom;
	}

	public void setUom( UnitOfMeasure uom ){
		this.uom = uom;
	}
}
