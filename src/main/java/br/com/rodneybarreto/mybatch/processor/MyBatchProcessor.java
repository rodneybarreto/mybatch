package br.com.rodneybarreto.mybatch.processor;

import br.com.rodneybarreto.mybatch.domain.Customer;
import br.com.rodneybarreto.mybatch.helpers.AESHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatchProcessor {

    private final AESHelper aesHelper;

    @Bean
    public ItemProcessor<Customer, Customer> processor() {
        return customer -> {
            customer.setPixKeyEncrypted(aesHelper.encrypt(this.clean(customer.getPixKey())));
            return customer;
        };
    }

    private String clean(String cpf) {
        return cpf.replaceAll("[.-]", "");
    }

}
