package sc.ml.secusecum.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Services.PersonnesServices;


@RestController
@RequestMapping("personnes")
@AllArgsConstructor

public class PersonnesController{

   private final PersonnesServices personnesServices;

    @PostMapping("/create")
    public Personnes create(@RequestBody Personnes personnes){
        return personnesServices.creer(personnes);
    }
}
