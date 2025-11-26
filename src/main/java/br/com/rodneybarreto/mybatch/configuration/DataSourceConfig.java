package br.com.rodneybarreto.mybatch.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springDS() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.app.datasource")
    public DataSource appDS() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "transactionManagerAppDS")
    public PlatformTransactionManager transactionManagerAppDS(@Qualifier("appDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
