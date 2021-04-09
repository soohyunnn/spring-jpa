package com.springboot.jpa.post1;

import com.springboot.jpa.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Post1Repository extends JpaRepository<Post1, Long> {
    List<Post1> findByTitleStartsWith(String title);
}
