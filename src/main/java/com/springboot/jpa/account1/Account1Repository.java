package com.springboot.jpa.account1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface Account1Repository extends JpaRepository<Account1, Long>, QuerydslPredicateExecutor<Account1> {
}
