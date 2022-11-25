package sc.ml.secusecum.Services;

import org.springframework.stereotype.Service;
import sc.ml.secusecum.Modeles.Personnes;

import java.util.List;


public interface PersonnesServices {

    Personnes creer(Personnes personnes);

    List<Personnes> lister();

    Personnes update(Long id , Personnes personnes);

    String delete(Long id);
}
