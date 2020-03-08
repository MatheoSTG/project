package com.example.project.service;

import com.example.project.dao.WorkerRepository;
import com.example.project.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    private final WorkerRepository jpaRepository;

    @Autowired
    public WorkerService(@Qualifier("workerJpa") WorkerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public void addWorker(Worker worker){
        jpaRepository.save(worker);
    }

    public List<Worker> getAllWorkers(int page){
        Pageable pageable = PageRequest.of(page,2, Sort.by(Sort.Direction.ASC, "surname"));
        return jpaRepository.findAll(pageable).getContent();
    }

    public Optional<Worker> getWorkerById(Long id){
        return jpaRepository.findById(id);
    }

    public int updateWorker(Long id, Worker worker){
        if(jpaRepository.existsById(id)){
            jpaRepository.save(new Worker(id, worker.getFirstname(), worker.getSurname()));
            return 1;
        }
        return 0;
    }

    public void deleteWorker(Long id){
        jpaRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return jpaRepository.existsById(id);
    }
}
