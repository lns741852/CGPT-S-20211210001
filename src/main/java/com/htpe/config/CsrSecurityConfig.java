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
 * Security�t�m��
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
        String password = passwordEncoder.encode("password");       //�[�K
        auth                                     //�{�ұb�K�A�Y�L�t�mconfigure��k�A�s����URL���ݱK�X
                .inMemoryAuthentication()
                .withUser("admin")
                .password(password)
                .roles("VIP1", "VIP2");*/

        auth.userDetailsService(userDetailsService).passwordEncoder(passowrd());
    }

    @Bean														//Security���[�K��
    PasswordEncoder passowrd(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	

				
		http.formLogin().permitAll()	
			.successHandler(myAuthenticationSuccessHandler)		//�n�J���\�B�z
			.failureHandler(myAuthenticationFailureHandler)		//�n�J���ѳB�z		
		.and().exceptionHandling()
	 		.authenticationEntryPoint(myAuthenticationEntryPoint)		//�L�v���B�z
		 	.accessDeniedHandler(myAccessDeniedHandler);				//�v�������B�z
		 	
					
		http.authorizeRequests()
				.anyRequest().authenticated().
		and().logout().permitAll()
				 	.addLogoutHandler(new MyLogoutHandler("JSESSIONID"))	//�ۭq���PCookie
				 	.logoutSuccessHandler(myLogoutSuccessHandler);	//���P���^�B�z
				 	//.deleteCookies("JSESSIONID");



		 http.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)	//�q�{IF_REQUIRED�A�]�w�L���A�Ҧ�stateless
		 	.invalidSessionStrategy(myInvalidSessionStrategy)			//session����B�z
		 	.maximumSessions(100)										//�̤j�H��
		 	.maxSessionsPreventsLogin(false)							//�F�̤j�H�ƫ�O�_���\�n�J
		 	.expiredSessionStrategy(myExpiredSessionStrategy);			//�H�ƹL�h��U�u�B�z



		http.csrf().disable();		//����CSRF�O�@
		
			//.antMatchers("/INSTRU_ALLOCATE_3M3.html").hasAuthority("R222")
			//.antMatchers("/have/auth").hasAynAuthority("VIP1","VIP2)	//�h�v��
			//.antMatchers("/level1/**").hasRole("sale"); 				//����hasAuthority�\��	
			//.antMatchers("/level1/**").hasAnyRole("sale");
		


    }



}