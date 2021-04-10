package com.springboot.jpa.comment;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    String getComment();

    int getUp();

    int getDown();

    default String getVotes() {
        return getUp() + " " + getDown();
    }

    //@Value("#{target.up + ' ' + target.down}")
    //String getVotes1();  //up과 down을 더해서 getVotes로 가져오겠다고 정의하는 것(Target은 Commnet인데 그래서 Comment 전부를 가져올 수 밖에 없습니다.)

}
