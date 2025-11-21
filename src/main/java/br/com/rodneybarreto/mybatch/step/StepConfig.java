package br.com.rodneybarreto.mybatch.step;

import br.com.rodneybarreto.mybatch.domain.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    private final PlatformTransactionManager transactionManagerApp;

    public StepConfig(@Qualifier("transactionManagerAppDS") PlatformTransactionManager transactionManagerApp) {
        this.transactionManagerApp = transactionManagerApp;
    }

    @Bean
    public Step step(JobRepository jobRepository, ItemReader<Customer> reader, ItemWriter<Customer> writer) {
        return new StepBuilder("step", jobRepository).
                <Customer, Customer>chunk(10, transactionManagerApp)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
