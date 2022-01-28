
package com.htpe.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 * �h�ƾڮw�t�m��
 *
 */
@Configuration
public class DataSourceConfig {

    /*
    * �ƾڷ��t�m
	*/
	@Bean(name = "olddb")
	@ConfigurationProperties(prefix = "spring.datasource.olddb")					//�q�t�m�������Ѽ�
	public DataSource OldDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "newdb")
	@ConfigurationProperties(prefix = "spring.datasource.newdb")
	public DataSource newDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

}
