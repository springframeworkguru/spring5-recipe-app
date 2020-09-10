package guru.springframework.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//@Data
//@Entity
@Getter
@Setter
@Document
public class UnitOfMeasure {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String Id;
    private String uom;


}
