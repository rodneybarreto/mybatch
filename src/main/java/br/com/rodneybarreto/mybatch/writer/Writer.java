package br.com.rodneybarreto.mybatch.writer;

import br.com.rodneybarreto.mybatch.domain.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Writer {

    @Bean
    public ItemWriter<Customer> write(@Qualifier("appDS") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .dataSource(dataSource)
                .sql("""
                    INSERT INTO customer (name, email, pix_key, pix_key_encrypted, pix_key_open)
                    VALUES (:name, :email, :pixKey, :pixKeyEncrypted, :pixKeyOpen)
                """)
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }

}
