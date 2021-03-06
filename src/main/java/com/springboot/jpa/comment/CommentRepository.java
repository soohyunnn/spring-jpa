package com.springboot.jpa.comment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> , JpaSpecificationExecutor<Comment> , QueryByExampleExecutor<Comment> {

     //@EntityGraph(value = "Comment.post1")  //설정한 건(post1) EAGER로 나머지는(id, comment)는 LAZY로 가져옵니다.
     //Optional<Comment> getById(Long id);

      @EntityGraph(attributePaths = "post1")  //설정한 건(post1) EAGER로 나머지는(id, comment)는 LAZY로 가져옵니다.
      Optional<Comment> getById(Long id);

      <T> List<T> findByPost1_Id(Long id , Class<T> type);

}
