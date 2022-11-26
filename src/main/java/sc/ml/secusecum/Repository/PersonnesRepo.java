package sc.ml.secusecum.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sc.ml.secusecum.Modeles.Personnes;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnesRepo extends JpaRepository<Personnes, Long> {

        Optional<Personnes> findByUsername(String username);

        Boolean existsByUsername(String username);

        Boolean existsByEmail(String email);


}