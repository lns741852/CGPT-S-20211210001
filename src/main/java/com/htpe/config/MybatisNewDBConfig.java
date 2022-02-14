
package com.htpe.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


/**
 * Mybatis配置 for NewDB
 */
@Configuration
@MapperScan(basePackages = { "com.htpe.mapper.nnew" }, sqlSessionFactoryRef = "sqlSessionFactory2")	
public class MybatisNewDBConfig {

	@Autowired
	@Qualifier("newdb")		//指定使用的bean
	private DataSource ds2;

	@Primary				//主數據源，配多資料庫時必要
	@Bean 
	public SqlSessionFactory sqlSessionFactory2() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds2); // 使用new資料來源, 連線new庫 
		factoryBean.setTypeAliasesPackage("com.htpe.bean");
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/nnew/*.xml"));
		return factoryBean.getObject(); 
	}

	@Primary
	@Bean 
	public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2());// 使用上面配置的Factory 
		return template; 
	} 
}
