package guru.springframework.models;

import javax.persistence.Entity;

@Entity
public class UnitOfMeasure extends BaseEntity{

    private String description; // Unit Of Measure

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
