package com.example.project;

import com.example.project.dao.SalaryRepository;
import com.example.project.model.Salary;
import com.example.project.model.Worker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
//@DataJpaTest
public class SalaryRepositoryTest {

    //@Autowired
    //private TestEntityManager testEntityManager;

    //@Autowired
    //private SalaryRepository salaryRepository;

    @Test
    public void whenGetSalariesForOne_thenReturnListSalaries(){

        Worker worker = new Worker(1L, "Marek", "Grechuta");
        //Salary salary_one = new Salary(1, new BigDecimal("1050.45"),  )
        //testEntityManager.persist(worker);

    }
}
