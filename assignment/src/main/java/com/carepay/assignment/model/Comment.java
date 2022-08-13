package com.carepay.assignment.model;

import com.carepay.assignment.domain.Post;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contents;

    @ManyToOne
    private Post post;

    @OneToOne
    private BlogUser user;

}
