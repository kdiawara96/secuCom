package sc.ml.secusecum.Modeles;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 5)

public class Personnes {
@Id
@GeneratedValue(strategy = GenerationType.TABLE)
 private Long id;

 @Column(nullable = false)
 private String name;

 @Column(nullable = false, unique = true)
 private String email;

 @Column(nullable = false, unique = true)
 private String username;

 @Column(nullable = false)
 private String password;




 @OneToMany(fetch = FetchType.EAGER)
 Collection<Roles> roles = new ArrayList<>();
}
