package sc.ml.secusecum.ServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Modeles.Roles;
import sc.ml.secusecum.Repository.PersonnesRepo;
import sc.ml.secusecum.Repository.RolesRepository;
import sc.ml.secusecum.Services.RoleService;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class RoleServiceImpli implements RoleService {

    private RolesRepository rolesRepository;
    private PersonnesRepo personnesRepo;


    @Override
    public Roles addRole(Roles name) {
        return rolesRepository.save(name);
    }

    @Override
    public void AddRoleToUser(String username, String name) {
       Personnes personnes = personnesRepo.findByUsername(username);
       Roles role = rolesRepository.findByName(name);
        Set<Roles> roleee = new HashSet<>();
        roleee.add(role);
        personnes.setRoles(roleee);
    }

    @Override
    public Roles findbyname(String name) {
        return rolesRepository.findByName(name);
    }
}
