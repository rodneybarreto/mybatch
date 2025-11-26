package br.com.rodneybarreto.mybatch.step;

import br.com.rodneybarreto.mybatch.enums.SchemaEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
class StepConfigIT {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    @Qualifier("appDS")
    private DataSource dataSource;

    @Autowired
    private JdbcOperations jdbcTemplate;

    @BeforeEach
    void before() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcTemplate.execute(SchemaEnum.CREATE_TABLE_CUSTOMER.getSql());
    }

    @Test
    void testStep() {
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step");
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

        assertEquals(15, stepExecution.getReadCount());
        assertEquals(15, stepExecution.getWriteCount());
    }

    @AfterEach
    void after() {
        this.jdbcTemplate.execute(SchemaEnum.DROP_TABLE_CUSTOMER.getSql());
    }

}
