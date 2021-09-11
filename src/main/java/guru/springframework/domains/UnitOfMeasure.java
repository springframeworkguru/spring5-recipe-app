package guru.springframework.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
public class UnitOfMeasure {
    @Id
    private String id;
    private String description;
}
