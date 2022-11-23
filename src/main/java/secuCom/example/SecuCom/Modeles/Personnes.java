package secuCom.example.SecuCom.Modeles;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 5)

public class Personnes {
@Id
@GeneratedValue(strategy = GenerationType.TABLE)
 private long id;

 @Column(nullable = false)
 private String name;

 @Column(nullable = false)
 private String LastName;

 @Column(nullable = false, unique = true)
 private String phone;

 @Column(nullable = false, unique = true)
 private String email;

 @Column(nullable = false, unique = true)
 private String login;

 @Column(nullable = false)
 private String password;

 @ManyToOne
 private Roles role;
}
