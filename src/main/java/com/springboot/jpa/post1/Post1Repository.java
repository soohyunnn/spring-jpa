package com.springboot.jpa.post1;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Post1Repository extends JpaRepository<Post1, Long> {
    List<Post1> findByTitleStartsWith(String title);

    @Query("SELECT p FROM #{#entityName} AS p WHERE p.title=:title")
    List<Post1> findByTitle(@Param("title") String title, Sort sort);
}
