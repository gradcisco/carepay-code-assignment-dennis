package com.carepay.assignment.model;

import com.carepay.assignment.domain.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@ToString
@Entity
public class BlogUser {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "username cannot be blank")
    @Column(unique = true)
    private String userName;

    @JsonIgnore
    @NotBlank(message = "Password cannot be blank")
    private String userPassword;

    @Column(name = "enabled")
    private boolean isActive=true;



    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_date", nullable = false, updatable = true)
    private Date lastLoginDate;

}
