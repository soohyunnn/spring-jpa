package com.springboot.jpa.post1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import javax.swing.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Post1ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Post1Repository post1Repository;

    @Test
    public void getPost() throws Exception {
        Post1 post1 = new Post1();
        post1.setTitle("jpa");
        post1Repository.save(post1);

        mockMvc.perform(get("/posts/" + post1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
    }

    @Test
    public void getPosts() throws Exception {
        Post1 post1 = new Post1();
        post1.setTitle("jpa");
        post1Repository.save(post1);

        mockMvc.perform(get("/posts")
                .param("page", "0")
                .param("size", "10")
                //.param("sort", "created.desc")
                .param("sort", "title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {

                    jsonPath("$.content[0].title", is("jpa"));

                });


    }

}