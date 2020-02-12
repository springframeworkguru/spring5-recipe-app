package guru.springframework.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UnitOfMeasure {

    String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
