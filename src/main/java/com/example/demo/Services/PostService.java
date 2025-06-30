package com.example.demo.Services;

import com.example.demo.Domain.Post;

import com.example.demo.Repositores.PostRepository;

import com.example.demo.Services.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String id){
        Optional<Post> Post = postRepository.findById(id);
       return Post.orElseThrow(() -> new ObjectNotFoundException("Id nao encontrado!"));
    }

    public Post save(Post Post){
       return postRepository.insert(Post);
    }

    public void delete(String id){
        findById(id);
        postRepository.deleteById(id);
    }

    public void update(String id, Post Post){
        Post obj = findById(id);
        obj.setTitle(Post.getTitle());
        obj.setBody(Post.getBody());
        postRepository.save(obj);
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
