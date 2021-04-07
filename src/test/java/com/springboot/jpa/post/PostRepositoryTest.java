package com.springboot.jpa.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

        assertThat(postRepository.contains(post)).isFalse();  //Transient 상태

        postRepository.save(post);

        assertThat(postRepository.contains(post)).isTrue();  //저장을 하고 나면 Persistent 상태로 변경

        postRepository.delete(post);
        postRepository.flush();
    }
}