package com.carepay.assignment.controller;

import com.carepay.assignment.dto.CommentDto;
import com.carepay.assignment.jwtutils.JwtFilter;
import com.carepay.assignment.model.Comment;
import com.carepay.assignment.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private JwtFilter jwtFilter;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.addComment(commentDto),HttpStatus.CREATED);
    }

    @PutMapping("{commentid}")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentDto commentDto,@PathVariable Long commentid) throws Exception {

        return new ResponseEntity<>(commentService.updateComment(commentDto,commentid,jwtFilter.getPrincipal()),HttpStatus.CREATED);
    }
    @GetMapping("/{postid}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postid){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postid),HttpStatus.CREATED);
    }

    @DeleteMapping("{commentid")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long commentid) throws Exception {
        commentService.deleteComment(commentid,jwtFilter.getPrincipal());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
