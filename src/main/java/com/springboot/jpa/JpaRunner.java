package com.springboot.jpa;

import com.springboot.jpa.account.Account;
import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("soohyun");
        account.setPassword("123");

        //영속성 -> DB에 저장을 의미
        entityManager.persist(account);


        //JPA는 하이버네이트를 사용하기 때문에 하이버네이트 API를 사용할 수 있습니다.
        //하이버네이트의 가장 핵심적인 API는 session 입니다.
        //Session session = entityManager.unwrap(Session.class);
        //session.save(account);
    }
}