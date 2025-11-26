package br.com.rodneybarreto.mybatch.reader;

import br.com.rodneybarreto.mybatch.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class ReaderIT {

    @Autowired
    private ItemReader<Customer> reader;

    @Test
    void testFileReader() throws Exception {
        var flatFileItemReader = (FlatFileItemReader<Customer>) reader;
        flatFileItemReader.open(new ExecutionContext());

        Customer customer;
        while((customer = flatFileItemReader.read()) != null) {
            assertNotNull(customer.getName());
            assertNotNull(customer.getEmail());
            assertNotNull(customer.getPixKey());
        }
    }

}
