package com.springboot.jpa.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void crud() {
        //postRepository.findMyPost();

        Post post = new Post();
        post.setTitle("hibernate");
        postRepository.save(post);

        postRepository.findMyPost();  //이걸 추가하면 INSERT와 DELETE가 실행된다.

        postRepository.delete(post);
    }
}
