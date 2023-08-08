package com.myproject.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.dao.MemberDao;
import com.myproject.domain.MemberDomain;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDao memberDao;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberDomain memberDomain = memberDao.findById(id);
        if (memberDomain == null){
            throw new UsernameNotFoundException(id);
        }
//        DB로 회원 정보
//        return User.builder()
//                .username(member.getEmail())
//                .password(member.getPassword())
//                .roles(member.getRole().toString())
//                .build();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(memberDomain.getAuthList().toString()));

    	log.info("id = "+id+", memberDomain = "+memberDomain);

        return new CustomUserMember(memberDomain, authorities);
    }
}
