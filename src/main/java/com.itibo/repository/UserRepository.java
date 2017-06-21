package com.itibo.repository;

import com.itibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Blob;

/**
 * Created by Makovsky on 27.04.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.name = :name")
    User findByName(@Param("name") String name);

    @Modifying
    @Transactional(readOnly=false)
    @Query("update User u set u.password = :password where u.name = :name")
    void changePassword(@Param("name") String name, @Param("password") String newPassword);

    @Modifying
    @Transactional(readOnly=false)
    @Query("update User u set u.image = :image where u.name = :name")
    void saveImage(@Param("name") String name, @Param("image") Blob image);
}
