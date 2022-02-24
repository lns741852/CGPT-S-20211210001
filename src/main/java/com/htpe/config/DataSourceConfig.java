
package com.htpe.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 多數據庫配置類
 *
 */
@Configuration
public class DataSourceConfig {

    /*
    * 數據源配置
	*/
	@Bean(name = "olddb")
	@ConfigurationProperties(prefix = "spring.datasource.olddb")					//從配置文件中獲取參數
	public DataSource OldDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "newdb")
	@ConfigurationProperties(prefix = "spring.datasource.newdb")
	public DataSource newDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	
    // 事務管理器配置
    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager(@Qualifier("newdb") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}