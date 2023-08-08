package com.myproject.config;

//import static org.springframework.security.config.Customizer.withDefaults;

import com.myproject.security.CustomUserDetailsService;
import com.myproject.security.handler.Custom403Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)// 메소드 실행전에 권한검사
public class SecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService customUserDetailsService;


    //    로그인과정 생략: 개발지 직접 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("----- configure -----");

//        Userdetails 객체 처리후 로그인 처리할 것
//        로그인/로그아웃관련 설정
        http.formLogin().loginPage("/member/login")// 사용자가 설정한 로그인페이지 이동
                .defaultSuccessUrl("/", true)// 로그인 성공시 이동할 페이지 경로
                .usernameParameter("username")// 로그인시 사용할 이름
                .failureUrl("/member/login/error")// 로그인 실패시 이동경로
                .and()
                .logout()// 로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))// 로그아웃 경로
                .logoutSuccessUrl("/index") // 로그아웃 성공시 이동 경로
        ;

//        CSRF 요청 비활성화
        http.csrf().disable();
        //http.csrf();

//    인증되지 않는 사용자 리소스 요청할 경우 에러처리
        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

//        인가(권한에 대한 접근) 과정 처리: "/js/**", "/assets", "/css/**", "/bootstrap/**", "/upload/**", "/imgs/**"

//        http.authorizeRequests().mvcMatchers(/*"/board/**",*/ "/members/**", "/board/**", "/item/**",
//                        "/",
//                        "/js/**", "/assets/**", "/css/**", "/bootstrap/**", "/upload/**", "/bootstrap/**").permitAll()
//                                .mvcMatchers("/admin/**").hasRole("ADMIN")
//                                .anyRequest()// 인증과정 요청
//                                .authenticated();// 리소스 접근 경로 설정외에는 모두 인증을 요구하도록 설정

        http.formLogin().loginPage("/member/login");

        // 403에러처리
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        // 자동 로그인 처리
        http.rememberMe()
                .key("12345678")// 키발급
                .tokenRepository(persistentTokenRepository())// 토큰 생성
                .userDetailsService(customUserDetailsService)// 시큐리티 로그인 정보
                .tokenValiditySeconds(60*60*24*30);// 유효기간 30일


        return http.build();
    }



    //    해시코드로 암호화 처리
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    //     정적자원들은 스프링시큐리티 적용에서 제외
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("----- web configure -----");

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //     접근 권한 거부 예외발생 처리
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }

    //    자동로그인 (로그인정보:UserDtails와 DB유지)
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return  repo;
    }
	
	/*
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .antMatchers("/",
                        		"/member/join",
                        		"/member/login",
                                "/resources/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/member/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("userid")
                        .passwordParameter("pw")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                )
                .logout(withDefaults());

        return http.build();
    }
    */

}
