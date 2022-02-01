package com.htpe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.htpe.filter.JwtAuthenticationFilter;


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
    
    
    @Bean														//Security���[�K��
    PasswordEncoder passowrd(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception{
    	JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passowrd());
    }
    

    /**
     *
     * ����k�|¶�Lspring scurity�{��
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/v2/api-docs/**")
                .antMatchers("/i18n/**")
                .antMatchers("/test/**")
                .antMatchers("/content/**")
                .antMatchers("/webjars/springfox-swagger-ui/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/index");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		 
		http.cors()
		.and()
		 	.csrf().disable()		//����CSRF�O�@
		 	.formLogin()
		 	.successHandler(myAuthenticationSuccessHandler)		//�n�J���\�B�z
			.failureHandler(myAuthenticationFailureHandler)		//�n�J���ѳB�z	
			
		.and()
			.logout()
			.logoutSuccessHandler(myLogoutSuccessHandler)
			
		.and().exceptionHandling()
	 		.authenticationEntryPoint(myAuthenticationEntryPoint)		//�L�v���B�z
		 	.accessDeniedHandler(myAccessDeniedHandler)				//�v�������B�z
			
		.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
		.and()
			.authorizeRequests()
			.antMatchers("/account").hasAnyAuthority("A")
			.anyRequest().authenticated()
				
		.and()
			.addFilter(jwtAuthenticationFilter())
			.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		

    }
    




}