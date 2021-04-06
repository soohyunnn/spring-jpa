package com.springboot.jpa.account;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends MyRepository<Comment, Long>{

    //=> 2. USE_DECLATED_QUERY 방법으로 미리 정의되어 있는 쿼리를 찾아서 정의(이 방식은 JPQL 방식)
    //@Query("SELECT c FROM Comment AS c")
    //=> 2번 방식을 nativeQuery로 정의(SQL)
    //@Query(value = "SELECT * FROM Comment", nativeQuery = true)
    //title 키워드가 들어있는 모든 comment를 찾는 메소드 정의 => 1. CREATE 방법으로 메소드 이름을 분석해서 직접 쿼리를 만드는 방법
    List<Comment> findByCommentContains(String title);

    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

}
