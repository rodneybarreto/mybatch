package br.com.rodneybarreto.mybatch.processor;

import br.com.rodneybarreto.mybatch.domain.Customer;
import br.com.rodneybarreto.mybatch.helpers.AESHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Processor {

    private final AESHelper aesHelper;

    @Bean
    public ItemProcessor<Customer, Customer> process() {
        return customer -> {
            String clean = this.clean(customer.getPixKey());
            String encrypted = aesHelper.encrypt(clean);
            String decrypted = aesHelper.decrypt(encrypted);

            return Customer.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .pixKey(customer.getPixKey())
                    .pixKeyEncrypted(encrypted)
                    .pixKeyOpen(decrypted)
                    .build();
        };
    }

    private String clean(String cpf) {
        return cpf.replaceAll("[.-]", "");
    }

}
