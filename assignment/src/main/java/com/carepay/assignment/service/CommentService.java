package com.carepay.assignment.service;

import com.carepay.assignment.dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface CommentService {

    public void addComment(@RequestBody CommentDto commentDto);

    public ResponseEntity<HttpStatus> deleteComment();
}
