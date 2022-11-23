package secuCom.example.SecuCom.Modeles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Roles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String libelle;


    @OneToMany(mappedBy = "role")
    Collection<Personnes> personnes = new ArrayList<>();
}

