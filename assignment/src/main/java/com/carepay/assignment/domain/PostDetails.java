package com.carepay.assignment.domain;

import com.carepay.assignment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDetails extends PostInfo {
    private String content;
}
