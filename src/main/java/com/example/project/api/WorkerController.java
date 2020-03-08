package com.example.project.api;

import com.example.project.model.Worker;
import com.example.project.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/worker")
@RestController
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public void addWorker(@Valid @NotNull @RequestBody Worker worker){
        workerService.addWorker(worker);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long id){
        Optional<Worker> worker = workerService.getWorkerById(id);
        if(!worker.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(worker.get());
    }

    @GetMapping
    public List<Worker> getAllWorkers(@RequestParam(defaultValue = "0") int page){
        return workerService.getAllWorkers(page);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateWorker(@PathVariable Long id, @Valid @NotNull @RequestBody Worker worker){
        if(!workerService.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        workerService.updateWorker(id, worker);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable Long id){
        if(!workerService.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        workerService.deleteWorker(id);
        return ResponseEntity.ok().build();
    }
}
