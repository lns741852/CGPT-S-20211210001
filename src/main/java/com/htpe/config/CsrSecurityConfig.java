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
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import com.htpe.filter.JwtAuthenticationFilter;



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
    
//    @Autowired
//    MyLogoutHandler myLogoutHandler;
    
    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;
    
    @Autowired
    MyInvalidSessionStrategy myInvalidSessionStrategy;
    
    @Autowired
    MyExpiredSessionStrategy myExpiredSessionStrategy;
    
    
    @Bean														//Security中加密用
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
     * 此方法會繞過spring scurity認證
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/v2/api-docs/**")
                .antMatchers("/i18n/**")
                .antMatchers("/test/**")
                .antMatchers("/file/**")
                .antMatchers("/content/**")
                .antMatchers("/webjars/springfox-swagger-ui/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/index");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    		 
		http.cors()
		.and()			
		 	.csrf().disable()		//關閉CSRF保護
		 	.formLogin()
		 	.successHandler(myAuthenticationSuccessHandler)		//登入成功處理
			.failureHandler(myAuthenticationFailureHandler)		//登入失敗處理	
			
		.and()
			.logout()
			.logoutSuccessHandler(myLogoutSuccessHandler)
			
		.and().exceptionHandling()
	 		.authenticationEntryPoint(myAuthenticationEntryPoint)		//無權限處理
		 	.accessDeniedHandler(myAccessDeniedHandler)				//權限不足處理
			
		.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
		.and()
			.authorizeRequests()
			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
			.antMatchers("/account").hasAnyAuthority("sys:account")
			.antMatchers("/udi").hasAnyAuthority("sys:udi")
			.anyRequest().authenticated()
		
		//jwt攔截器
		.and()
			.addFilter(jwtAuthenticationFilter())
			.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		

    }
    




}