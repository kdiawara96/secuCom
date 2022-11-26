package sc.ml.secusecum.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Modeles.Roles;
import sc.ml.secusecum.Repository.RolesRepository;
import sc.ml.secusecum.ServiceImpl.RoleServiceImpli;
import sc.ml.secusecum.Services.PersonnesServices;
import sc.ml.secusecum.Services.RoleService;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/personnes")
@AllArgsConstructor

public class PersonnesController{

    //Nous allons injecter password encoder et declarer dans le constructeur
    private PasswordEncoder passwordEncoder;
   private final PersonnesServices personnesServices;
   private RoleService roleService;
   private RolesRepository rol;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Personnes create(Authentication auauthentication, @RequestBody Personnes personnes){





       
        personnes.setPassword(passwordEncoder.encode(personnes.getPassword()));
        return personnesServices.createUser(personnes);
    }


    @GetMapping("/readuser")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public List<Personnes> readUser(Authentication auauthentication){
        return personnesServices.readUser();
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Personnes update(Authentication auauthentication,@PathVariable Long id, @RequestBody Personnes personnes){
        return personnesServices.update(id, personnes);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String delete(Authentication auauthentication, @PathVariable Long id){
        return personnesServices.delete(id);
    }

    @GetMapping("/readeUserByUserName")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")
    public Personnes readUserByUserName(Authentication auauthentication,String username){
        return personnesServices.readByUserName(username);
    }

    @PostMapping("/create_role")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Roles createRole(Authentication auauthentication, @RequestBody Roles name){
        System.err.println(name);
        return roleService.addRole(name);
    }

}
