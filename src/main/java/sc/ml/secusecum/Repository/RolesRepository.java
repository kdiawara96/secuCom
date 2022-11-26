package sc.ml.secusecum.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import sc.ml.secusecum.Modeles.ERoles;
import sc.ml.secusecum.Modeles.Roles;

import javax.management.relation.Role;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Roles findByName(ERoles name);
}
