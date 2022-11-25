package sc.ml.secusecum.Services;

import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Modeles.Roles;

import java.util.List;

public interface ServicesCompte {

    Roles createRoles(Roles roles);
    Personnes createPersonnes(Personnes personnes);
    void assignerRole(String username, String nomrole);
    Personnes chargePersonnesByUserName(String username);
    List<Personnes> listerPersonnes();
}
