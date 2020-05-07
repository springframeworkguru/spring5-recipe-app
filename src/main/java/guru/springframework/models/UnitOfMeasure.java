package guru.springframework.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class UnitOfMeasure extends BaseEntity{

    private String description; // Unit Of Measure

}
