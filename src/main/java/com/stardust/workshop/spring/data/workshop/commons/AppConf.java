package com.stardust.workshop.spring.data.workshop.commons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement //Equals to <tx:annotation-driven transaction-manager="xxxxx"/>
@PropertySource("classpath:application.properties")
public class AppConf {
    @Value("${workshop.jdbc.datasource.url}")
    private String jdbcDBUrl;

    @Value("${workshop.jdbc.datasource.username}")
    private String jdbcDBUsername;

    @Value("${workshop.jdbc.datasource.password}")
    private String jdbcDBPassword;

    @Value("${workshop.jdbc.datasource.driverClassName}")
    private String jdbcDBDriver;

    /**
     * This is tricky one....YinTreeSiTing
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "JDBCDataSource")
    public DataSource getJDBCDataSource() {
        DataSource ds = DataSourceBuilder
                            .create()
                            .driverClassName(jdbcDBDriver)
                            .url(jdbcDBUrl)
                            .username(jdbcDBUsername)
                            .password(jdbcDBPassword).build();
        return ds;
    }

    @Bean(name = "JDBCTemplate")
    @Autowired
    public JdbcTemplate getJDBCTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "JDBCDataSourceManager")
    @Autowired
    public PlatformTransactionManager getDataSourceTransactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}
