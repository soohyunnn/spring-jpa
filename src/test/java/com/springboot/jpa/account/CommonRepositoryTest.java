package com.springboot.jpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommonRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setComment("Hello Comment");
        commentRepository.save(comment);

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(1);

        long count = commentRepository.count();
        assertThat(count).isEqualTo(1);

        //이부분 추가
        Optional<Comment> byId = commentRepository.findById(10L);
        assertThat(byId).isEmpty();  //null은 아니라 isEmpty()로 확인해야 합니다. isEmpty()는 Optional을 체크하는 것입니다.
        //Comment comment1 = byId.orElse(new Comment());  //값이 없을 경우 대체를 설정하는 것
        Comment comment1 = byId.orElseThrow(IllegalAccessError::new);
    }

}