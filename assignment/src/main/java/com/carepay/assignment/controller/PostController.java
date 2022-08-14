package com.carepay.assignment.controller;

import javax.validation.Valid;
import javax.ws.rs.PathParam;

import com.carepay.assignment.domain.CreatePostRequest;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.domain.PostDetails;
import com.carepay.assignment.jwtutils.JwtFilter;
import com.carepay.assignment.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private JwtFilter jwtFilter;

    @GetMapping
    Page<Post> getPosts(Pageable pageable) {

        return postServiceImpl.getPosts(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PostDetails createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        return postServiceImpl.createPost(createPostRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PostDetails updatePost(@Valid @RequestBody CreatePostRequest createPostRequest,@PathVariable Long id) throws Exception {
        return postServiceImpl.updatePost(createPostRequest,jwtFilter.getPrincipal(),id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    PostDetails getPostDetails(@PathVariable("id") final Long id) {

        return postServiceImpl.getPostDetails(id);
    }

    @GetMapping("/user/{postedby}")
    @ResponseStatus(HttpStatus.OK)
    List<Post> getAllPostsByUser(@PathVariable String postedby) {

        return postServiceImpl.getPostDetailsByUser(postedby);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePost(@PathVariable("id") final Long id) throws Exception {

        postServiceImpl.deletePost(id,jwtFilter.getPrincipal());
    }
}
