package com.carepay.assignment.domain;

import com.carepay.assignment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDetails extends PostInfo {
    private Long id;
    private String content;
    private String createdBy;
    private Date creationDate;
}
