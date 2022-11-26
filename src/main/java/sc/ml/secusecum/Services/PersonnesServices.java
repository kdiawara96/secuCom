package sc.ml.secusecum.Services;

import org.springframework.stereotype.Service;
import sc.ml.secusecum.Modeles.Personnes;

import java.util.List;


public interface PersonnesServices {

    Personnes createUser(Personnes personnes);

    List<Personnes> readUser();

    Personnes update(Long id, Personnes personne);

    String delete(Long id);
}
