package com.springboot.jpa.comment;


import com.springboot.jpa.post1.Post1;
import com.springboot.jpa.post1.Post1Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
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
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findByPost1_Id(savePost.getId(), CommentSummary.class).forEach(c -> {
            System.out.println("=================CommentSummary=================");
            System.out.println(c.getVotes());
        });

        commentRepository.findByPost1_Id(savePost.getId(), CommentOnly.class).forEach(c -> {
            System.out.println("=================CommentOnly=================");
            System.out.println(c.getComment());
        });
    }

    @Test
    public void specs() {
        List<Comment> all = commentRepository.findAll(CommentSpecs.isBest());
        System.out.println("======================");
        commentRepository.findAll(CommentSpecs.isBest().or(CommentSpecs.isGood()));
        System.out.println("======================");
        commentRepository.findAll(CommentSpecs.isBest().or(CommentSpecs.isGood()), PageRequest.of(0, 10));
    }

}