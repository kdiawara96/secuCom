package sc.ml.secusecum.Modeles;

import javax.persistence.*;

import lombok.Data;

@Data

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;

    public Roles() {

    }

    public Roles(ERoles name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERoles getName() {
        return name;
    }

    public void setRolesName(ERoles name) {
        this.name = name;
    }
}