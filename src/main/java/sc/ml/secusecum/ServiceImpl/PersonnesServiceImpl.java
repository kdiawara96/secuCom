package sc.ml.secusecum.ServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Repository.PersonnesRepo;
import sc.ml.secusecum.Services.PersonnesServices;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonnesServiceImpl implements PersonnesServices {

    private final PersonnesRepo personnesRepo;

    @Override
    public Personnes creer(Personnes personnes) {
        return personnesRepo.save(personnes);
    }

    @Override
    public List<Personnes> lister() {
        return personnesRepo.findAll();
    }

    @Override
    public Personnes update(Long id, Personnes personnes) {
        return personnesRepo.findById(id).map(person->{
            person.setName(person.getName());
            person.setUsername(person.getUsername());
            person.setEmail(person.getEmail());
            person.setPassword(person.getPassword());
            return personnesRepo.save(person);
        }).orElseThrow(()->new RuntimeException("Personne non trouver"));
    }

    @Override
    public String delete(Long id) {
        personnesRepo.deleteById(id);
        return "Personne supprimé!";
    }
}
