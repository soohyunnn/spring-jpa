package com.springboot.jpa.account1;

import com.querydsl.core.types.Predicate;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class Account1RepositoryTest {

    @Autowired
    Account1Repository account1Repository;

    @Test
    public void crud() {
        QAccount1 account1 = QAccount1.account1;
        Predicate predicate = account1.firstName.containsIgnoreCase("soohyun")
                .and(account1.lastName.startsWith("baik"));

        Optional<Account1> one = account1Repository.findOne(predicate);
        assertThat(one).isEmpty();  //Optoinal은 null으로 반환될 일은 없습니다.(null이면 반값으로 반환)

    }
}
