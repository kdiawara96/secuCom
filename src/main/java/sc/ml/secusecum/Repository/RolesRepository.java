package sc.ml.secusecum.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sc.ml.secusecum.Modeles.Roles;

import javax.management.relation.Role;

public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByRolesName(String rolesName);
}
