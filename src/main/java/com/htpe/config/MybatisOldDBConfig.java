
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
 * Mybatis�t�m for oldDB
 */
@Configuration
@MapperScan(basePackages = { "com.htpe.mapper.old" }, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisOldDBConfig {

	@Autowired
	@Qualifier("olddb")
	private DataSource ds1;

	@Primary
	@Bean
	public SqlSessionFactory sqlSessionFactory1() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds1); // �ϥ�old��ƨӷ�, �s�uold�w
		factoryBean.setTypeAliasesPackage("com.htpe.bean");
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/old/*.xml"));
		return factoryBean.getObject();
	}

	@Primary
	@Bean
	public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1());
		// �ϥΤW���t�m��Factory
		return template;
	}
}
