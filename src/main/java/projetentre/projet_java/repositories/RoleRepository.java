//  En fait, le CRUD est présent, mais de manière implicite.

package projetentre.projet_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetentre.projet_java.entities.Role;

//Quand vous écrivez :

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
  //      Vous héritez automatiquement de toutes les méthodes CRUD de JpaRepository. C'est comme si vous aviez écrit :
//  public interface RoleRepository extends JpaRepository<Role, Long> {
//      // Méthodes CRUD héritées automatiquement de JpaRepository :
//
//      // CREATE
//      Role save(Role role);
//
//      // READ
//      List<Role> findAll();
//      Optional<Role> findById(Long id);
//      boolean existsById(Long id);
//
//      // UPDATE
//      Role save(Role role);  // Même méthode que CREATE, mais avec un ID existant
//
//      // DELETE
//      void delete(Role role);
//      void deleteById(Long id);
//
//      // Notre méthode personnalisée
//      Role findByName(String name);
//  }