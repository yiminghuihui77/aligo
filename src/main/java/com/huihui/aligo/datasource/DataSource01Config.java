package com.huihui.aligo.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 一号数据源配置类
 *
 * @author minghui.y BG358486
 * @create 2020-10-17 19:28
 **/
@Configuration
@MapperScan(basePackages = "com.huihui.aligo.mapper.test01", sqlSessionFactoryRef = "sqlSessionFactory01")
public class DataSource01Config {

    @Bean("dataSource01")
    @ConfigurationProperties(prefix = "ext.datasource.test01")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean("sqlSessionFactory01")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource01") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean("dataSourceTransactionManager01")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource01") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sqlSessionTemplate01")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory01") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
