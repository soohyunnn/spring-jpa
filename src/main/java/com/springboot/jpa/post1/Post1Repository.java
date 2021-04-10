package com.springboot.jpa.post1;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Post1Repository extends JpaRepository<Post1, Long> {
    List<Post1> findByTitleStartsWith(String title);

    @Query("SELECT p FROM Post1 p WHERE p.title=?1")
    List<Post1> findByTitle(String title, Sort sort);
}
