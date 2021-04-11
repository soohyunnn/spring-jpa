package com.springboot.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  //빈으로 등록
public class AccountAuditAware implements AuditorAware<Account> {
    @Override
    public Optional<Account> getCurrentAuditor() {

        System.out.println("looking for current user");
        return Optional.empty();
        //스프링 시큐리티 사용 방법
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if(authentication == null || !authentication.isAuthenticated()){
//            return Optional.empty();
//        }
//
//        return authentication.getPrincipal().getUser();
    }
}
