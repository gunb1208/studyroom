package com.myproject.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.myproject.domain.MemberDomain;

public class CustomUserMember extends User {
	
    private final String id;
    private final String password;

    // member.getId : 유니크 유일성 보장
    public CustomUserMember(MemberDomain member, List<GrantedAuthority> authorities){
        super(member.getUserId(), member.getUserPw(), authorities);

        // User 객체가 가지는 username과 passwerd 이외에 id, getAddress, password 추가
        this.id = member.getUserId();
        this.password = member.getUserPw();
    }
}
