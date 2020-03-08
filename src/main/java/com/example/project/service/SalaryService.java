package com.example.project.service;

import com.example.project.dao.SalaryRepository;
import com.example.project.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SalaryService {
    private final SalaryRepository jpaRepository;
    private Clock clock;

    @Autowired
    public SalaryService(@Qualifier("salaryJpa") SalaryRepository jpaRepository, Clock clock) {
        this.jpaRepository = jpaRepository;
        this.clock = clock;
    }

    public void addSalary(Salary salary){
        jpaRepository.save(salary);
    }

    public boolean existsById(Long id){
        return jpaRepository.existsById(id);
    }


    public BigDecimal getAmountYear(Long workerId){
        OffsetDateTime lastYear = OffsetDateTime.now(clock).minus(1, ChronoUnit.YEARS);
        List<Salary> salaries = jpaRepository.getAmountYear(workerId, lastYear);
        int size = salaries.size();
        BigDecimal sum = salaries.stream()
                .map(Salary::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(size), 2, BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getAmountCustom(Long workerId, OffsetDateTime begin, OffsetDateTime end){
        List<Salary> salaries = jpaRepository.getAmountCustom(workerId, begin, end);
        int size = salaries.size();

        BigDecimal sum = salaries.stream()
                .map(Salary::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(size), 2, BigDecimal.ROUND_HALF_EVEN);
    }

    /*
    public List<Salary> getSalariesForOne(Long workerId, int page){
        Pageable pageable = PageRequest.of(page,15, Sort.by("time_stamp"));
        return jpaRepository.getSalariesForOne(workerId, pageable);
    }

     */

    public List<Salary> getSalaries(List<Long> workerList, int page){
        Pageable pageable = PageRequest.of(page,15, Sort.by("workerid").and(Sort.by("time_stamp")));
        return jpaRepository.getSalaries(workerList, pageable);
    }

    public int updateSalary(Long id, Salary salary){
        return jpaRepository.updateSalary(id, salary.getAmount(), salary.getTime_stamp(),
                salary.getWorker().getWorkerid());
    }

    public void deleteSalary(Long id){
        jpaRepository.deleteById(id);
    }
}
