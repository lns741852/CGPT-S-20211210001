
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
 * Mybatis�t�m for NewDB
 */
@Configuration
@MapperScan(basePackages = { "com.htpe.mapper.nnew" }, sqlSessionFactoryRef = "sqlSessionFactory2")	
public class MybatisNewDBConfig {

	@Autowired
	@Qualifier("newdb")		//���w�ϥΪ�bean
	private DataSource ds2;

	@Primary				//�D�ƾڷ��A�t�h��Ʈw�ɥ��n
	@Bean 
	public SqlSessionFactory sqlSessionFactory2() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds2); // �ϥ�new��ƨӷ�, �s�unew�w 
		factoryBean.setTypeAliasesPackage("com.htpe.bean");
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/nnew/*.xml"));
		return factoryBean.getObject(); 
	}

	@Primary
	@Bean 
	public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2());// �ϥΤW���t�m��Factory 
		return template; 
	} 
}
