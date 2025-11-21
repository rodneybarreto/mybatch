package br.com.rodneybarreto.mybatch.reader;

import br.com.rodneybarreto.mybatch.domain.Customer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class MyBatchReader {

    @Bean
    public ItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("read-customer-file")
                .resource(new FileSystemResource("imports/customer.csv"))
                .comments("--")
                .delimited()
                .names("name", "email", "pixKey")
                .targetType(Customer.class)
                .build();
    }

}
