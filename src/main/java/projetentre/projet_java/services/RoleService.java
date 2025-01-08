package projetentre.projet_java.services;  // Correction : java au lieu de jave

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetentre.projet_java.entities.Role;
import projetentre.projet_java.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;  // Correction : Optional au lieu de Optionnal

@Service  // Correction : Service au lieu de Services
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;  // Ajout du point-virgule manquant
    }

    // Créer un rôle
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Récupérer tous les rôles
    public List<Role> getAllRoles() {  // Correction : getAllRoles au lieu de getAllUsers
        return roleRepository.findAll();
    }

    // Récupérer un rôle par id
    public Optional<Role> getRoleById(Long id) {  // Correction : findById au lieu de findByRole
        return roleRepository.findById(id);
    }

    // Mettre à jour un rôle
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    // Supprimer un rôle
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    // Ajout de la méthode findByName qui était dans le repository
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

}