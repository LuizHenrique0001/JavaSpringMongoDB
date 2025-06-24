package com.example.demo.Services;

import com.example.demo.Domain.User;
import com.example.demo.Repositores.UserRepository;
import com.example.demo.Services.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        Optional<User> user = userRepository.findById(id);
       return user.orElseThrow(() -> new ObjectNotFoundException("Id nao encontrado!"));
    }
}
