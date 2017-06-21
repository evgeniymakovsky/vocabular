package com.itibo.repository;

import com.itibo.entity.User;
import com.itibo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Makovsky on 27.04.2017.
 */
public interface WordRepository extends JpaRepository<Word, Integer> {
    @Modifying
    @Transactional(readOnly=false)
    @Query("update Word u set u.repeated = :repeated, u.score = :score where u.id = :id")
    void alterStatistics(@Param("id") Integer id, @Param("repeated") Integer repeated, @Param("score") Integer score);
}
