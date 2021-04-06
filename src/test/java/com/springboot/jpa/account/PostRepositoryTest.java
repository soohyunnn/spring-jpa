package com.springboot.jpa.account;

import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void crudRepository() {
        //Given
        Post post = new Post();
        post.setTitle("hello spring boot common");
        //Persistent상태라 이때는 ID가 NULL일 것입니다.
        assertThat(post.getId()).isNull();

        //When
        Post newPost = postRepository.save(post);

        //Then
        assertThat(newPost.getId()).isNotNull();

        //When
        List<Post> posts = postRepository.findAll();

        //Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.contains(newPost));

        //When
        Page<Post> page = postRepository.findAll(PageRequest.of(0,10));  //0 : page, 10: size
        assertThat(page.getTotalElements()).isEqualTo(1);  //전체의 갯수는 1개
        assertThat(page.getNumber()).isEqualTo(0);  //현재 페이지의 number
        assertThat(page.getSize()).isEqualTo(10);  //요청했던 사이즈 크기
        assertThat(page.getNumberOfElements()).isEqualTo(1);  //총 목록 수

        //When(PostRepository에 커스텀 메소드 추가한 후 findByTitleContains TEST)
        page = postRepository.findByTitleContains("spring", PageRequest.of(0,10));
        //Then
        assertThat(page.getTotalElements()).isEqualTo(1);  //전체의 갯수는 1개
        assertThat(page.getNumber()).isEqualTo(0);  //현재 페이지의 number
        assertThat(page.getSize()).isEqualTo(10);  //요청했던 사이즈 크기
        assertThat(page.getNumberOfElements()).isEqualTo(1);  //총 목록 수

        //When(PostRepository에 커스텀 메소드 추가한 후 countVyTitleContains TEST)
        long spring = postRepository.countByTitleContains("spring");
        //Then
        assertThat(spring).isEqualTo(1);

    }

}