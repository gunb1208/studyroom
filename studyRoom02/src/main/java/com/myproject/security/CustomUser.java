//package com.myproject.security;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import com.myproject.domain.AuthDomain;
//import com.myproject.domain.MemberDomain;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import lombok.extern.log4j.Log4j2;
//
//@Getter
//@Setter
//@ToString
//@Log4j2
//public class CustomUser extends User{
//
//	private static final long serialVersionUID = 1L;
//	private MemberDomain vo;
//	
//	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, authorities);
//	}
//	
//	public CustomUser(MemberDomain vo){
//		//super(vo.getUserid(), vo.getUserpw(), getList(vo));
//		super(vo.getUserId(), vo.getUserPw(), vo.getAuthList().stream().map(
//				a -> {
//					log.info(a);
//					log.info(a.getAuth());
//				return new SimpleGrantedAuthority(a.getAuth());
//				}).collect(Collectors.toList()));
//			this.vo = vo;
//		}
//	
//	public static List<GrantedAuthority> getList(MemberDomain vo){
//		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//		List<AuthDomain> authList = vo.getAuthList();
//		for(int i = 0; i <authList.size(); i++){
//			String auth = authList.get(i).getAuth();
//			GrantedAuthority ga = new SimpleGrantedAuthority(auth);
//			list.add(ga);
//		}
//		return list;
//	}
//
//}
