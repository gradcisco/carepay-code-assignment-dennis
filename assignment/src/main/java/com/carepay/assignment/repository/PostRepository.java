package com.carepay.assignment.repository;


import com.carepay.assignment.domain.Post;
import com.carepay.assignment.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public Optional<Post> findById(Long id);

    List<Post> findByBlogUserId(Long id);
}
