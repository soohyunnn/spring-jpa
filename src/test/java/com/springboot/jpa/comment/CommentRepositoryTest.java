package com.springboot.jpa.comment;


import com.springboot.jpa.AccountAuditAware;
import com.springboot.jpa.post1.Post1;
import com.springboot.jpa.post1.Post1Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Post1Repository post1Repository;

    @Test
    public void getComment() {
        Post1 post1 = new Post1();
        post1.setTitle("jpa");
        Post1 savePost = post1Repository.save(post1);

        Comment comment = new Comment();
        comment.setPost1(savePost);
        comment.setComment("인강은 즐거워");
        //comment.setUp(10);
        //comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findAll();

//        commentRepository.findByPost1_Id(savePost.getId(), CommentSummary.class).forEach(c -> {
//            System.out.println("=================CommentSummary=================");
//            System.out.println(c.getVotes());
//        });
//
//        commentRepository.findByPost1_Id(savePost.getId(), CommentOnly.class).forEach(c -> {
//            System.out.println("=================CommentOnly=================");
//            System.out.println(c.getComment());
//        });
    }

//    @Test
//    public void specs() {
//        List<Comment> all = commentRepository.findAll(CommentSpecs.isBest());
//        System.out.println("======================");
//        commentRepository.findAll(CommentSpecs.isBest().or(CommentSpecs.isGood()));
//        System.out.println("======================");
//        commentRepository.findAll(CommentSpecs.isBest().or(CommentSpecs.isGood()), PageRequest.of(0, 10));
//    }
//
//    @Test
//    public void qbe() {
//        Comment prove = new Comment();
//        prove.setBest(true);
//
//        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
//                .withIgnorePaths("up", "down");  // 이 부분이 필드 이름을 작성할 필요가 없다는 부분에서 거짓이 증명됩니다. (up, down 이라는 필드명을 작성해서 제외를 시켜주는 부분입니다.)
//        Example<Comment> example = Example.of(prove, exampleMatcher);
//
//        commentRepository.findAll(example);
   // }

}