package guru.springframework.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Data
//@EqualsAndHashCode(exclude = {"recipe"})
@EqualsAndHashCode(callSuper = false)
@Entity
public class Notes extends BaseEntity{

    @OneToOne // leave empty so the Recipe entity will own this object
    private Recipe recipe; // 1-1

    @Lob // Large Object, hint JPA to expect lots of data in this filed
    private String recipeNotes;

}
