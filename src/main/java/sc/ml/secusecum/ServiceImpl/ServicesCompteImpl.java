package sc.ml.secusecum.ServiceImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Modeles.Roles;
import sc.ml.secusecum.Repository.PersonnesRepo;
import sc.ml.secusecum.Repository.RolesRepository;
import sc.ml.secusecum.Services.ServicesCompte;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor

public class ServicesCompteImpl implements ServicesCompte {

    private PersonnesRepo personnesRepo;
    private RolesRepository rolesRepository;

    @Override
    public Roles createRoles(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public Personnes createPersonnes(Personnes personnes) {
        return personnesRepo.save(personnes);
    }

    @Override
    public void assignerRole(String username, String nomrole) {
        Personnes person = personnesRepo.findByUsername(username);
        Roles roles = rolesRepository.findByRolesName(nomrole);
        person.getRoles().add(roles);
    }

    @Override
    public Personnes chargePersonnesByUserName(String username) {
        return personnesRepo.findByUsername(username);
    }

    @Override
    public List<Personnes> listerPersonnes() {
        return personnesRepo.findAll();
    }
}
