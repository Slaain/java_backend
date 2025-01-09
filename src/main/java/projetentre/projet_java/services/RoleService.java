package projetentre.projet_java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetentre.projet_java.entities.Role;
import projetentre.projet_java.entities.User;
import projetentre.projet_java.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Créer un rôle
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Récupérer tous les rôles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Récupérer un rôle par id
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Mettre à jour un rôle
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    // Supprimer un rôle
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Dissocier les utilisateurs liés au rôle
        if (role.getUsers() != null) {
            role.getUsers().forEach(user -> user.getRoles().remove(role));
        }

        // Supprimer le rôle
        roleRepository.delete(role);
    }

    // Trouver un rôle par nom
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
