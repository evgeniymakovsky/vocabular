package com.itibo.repository;

import com.itibo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Makovsky on 27.04.2017.
 */

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
