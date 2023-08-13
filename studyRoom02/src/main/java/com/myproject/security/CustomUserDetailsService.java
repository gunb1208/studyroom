//package com.myproject.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.myproject.dao.MemberDao;
//import com.myproject.domain.MemberDomain;
//
//import lombok.Data;
//import lombok.extern.log4j.Log4j2;
//
//
//@Log4j2
//@Data
//public class CustomUserDetailsService implements UserDetailsService{
//	@Autowired
//	private MemberDao memberMapper;
//
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		log.warn(username);
//		int userNo = memberMapper.findUserNoBy(username);
//		MemberDomain vo =  memberMapper.read(userNo);
//		//log.warn(!vo.isEnabled());
//		/*if(!vo.isEnabled()) {
//			return null;
//		}*/
//		log.warn("member mapper's vo :: "+ vo);
//		//User user = new CustomUser(vo);
//		//log.warn("user : " + user);
//		//return vo == null ? null : user;
//		return vo == null || !vo.isEnabled() ? null : new CustomUser(vo);
//	}
//}