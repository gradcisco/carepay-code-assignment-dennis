package com.carepay.assignment.service;

import com.carepay.assignment.dto.CommentDto;
import com.carepay.assignment.model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface CommentService {

    public void addComment(@RequestBody CommentDto commentDto);

    public ResponseEntity<HttpStatus> deleteComment();

    public List<Comment> getCommentsByPostId(Long id);
}
