package com.springboot.jpa.book;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // => entityInformation 빨간줄 없애기 위한 것
public class CustomRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    private EntityManager entityManager;

    //JpaRepository에 대한 구현체는 SimpleJpaRepository가 구현하기 때문에 따로 구현할 필요는 없음.. => 대신 생성자를 구현해야 함.
    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public boolean contains(T entity) {
        return entityManager.contains(entity);
    }
}


