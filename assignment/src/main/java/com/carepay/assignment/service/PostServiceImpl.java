package com.carepay.assignment.service;


import com.carepay.assignment.domain.CreatePostRequest;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.domain.PostDetails;
import com.carepay.assignment.domain.PostInfo;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.repository.PostRepository;
import com.carepay.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostDetails createPost(@Valid CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setContent(createPostRequest.getContent());
        post.setTitle(createPostRequest.getTitle());

        Optional<BlogUser> user = userRepository.findByUserName(createPostRequest.getUsername());

        if(!user.isPresent())
            throw new RuntimeException("User " + createPostRequest.getUsername() + " does not exists");


       post.setBlogUser(user.get());

        postRepository.save(post);

        PostDetails postDetails = new PostDetails(post.getContent());

        return postDetails;

    }

    @Override
    public Page<PostInfo> getPosts(Pageable pageable) {
        throw new IllegalArgumentException("Not implemented"); // TODO: Implement
    }

    @Override
    public PostDetails getPostDetails(Long id) {
        throw new IllegalArgumentException("Not implemented"); // TODO: Implement
    }

    @Override
    public void deletePost(Long id) {
        throw new IllegalArgumentException("Not implemented"); // TODO: Implement
    }
}

