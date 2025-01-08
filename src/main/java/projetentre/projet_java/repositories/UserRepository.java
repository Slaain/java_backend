package projetentre.projet_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetentre.projet_java.entities.User;

//Lane CRUD

public interface UserRepository extends JpaRepository<User, Long> {

    // Méthodes personnalisées

    User findByEmail(String email);
    boolean existsByEmail(String email);
}