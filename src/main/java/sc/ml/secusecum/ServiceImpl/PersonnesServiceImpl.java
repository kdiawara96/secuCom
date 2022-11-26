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

    private PersonnesRepo personnesRepo;

    @Override
    public Personnes createUser(Personnes personnes) {
        return personnesRepo.save(personnes);
    }

    @Override
    public List<Personnes> readUser() {
        return personnesRepo.findAll();
    }

    @Override
    public Personnes update(Long id, Personnes personne) {
        return personnesRepo.findById(id).map(p->{
            p.setEmail(p.getEmail());
            p.setUsername(p.getUsername());
            p.setRoles(p.getRoles());
            p.setPassword(p.getPassword());


            return personnesRepo.save(p);

        }).orElseThrow(()->new RuntimeException("Personne non trouvé!"));
    }

    @Override
    public String delete(Long id) {
        personnesRepo.deleteById(id);
        return "Personne supprimé avec plaisir";
    }

    @Override
    public Personnes readByUserName(String username) {
        return personnesRepo.findByUsername(username);
    }
}