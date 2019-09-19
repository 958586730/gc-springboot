package com.yfc.gc.config.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.yfc.gc.core.base.mapper.WriterBaseMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.yfc.gc.mapper.writer"
        , markerInterface = WriterBaseMapper.class
        , sqlSessionFactoryRef = "writerSqlSessionFactory")
public class MapperWriterConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties("spring.datasource.writer")
    public DruidDataSource dataSourceWriter(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory writerSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceWriter());
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:com/yfc/gc/mapper/writer/**/*Mapper.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate writerSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(writerSqlSessionFactory());
    }

    @Bean
    public PlatformTransactionManager writerTransactionManager() {
        return new DataSourceTransactionManager(dataSourceWriter());
    }
}
