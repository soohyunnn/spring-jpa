package com.springboot.jpa.comment;


import com.springboot.jpa.post1.Post1;
import com.springboot.jpa.post1.Post1Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findByPost1_Id(1L).forEach(c -> {
            System.out.println("=================");
            System.out.println(c.getVotes());
        });
    }

}