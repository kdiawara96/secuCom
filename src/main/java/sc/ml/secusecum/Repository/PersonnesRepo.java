package sc.ml.secusecum.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Modeles.Roles;

import java.util.List;
import java.util.Optional;

public interface PersonnesRepo extends JpaRepository<Personnes, Long> {

    public Personnes findByUsername(String username);
    public Optional<Personnes> findById(Long id);

}
