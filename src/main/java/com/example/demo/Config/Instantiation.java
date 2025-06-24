package com.example.demo.Config;

import com.example.demo.Domain.User;
import com.example.demo.Repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;
@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com", Instant.now(), true);
        User alex = new User(null, "Alex Green", "alex@gmail.com", Instant.now(), true);
        User bob = new User(null, "Bob Grey", "bob@gmail.com", Instant.now(), false);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

    }
}
