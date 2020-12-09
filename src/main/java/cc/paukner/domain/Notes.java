package cc.paukner.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(exclude = {"recipe"}) // to break circular reference
@NoArgsConstructor // needed by Spring
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // no cascade, as this is not the owner
    private Recipe recipe;

    @Lob
    private String content;

    public Notes(String content) {
        this.content = content;
    }
}
