package com.htpe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Security配置類
 *
 */
@Configuration
@EnableWebSecurity
public class CsrSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    
    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    
    @Autowired
    MyLogoutHandler myLogoutHandler;
    
    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;
    
    @Autowired
    MyInvalidSessionStrategy myInvalidSessionStrategy;
    
    @Autowired
    MyExpiredSessionStrategy myExpiredSessionStrategy;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");       //加密
        auth                                     //認證帳密，若無配置configure方法，瀏覽任URL都需密碼
                .inMemoryAuthentication()
                .withUser("admin")
                .password(password)
                .roles("VIP1", "VIP2");*/

        auth.userDetailsService(userDetailsService).passwordEncoder(passowrd());
    }

    @Bean														//Security中加密用
    PasswordEncoder passowrd(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	

				
		http.formLogin().permitAll()	
			.successHandler(myAuthenticationSuccessHandler)		//登入成功處理
			.failureHandler(myAuthenticationFailureHandler)		//登入失敗處理		
		.and().exceptionHandling()
	 		.authenticationEntryPoint(myAuthenticationEntryPoint)		//無權限處理
		 	.accessDeniedHandler(myAccessDeniedHandler);				//權限不足處理
		 	
					
		http.authorizeRequests()
				.anyRequest().authenticated().
		and().logout().permitAll()
				 	.addLogoutHandler(new MyLogoutHandler("JSESSIONID"))	//自訂註銷Cookie
				 	.logoutSuccessHandler(myLogoutSuccessHandler);	//註銷後返回處理
				 	//.deleteCookies("JSESSIONID");



		 http.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)	//默認IF_REQUIRED，設定無狀態模式stateless
		 	.invalidSessionStrategy(myInvalidSessionStrategy)			//session到期處理
		 	.maximumSessions(100)										//最大人數
		 	.maxSessionsPreventsLogin(false)							//達最大人數後是否允許登入
		 	.expiredSessionStrategy(myExpiredSessionStrategy);			//人數過多踢下線處理



		http.csrf().disable();		//關閉CSRF保護
		
			//.antMatchers("/INSTRU_ALLOCATE_3M3.html").hasAuthority("R222")
			//.antMatchers("/have/auth").hasAynAuthority("VIP1","VIP2)	//多權限
			//.antMatchers("/level1/**").hasRole("sale"); 				//類似hasAuthority功能	
			//.antMatchers("/level1/**").hasAnyRole("sale");
		


    }



}