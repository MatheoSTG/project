package com.example.project;

import com.example.project.dao.SalaryRepository;
import com.example.project.model.Salary;
import com.example.project.model.Worker;
import com.example.project.service.SalaryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SalaryServiceTests {

    @Mock
    private SalaryRepository salaryRepository;

    private Clock clock;

    private SalaryService salaryService;

    @Before
    public void setUp(){
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        salaryService = new SalaryService(salaryRepository, clock);
    }

    @Test
    public void getAmountYear_returnYearAmount() {
        Long id = 1L;

        Worker testWorker = new Worker(1L, "Jerry", "Goldsmith");

        Salary testSalary1 = new Salary(1L, new BigDecimal("1000.00"),
                OffsetDateTime.now(clock).minus(6, ChronoUnit.MONTHS), testWorker);
        Salary testSalary2 = new Salary(2L, new BigDecimal("500.00"),
                OffsetDateTime.now(clock).minus(7, ChronoUnit.MONTHS), testWorker);

        List<Salary> testSalaries = new ArrayList<>();
        testSalaries.add(testSalary1);
        testSalaries.add(testSalary2);

        OffsetDateTime yearBefore = OffsetDateTime.now(clock).minus(1, ChronoUnit.YEARS);
        when(salaryRepository.getAmountYear(eq(id), eq(yearBefore))).thenReturn(testSalaries);
        BigDecimal salaryServiceResponse = salaryService.getAmountYear(id);
        assertThat(salaryServiceResponse).isEqualTo(new BigDecimal("750.00"));
    }
}
