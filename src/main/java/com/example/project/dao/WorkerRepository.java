package com.example.project.dao;

import com.example.project.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("workerJpa")
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
