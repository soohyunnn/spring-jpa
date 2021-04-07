//package com.springboot.jpa;
//
//import com.springboot.jpa.account.*;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Component
//@Transactional
//public class JpaRunner implements ApplicationRunner {
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Post post = new Post();
//        post.setTitle("Spring");
//
//        Comment comment = new Comment();
//        comment.setComment("Hello");
//
//        postRepository.save(post);
//    }
//}
