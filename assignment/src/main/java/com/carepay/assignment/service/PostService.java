package com.carepay.assignment.service;


import com.carepay.assignment.domain.CreatePostRequest;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.domain.PostDetails;
import com.carepay.assignment.domain.PostInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

public interface PostService {
    PostDetails createPost(@Valid CreatePostRequest createPostRequest);

    Page<Post> getPosts(final Pageable pageable);

    PostDetails getPostDetails(Long id);

    void deletePost(Long id);
}
