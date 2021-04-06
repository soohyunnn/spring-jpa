package com.springboot.jpa.account;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface CommonRepository {

    //필요한 기능은 직접 구현(다른 필요한 기능을 가져오는 것이 맘에 안들경우에 사용!)
    Comment save(Comment commnet);

    List<Comment> findAll();
}
