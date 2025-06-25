package com.example.demo.Services;

import com.example.demo.Domain.User;
import com.example.demo.Dto.UserDTO;
import com.example.demo.Repositores.UserRepository;
import com.example.demo.Services.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findByStatus(true);
    }

    public User findById(String id){
        Optional<User> user = userRepository.findById(id);
       return user.orElseThrow(() -> new ObjectNotFoundException("Id nao encontrado!"));
    }

    public User save(User user){
       return userRepository.insert(user);
    }

    public User fromDTO(UserDTO userDTO){
        return new User(null, userDTO.getName(), userDTO.getEmail(), Instant.now(), true);
    }

    public void delete(String id){
        User obj = findById(id);
        obj.setStatus(false);
        userRepository.save(obj);
    }

    public void update(String id, UserDTO userDTO){
        User obj = findById(id);
        obj.setName(userDTO.getName());
        obj.setEmail(userDTO.getEmail());
        userRepository.save(obj);
    }
}
