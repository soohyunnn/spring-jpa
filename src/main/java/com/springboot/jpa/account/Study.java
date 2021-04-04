package com.springboot.jpa.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Study {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //1사람이 여러개의 스터디를 만들 수 있음 => 스터디입장에서는 ManyToOne(Account가 Collection이 아니기 때문에 One으로 설정)
//    @ManyToOne
//    private Account owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Account getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Account owner) {
//        this.owner = owner;
//    }
}
