package com.springboot.jpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommonRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        this.createComment(100, "spring data jpa");
        this.createComment(55, "HIBERNATE SPRING");

        //List<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("Spring");
        //assertThat(comments.size()).isEqualTo(1);

        //예제 1. keyword가 string인 comment 중 likeCount가 10보다 큰 것 조회
        //List<Comment> comments1 = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring", 10);
        //assertThat(comments1.size()).isEqualTo(1);

        //예제 2. keyword가 string인 comment 중 likeCount가 높은 순 조회
        //List<Comment> comments2 = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("spring");
        //assertThat(comments2.size()).isEqualTo(2);
        //assertThat(comments2).first().hasFieldOrPropertyWithValue("likeCount", 100);

        //예제 3. keyword가 string인 comment 목록을 페이징 처리하여 조회
        //PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
        //Page<Comment> comments3 = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);
        //assertThat(comments3.getNumberOfElements()).isEqualTo(2);
        //assertThat(comments3).first().hasFieldOrPropertyWithValue("likeCount", 100);

        //예제 4. keyword가 string인 comment 목록을 페이징 처리하여 조회 => Stream로 반환
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
        try(Stream<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest)){
            Comment firstComment = comments.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }

    }

    private void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setLikeCount(likeCount);
        newComment.setComment(comment);
        commentRepository.save(newComment);
    }

}