package com.springboot.jpa.post;

import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post extends AbstractAggregateRoot<Post> {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    //Publish 하는 도메인 생성 => save 할 때 이벤트를 만들어서 넣기만 하면 된다. publishing을 하는 일을 만들어야 하기 때문에 생성
    public Post publish() {
        this.registerEvent(new PostPublishedEvent(this));  //이벤트를 만들어서 넣어주면 끝!!(완전 간단)
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}