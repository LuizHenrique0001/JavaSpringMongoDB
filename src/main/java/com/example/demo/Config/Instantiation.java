package com.example.demo.Config;

import com.example.demo.Domain.Post;
import com.example.demo.Domain.User;
import com.example.demo.Dto.AuthorDTO;
import com.example.demo.Dto.UserDTO;
import com.example.demo.Repositores.PostRepository;
import com.example.demo.Repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com", Instant.now(), true);
        User alex = new User(null, "Alex Green", "alex@gmail.com", Instant.now(), true);
        User bob = new User(null, "Bob Grey", "bob@gmail.com", Instant.now(), false);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post p1 = new Post(null, fmt.parse("25/06/2025"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post p2 = new Post(null, fmt.parse("24/06/2025"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
        Post p3 = new Post(null, fmt.parse("27/06/2025"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(bob));

        postRepository.saveAll(Arrays.asList(p1, p2, p3));

        maria.getPost().addAll(Arrays.asList(p1, p2));
        bob.getPost().add(p3);

        userRepository.saveAll(Arrays.asList(maria, bob));


    }
}
