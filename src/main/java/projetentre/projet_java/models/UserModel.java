package projetentre.projet_java.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
// Ajout des imports manquants
import projetentre.projet_java.models.BaseModel;
import projetentre.projet_java.models.RoleModel;

@Entity
@Table(name = "users")
@Data
public class UserModel extends BaseModel {
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    private String prenom;

    private String nom;

    private String username;

    @Column(name = "date_creation")  // On spécifie explicitement le nom
    private LocalDateTime dateCreation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleModel> roles = new HashSet<>();
}