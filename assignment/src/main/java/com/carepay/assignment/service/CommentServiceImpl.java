package com.carepay.assignment.service;

import com.carepay.assignment.domain.Post;
import com.carepay.assignment.dto.CommentDto;
import com.carepay.assignment.model.BlogUser;
import com.carepay.assignment.model.Comment;
import com.carepay.assignment.repository.CommentRepository;
import com.carepay.assignment.repository.PostRepository;
import com.carepay.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void addComment(CommentDto commentDto) {

        //get user
        Optional<BlogUser> blogUser = userRepository.findByUserName(commentDto.getUserName());

        if(!blogUser.isPresent())
            throw new RuntimeException("User with username " + commentDto.getUserName() + " does not exist");

        //get post
        Optional<Post> post = postRepository.findById(commentDto.getPostId());

        if(!post.isPresent())
            throw new RuntimeException("Post with id " + commentDto.getPostId() + " does not exist");

        Comment comment = new Comment();
        comment.setComment(commentDto.getMessage());
        comment.setUser(blogUser.get());
        comment.setPost(post.get());

        commentRepository.save(comment);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteComment() {
        return null;
    }
}
