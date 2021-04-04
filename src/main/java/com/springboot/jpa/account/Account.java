package com.springboot.jpa.account;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Transient
    private String no;

    //아래 어노테이션을 생략하면 street으로 필드명이 생성됨.
    //@AttributeOverrides 사용안할 경우 => city | state | street | zip_code
    //@AttributeOverrides 사용할 경우 => city | state | home_street | zip_cod
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address address;

    //한 명은 여러개의 study를 할 수 있기 때문에 Account 입장에서 OneToMany
    //양방향으로 정의하려면 Study쪽에서 Account를 정의한 필드명을 mapperBy로 지정해주어야 연결됨.
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    //추가
    public void addStudy(Study study) {
        this.getStudies().add(study);  //이거는 Account에 관계를 설정하는 것
        study.setOwner(this);  //Study에 관계를 설정하는 것
    }

    //삭제
    public void removeStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
    }
}
