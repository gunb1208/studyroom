package com.myproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.dao.MemberDao;
import com.myproject.domain.MemberDomain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
//@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

//    private final MemberDao memberDao;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//        MemberDomain memberDomain = memberDao.findById(id);
//        if (memberDomain == null){
//            throw new UsernameNotFoundException(id);
//        }
////        DB로 회원 정보
////        return User.builder()
////                .username(member.getEmail())
////                .password(member.getPassword())
////                .roles(member.getRole().toString())
////                .build();
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(memberDomain.getAuthList().toString()));
//
//    	log.info("id = "+id+", memberDomain = "+memberDomain);
//
//        return new CustomUserMember(memberDomain, authorities);
//    }
    
    
    @Autowired
	private MemberDao memberDao;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn(username);
		int userNo = memberDao.findUserNoBy(username);
		MemberDomain vo =  memberDao.read(userNo);
		//log.warn(!vo.isEnabled());
		/*if(!vo.isEnabled()) {
			return null;
		}*/
		log.warn("member mapper's vo :: "+ vo);
		//User user = new CustomUser(vo);
		//log.warn("user : " + user);
		//return vo == null ? null : user;
		return vo == null || !vo.isEnabled() ? null : new CustomUserMember(vo);
	}
}
