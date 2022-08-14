package com.carepay.assignment.service;


import com.carepay.assignment.domain.CreatePostRequest;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.domain.PostDetails;
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

        PostDetails postDetails = new PostDetails();
        postDetails.setId(post.getId());
        postDetails.setContent(post.getContent());
        postDetails.setCreatedBy(post.getBlogUser().getUserName());
        postDetails.setCreationDate(post.getCreationDate());

        return postDetails;

    }

    @Override
    public Page<Post> getPosts(Pageable pageable) {

        return postRepository.findAll(pageable);
    }

    @Override
    public PostDetails getPostDetails(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        PostDetails pd = new PostDetails();
        pd.setId(post.getId());
        pd.setContent(post.getContent());
        pd.setCreatedBy(post.getBlogUser().getUserName());
        pd.setCreationDate(post.getCreationDate());

        return pd;
    }

    @Override
    public void deletePost(Long id,String principal) throws Exception {
        Post post = postRepository.findById(id).orElseThrow();
        if(!post.getBlogUser().getUserName().equalsIgnoreCase(principal))
            throw new Exception("Cannot delete a post by another user");
        postRepository.deleteById(id);

    }

    @Override
    public PostDetails updatePost(CreatePostRequest createPostRequest, String principal,Long id) throws Exception {
        Post post = postRepository.findById(id).orElseThrow();

        if(!post.getBlogUser().getUserName().equalsIgnoreCase(principal))
            throw new Exception("Cannot update a post by another user");

        post.setContent(createPostRequest.getContent());
        post.setTitle(createPostRequest.getTitle());
        post = postRepository.save(post);

        PostDetails postDetails = new PostDetails();
        postDetails.setId(post.getId());
        postDetails.setContent(post.getContent());
        postDetails.setCreatedBy(post.getBlogUser().getUserName());
        postDetails.setCreationDate(post.getCreationDate());

        return postDetails;
    }

    @Override
    public List<Post> getPostDetailsByUser(String postedby) {

        BlogUser user = userRepository.findByUserName(postedby).orElseThrow();

        return postRepository.findByBlogUserId(user.getId());
    }
}

