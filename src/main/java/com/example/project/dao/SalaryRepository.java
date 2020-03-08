package com.example.project.dao;

import com.example.project.model.Salary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Repository("salaryJpa")
public interface SalaryRepository extends JpaRepository<Salary,Long>{

    @Query(value = "SELECT * FROM salary WHERE workerid = :givenId AND time_stamp > :data",nativeQuery = true)
    List<Salary> getAmountYear(Long givenId, OffsetDateTime data);

    @Query(value = "select * from salary WHERE workerid = :givenId AND time_stamp >= :begin AND time_stamp <= :end",nativeQuery = true)
    List<Salary> getAmountCustom(Long givenId, OffsetDateTime begin, OffsetDateTime end);

    @Query(value = "SELECT * FROM salary WHERE workerid IN :workerList", nativeQuery = true,
            countQuery = "SELECT count(*) FROM salary WHERE workerid IN :workerList")
    List<Salary> getSalaries(List<Long> workerList, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE salary SET amount = :amount, time_stamp = :timestamp, workerid = :workerid WHERE salaryid = :salaryid",
            nativeQuery = true)
    int updateSalary(Long salaryid, BigDecimal amount, OffsetDateTime timestamp, Long workerid);

}
