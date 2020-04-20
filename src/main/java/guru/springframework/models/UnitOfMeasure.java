package guru.springframework.models;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class UnitOfMeasure extends BaseEntity{

    private String description; // Unit Of Measure

}
