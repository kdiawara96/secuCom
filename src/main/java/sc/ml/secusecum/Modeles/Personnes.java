package sc.ml.secusecum.Modeles;


import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "personnes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Personnes {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(name = "username", length = 20)
 private String username;

 @Column(name = "email", length = 50)
 private String email;

 @Column(name = "password", length = 120)
 private String password;

 @ManyToMany(fetch = FetchType.LAZY)
 @JoinTable(name = "personnes_roles",
         joinColumns = @JoinColumn(name = "personne_id"),
         inverseJoinColumns = @JoinColumn(name = "role_id"))
 private Set<Roles> roles = new HashSet<>();

 public Personnes() {
 }

 public Personnes(String username, String email, String password) {
  this.username = username;
  this.email = email;
  this.password = password;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public Set<Roles> getRoles() {
  return roles;
 }

 public void setRoles(Set<Roles> roles) {
  this.roles = roles;
 }
}


