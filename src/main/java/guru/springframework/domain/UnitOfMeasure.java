package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
//    @OneToOne      //weArentdointthisin thisside because its unidirectional.//yehagnawbchanew miyakew relationship'un.
//    private Ingredient ingredient;

}
