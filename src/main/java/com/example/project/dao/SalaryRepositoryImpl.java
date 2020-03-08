package com.example.project.dao;

import com.example.project.model.Salary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

//@Repository
//@Transactional(readOnly = true)
public class SalaryRepositoryImpl implements SalaryRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Salary> getAmmountYear(Long workerId) {
        OffsetDateTime lastYear = OffsetDateTime.now().minus(1, ChronoUnit.YEARS);
        Query query = entityManager.createNativeQuery("SELECT * FROM salary WHERE workerid = :givenId AND time_stamp > :data ", Salary.class)
                .setParameter("givenId", workerId)
                .setParameter("data", lastYear);
        return query.getResultList();
    }

    @Override
    public List<Salary> getAmmountCustom(Long workerId, OffsetDateTime begin, OffsetDateTime end) {
        Query query = entityManager.createNativeQuery("select * from salary WHERE time_stamp >= :begin AND time_stamp <= :end", Salary.class)
                .setParameter("begin", begin)
                .setParameter("end", end);
        return query.getResultList();
    }

    @Override
    public List<Salary> getSalariesForOne(Long workerId) {
        Query query = entityManager.createNativeQuery("select * from salary WHERE workerid = :givenId", Salary.class)
                .setParameter("givenId", workerId);
        return query.getResultList();
    }

    @Override
    public int updateSalary(Long id, Salary salary) {
        Query query = entityManager.createNativeQuery("UPDATE salary SET ammount = :ammount, time_stamp = :timestamp, workerid = :workerid WHERE salaryid = :salaryid",
                Salary.class).setParameter("ammount", salary.getAmount())
                .setParameter("timestamp", salary.getTime_stamp())
                .setParameter("workerid", salary.getSalaryid())
                .setParameter("salaryid", id);
        return query.executeUpdate();
    }
}
