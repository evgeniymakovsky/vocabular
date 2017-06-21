package com.itibo.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Makovsky on 19.04.2017.
 */

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "role_id",
            unique = true, nullable = false)
    private Integer role_id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "role", nullable = false)
    private String role;

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
