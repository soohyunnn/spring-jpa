package com.springboot.jpa.post1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Post1RepositoryTest {

    @Autowired
    private Post1Repository post1Repository;

    @Test
    public void crud() {
        Post1 post1 = new Post1();
        post1.setTitle("jpa");
        post1Repository.save(post1);

        List<Post1> all = post1Repository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

}