package sc.ml.secusecum.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Repository.PersonnesRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
//Cette annotation nous permettra d'utiliser les logs pour verifier tous les mouvements de user
@Slf4j
public class PersonnaliserUserDetailService implements UserDetailsService {

    @Autowired
    private PersonnesRepo personnesRepo;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.trim().isEmpty()){
            throw new UsernameNotFoundException("Le nom "+username+" n'existe pas!");
        }
        //Nous allons recuperer le user par son nom
        Personnes personnes = personnesRepo.findByUsername(username);
        if (personnes == null){
            log.error("L'utilisateur"+ username +"n'a pas été trouver!");
            throw new UsernameNotFoundException("L'utilisateur"+ username +"n'a pas été trouver!");
        }else{
            log.error("L'utilisateur trouver!" ,username);
        }

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        personnes.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(personnes.getUsername(),personnes.getPassword(),authorities);
    }
}
