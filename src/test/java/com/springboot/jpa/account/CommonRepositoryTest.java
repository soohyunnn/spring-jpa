//package com.springboot.jpa.account;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class CommonRepositoryTest {
//
//    @Autowired
//    CommentRepository commentRepository;
//
//    @Test
//    public void crud() throws ExecutionException, InterruptedException {
//        this.createComment(100, "spring data jpa");
//        this.createComment(55, "HIBERNATE SPRING");
//
//        //List<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("Spring");
//        //assertThat(comments.size()).isEqualTo(1);
//
//        //예제 1. keyword가 string인 comment 중 likeCount가 10보다 큰 것 조회
//        //List<Comment> comments1 = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("spring", 10);
//        //assertThat(comments1.size()).isEqualTo(1);
//
//        //예제 2. keyword가 string인 comment 중 likeCount가 높은 순 조회
//        //List<Comment> comments2 = commentRepository.findByCommentContainsIgnoreCaseOrderByLikeCountDesc("spring");
//        //assertThat(comments2.size()).isEqualTo(2);
//        //assertThat(comments2).first().hasFieldOrPropertyWithValue("likeCount", 100);
//
//        //예제 3. keyword가 string인 comment 목록을 페이징 처리하여 조회
//        //PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
//        //Page<Comment> comments3 = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest);
//        //assertThat(comments3.getNumberOfElements()).isEqualTo(2);
//        //assertThat(comments3).first().hasFieldOrPropertyWithValue("likeCount", 100);
//
//        //예제 4. keyword가 string인 comment 목록을 페이징 처리하여 조회 => Stream로 반환
////        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likeCount"));
////        try(Stream<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest)){
////            Comment firstComment = comments.findFirst().get();
////            assertThat(firstComment.getLikeCount()).isEqualTo(100);
////        }
//
//        //예제 5. 비동기 쿼리 -> 해당 메소드를 호출해서 실행하는 것을 별도의 Thread에게 위힘하는 것입니다.
//        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "likeCount"));
//
//        Future<List<Comment>> future = commentRepository.findByCommentContainsIgnoreCase("spring", pageRequest); //noneblocking call
//        System.out.println("=======================");
//        System.out.println("is done?" + future.isDone());  //결과가 나왔는지 안나왔는지 확인.
//
//        List<Comment> comments = future.get();  //결과가 나올때까지 대기. (다른 get()도 존재, 정해진 시간만큼 기다려주는 설정이 추가) => get을 호출할 때 blocking이 됩니다.
//        comments.forEach(System.out::println);
//
//    }
//
//    private void createComment(int likeCount, String comment) {
//        Comment newComment = new Comment();
//        newComment.setLikeCount(likeCount);
//        newComment.setComment(comment);
//        commentRepository.save(newComment);
//    }
//
//}