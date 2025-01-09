package projetentre.projet_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetentre.projet_java.entities.Role;
import projetentre.projet_java.entities.User;
import projetentre.projet_java.services.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        logger.info("Creating role: {}", role.getName());
        Role newRole = roleService.createRole(role);
        logger.info("Role created successfully with ID: {}", newRole.getId());
        return ResponseEntity.ok(newRole);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        logger.info("Fetching all roles");
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        logger.info("Fetching role with ID: {}", id);
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            logger.info("Request to delete role with ID: {}", id);
            roleService.deleteRole(id);
            logger.info("Role with ID: {} deleted successfully", id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            logger.error("Error deleting role with ID: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }


    // GET USERS BY ROLE
    @GetMapping("/{roleId}/users")
    public ResponseEntity<Set<User>> getUsersByRole(@PathVariable Long roleId) {
        Role role = roleService.getRoleById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return ResponseEntity.ok(role.getUsers());
    }

}
