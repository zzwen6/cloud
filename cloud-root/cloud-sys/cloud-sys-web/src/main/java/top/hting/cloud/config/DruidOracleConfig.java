package top.hting.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "top.hting.cloud.mapper",
    sqlSessionFactoryRef = "oracleSessionFactory")
public class DruidOracleConfig {

    /**数据源*/
    @Bean(name = "oracleDatasource")
    @Qualifier("oracleDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDatasource() {
        return new DruidDataSource();
    }


    @Bean
    public MybatisSqlSessionFactoryBean oracleSessionFactory(@Qualifier("oracleDatasource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();

        MybatisConfiguration config = new MybatisConfiguration();
        config.setMapUnderscoreToCamelCase(false);
        config.setCacheEnabled(true);

        factoryBean.setConfiguration(config);


        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*Mapper.xml"));
        return factoryBean;
    }


    @Bean
    public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracleDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
