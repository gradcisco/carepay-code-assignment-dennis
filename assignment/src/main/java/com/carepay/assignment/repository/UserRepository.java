package com.carepay.assignment.repository;

import com.carepay.assignment.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BlogUser, Long> {
    public Optional<BlogUser> findByUserName(String userName);
}
