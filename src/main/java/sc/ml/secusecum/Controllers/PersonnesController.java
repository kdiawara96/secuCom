package sc.ml.secusecum.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Services.PersonnesServices;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/personnes")
@AllArgsConstructor

public class PersonnesController{

    //Nous allons injecter password encoder et declarer dans le constructeur
    private PasswordEncoder passwordEncoder;
   private final PersonnesServices personnesServices;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Personnes create(Authentication auauthentication, @RequestBody Personnes personnes){
        personnes.setPassword(passwordEncoder.encode(personnes.getPassword()));
        return personnesServices.createUser(personnes);
    }


    @GetMapping("/readuser")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_USER')")

    public List<Personnes> readUser(){
        return personnesServices.readUser();
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Personnes update(@PathVariable Long id, @RequestBody Personnes personnes){
        return personnesServices.update(id, personnes);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String delete(@PathVariable Long id){
        return personnesServices.delete(id);
    }
}
