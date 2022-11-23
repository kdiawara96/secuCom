package secuCom.example.SecuCom.Modeles;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


import lombok.Data;


@Entity
@Data

@DiscriminatorValue("ADMIN")

public class Admins {

}
