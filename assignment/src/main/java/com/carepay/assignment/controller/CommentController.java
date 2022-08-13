package com.carepay.assignment.controller;

import com.carepay.assignment.dto.CommentDto;
import com.carepay.assignment.model.Comment;
import com.carepay.assignment.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<HttpStatus> addComment(@RequestBody CommentDto commentDto){

        commentService.addComment(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteComment(){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
