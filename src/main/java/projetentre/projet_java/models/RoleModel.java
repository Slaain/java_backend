package projetentre.projet_java.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import projetentre.projet_java.models.BaseModel;  // Ajout de cet import

@Entity
@Table(name = "roles")
@Data
public class RoleModel extends BaseModel {
    @NotEmpty
    @Column(unique = true)
    private String name;
}