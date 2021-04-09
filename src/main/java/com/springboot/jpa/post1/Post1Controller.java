package com.springboot.jpa.post1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
public class Post1Controller {

    @Autowired
    private Post1Repository post1Repository;

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id) {  //받는 파라미터 이름이랑 변수명이 같으면 @PathVariable에 따로 이름을 명시해주지 않아도 됩니다.
        Optional<Post1> byId = post1Repository.findById(id);
        Post1 post = byId.get();
        return post.getTitle();
    }

    @GetMapping("/posts")
    public PagedModel<EntityModel<Post1>> getPosts(Pageable pageable, PagedResourcesAssembler<Post1> assembler) {
        return assembler.toModel(post1Repository.findAll(pageable));
    }
}
