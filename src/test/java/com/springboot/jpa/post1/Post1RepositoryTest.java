package com.springboot.jpa.post1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.postgresql.jdbc.EscapedFunctions.LENGTH;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Post1RepositoryTest {

    @Autowired
    private Post1Repository post1Repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void save() {
        Post1 post1 = new Post1();
        post1.setTitle("jpa");
        Post1 savePost = post1Repository.save(post1);  //Post1 엔티티가 추가(INSERT) => .persist() 호출(ID가 없기 때문에)

        //persist 되는 순간 post1가 영속화가
        assertThat(entityManager.contains(post1)).isTrue();  //post == savePost => TRUE됨
        assertThat(entityManager.contains(savePost)).isTrue();
        assertThat(post1 == savePost);

        Post1 postUpdate = new Post1();
        postUpdate.setId(1L);  //=> ID를 추가하면 .merge()를 사용
        postUpdate.setTitle("hibernate");
        Post1 updatePost = post1Repository.save(postUpdate);  //UPDATE => .merge()를 호출(ID가 있기 때문에), Merge할 경우 JPA대신 하이버네이트로 값을 업데이트 하게됨

        //merge를 하고 나서 복사한 updatePost만 영속화를 함
        assertThat(entityManager.contains(postUpdate)).isFalse();  //FALSE
        assertThat(entityManager.contains(updatePost)).isTrue();  //TRUE
        assertThat(postUpdate == updatePost);  //이 2개는 같지 않음

        List<Post1> all = post1Repository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith() {
        Post1 post1 = new Post1();
        post1.setTitle("spring Data Jpa");
        post1Repository.save(post1);  //Post1 엔티티가 추가(INSERT) -> .persist() 호출(ID가 없기 때문에)

        List<Post1> all = post1Repository.findByTitleStartsWith("spring");
        assertThat(all.size()).isEqualTo(1);
    }

    private void savePost() {
        Post1 post1 = new Post1();
        post1.setTitle("Spring");
        post1Repository.save(post1);
    }

    @Test
    public void findByTitle() {
        savePost();

        String title = "title";
        //List<Post1> all = post1Repository.findByTitle("Spring", Sort.by("title"));
        List<Post1> all = post1Repository.findByTitle("Spring", JpaSort.unsafe(LENGTH(title)));
        assertThat(all.size()).isEqualTo(1);
    }

    private String LENGTH(String title) {
        return title;
    }

}