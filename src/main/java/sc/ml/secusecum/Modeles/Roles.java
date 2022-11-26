package sc.ml.secusecum.Modeles;

import javax.management.relation.Role;
import javax.persistence.*;

import lombok.Data;

@Data

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Enumerated(EnumType.STRING)
    @Column(length = 15, unique = true, nullable = false)
    private String name;

    public Roles() {

    }

    public Roles(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setRolesName(String name) {
        this.name = name;
    }
}