package com.htpe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * 跨域整合
 * @author Administrator
 *
 */

@Configuration
public class CsrWebMvcConfigurer implements WebMvcConfigurer {
//	  @Override
//	    public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**")//項目中所有接口都支持跨域
//	                .allowedOriginPatterns("**")//所有地址都可以訪問
//	                .allowCredentials(true)
//	                .allowedMethods("*")//"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"
//	                .maxAge(3600);//允許時間
//	    }
	  
	  @Bean
	    public CorsFilter corsFilter() {
	         //1.添加CORS配置信息
	        CorsConfiguration config = new CorsConfiguration();
	          //放行哪些原始域
	          config.addAllowedOriginPattern("*");
	          //是否發送Cookie信息
	          config.setAllowCredentials(true);
	          //放行哪些请求方式
	          config.addAllowedMethod("*");
	          //放行哪些header
	          config.addAllowedHeader("*");
	          //暴露哪些header,前端接收header
	          config.addExposedHeader("*");

	        //2.添加映射路徑
	        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
	        configSource.registerCorsConfiguration("/**", config);

	        //3.返回新的CorsFilter.
	        return new CorsFilter(configSource);
	    }
}
