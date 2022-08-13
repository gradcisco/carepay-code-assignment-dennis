package com.carepay.assignment.model;

import com.carepay.assignment.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_seq", initialValue = 1, allocationSize = 1)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
    private Long id;
    private String comment;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_date", nullable = false, updatable = false)
    private Date creationDate;

    @ManyToOne
    private Post post;

    @OneToOne
    private BlogUser user;

}
