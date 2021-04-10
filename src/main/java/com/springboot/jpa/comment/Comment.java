package com.springboot.jpa.comment;

import com.springboot.jpa.post1.Post1;

import javax.persistence.*;

@Entity
//@NamedEntityGraph(name = "Comment.Post1", attributeNodes = @NamedAttributeNode("post1"))
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne
    private Post1 post1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post1 getPost1() {
        return post1;
    }

    public void setPost1(Post1 post1) {
        this.post1 = post1;
    }
}
