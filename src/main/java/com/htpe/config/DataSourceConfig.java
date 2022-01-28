
package com.htpe.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 计沮畐皌竚摸
 *
 */
@Configuration
public class DataSourceConfig {

    /*
    * 计沮方皌竚
	*/
	@Bean(name = "olddb")
	@ConfigurationProperties(prefix = "spring.datasource.olddb")					//眖皌竚ゅンい莉把计
	public DataSource OldDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "newdb")
	@ConfigurationProperties(prefix = "spring.datasource.newdb")
	public DataSource newDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

}
