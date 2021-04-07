package com.springboot.jpa.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface MyRepository <T, ID extends Serializable> extends JpaRepository<T, ID> {

    //추가하고 싶은 기능 정의(어떤 entity가 persistentContext에 들어있는지 없는지 확인하는 메소드)
    boolean contains(T entity);
}
