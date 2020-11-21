//package com.huihui.aligo.datasource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * 二号数据源配置类
// *
// * @author minghui.y BG358486
// * @create 2020-10-17 19:28
// **/
////@Configuration
////@MapperScan(basePackages = "com.huihui.aligo.mapper.test02", sqlSessionFactoryRef = "sqlSessionFactory02")
//public class DataSource02Config {
//
//    @Bean("dataSource02")
//    @ConfigurationProperties(prefix = "ext.datasource.test02")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean("sqlSessionFactory02")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource02") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        return factoryBean.getObject();
//    }
//
//    @Bean("dataSourceTransactionManager02")
//    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource02") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean("sqlSessionTemplate02")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory02") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//}
