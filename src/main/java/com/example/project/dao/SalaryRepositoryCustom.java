package com.example.project.dao;

import com.example.project.model.Salary;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface SalaryRepositoryCustom {
    public List<Salary> getAmmountYear(Long workerId);
    public List<Salary> getAmmountCustom(Long workerId, OffsetDateTime begin, OffsetDateTime end);
    public List<Salary> getSalariesForOne(Long workerId);
    public int updateSalary(Long id, Salary salary);
}
