package com.springboot.jpa.comment;

import com.springboot.jpa.Account;
import com.springboot.jpa.post1.Post1;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post1 post1;

//    private int up;
//
//    private int down;
//
//    private boolean best;

    @CreatedDate
    private Date created;

    @CreatedBy
    @ManyToOne
    private Account createdBy;

    @LastModifiedDate
    private Date updated;

    @LastModifiedBy
    @ManyToOne
    private Account updatedBy;

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

//    public int getUp() {
//        return up;
//    }
//
//    public void setUp(int up) {
//        this.up = up;
//    }
//
//    public int getDown() {
//        return down;
//    }
//
//    public void setDown(int down) {
//        this.down = down;
//    }
//
//    public boolean isBest() {
//        return best;
//    }
//
//    public void setBest(boolean bset) {
//        this.best = bset;
//    }

    @PrePersist
    public void prePersist() {
        System.out.println("Pre Persist is called");
    }
}
