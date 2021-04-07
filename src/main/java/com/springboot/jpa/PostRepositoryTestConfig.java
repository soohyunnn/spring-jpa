package com.springboot.jpa;

import com.springboot.jpa.post.PostListener;
import com.springboot.jpa.post.PostPublishedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

    @Bean
    public ApplicationListener<PostPublishedEvent> postListener() {
        return new ApplicationListener<PostPublishedEvent>() {
            @Override
            public void onApplicationEvent(PostPublishedEvent event) {
                System.out.println("==========================");
                System.out.println(event.getPost().getTitle() + " is published!!");
                System.out.println("==========================");
            }
        };
    }
}
