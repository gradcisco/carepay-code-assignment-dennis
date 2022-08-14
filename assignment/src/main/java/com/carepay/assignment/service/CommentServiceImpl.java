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

import java.util.List;
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
    public Comment addComment(CommentDto commentDto) {

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

        comment = commentRepository.save(comment);

        return comment;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteComment() {
        return null;
    }

    @Override
    public List<Comment> getCommentsByPostId(Long id) {
        return commentRepository.findByPostId(id);
    }

    @Override
    public void deleteComment(Long id, String principal) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(!comment.getUser().getUserName().equalsIgnoreCase(principal))
            throw new Exception("Cannot update comment by another user");

        commentRepository.deleteById(id);
    }

    @Override
    public Comment updateComment(CommentDto commentDto, Long id, String principal) throws Exception {
        //get comment with id
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(!comment.getUser().getUserName().equalsIgnoreCase(principal))
            throw new Exception("Cannot update comment by another user");

        comment.setComment(commentDto.getMessage());

        return commentRepository.save(comment);
    }
}
