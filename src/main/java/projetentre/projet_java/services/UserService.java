package projetentre.projet_java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetentre.projet_java.entities.User;
import projetentre.projet_java.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // crée un user
    public User createUser(User user){
        return userRepository.save(user);
    }

    // recuperer tout les user
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //recuperer un user par id
    public Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

    //update user
    public User updateUser(User user){
        return userRepository.save(user);
    }

    //supprimer user
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    // Trouver un utilisateur par email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}