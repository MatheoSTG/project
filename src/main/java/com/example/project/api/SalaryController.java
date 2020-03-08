package com.example.project.api;

import com.example.project.model.Salary;
import com.example.project.service.SalaryService;
import com.example.project.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("api/v1/salary")
@RestController
public class SalaryController {

    private final SalaryService salaryService;
    private final WorkerService workerService;

    @Autowired
    public SalaryController(SalaryService salaryService, WorkerService workerService) {
        this.salaryService = salaryService;
        this.workerService = workerService;
    }

    @PostMapping
    public ResponseEntity<String> addSalary(@Valid @NotNull @RequestBody Salary salary){
        if(salary.getWorker()==null){
            if(!workerService.existsById(salary.getWorkerid())){
                return ResponseEntity.badRequest().build();
            }
            salary.setWorker(workerService.getWorkerById(salary.getWorkerid()).get());
        }
        salaryService.addSalary(salary);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "year/{id}")
    public ResponseEntity<BigDecimal> getAmountYear(@PathVariable Long id){
        if(!workerService.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        BigDecimal amountYear;
        try{
            amountYear = salaryService.getAmountYear(id);

        }catch(NoSuchElementException ex){
            return ResponseEntity.ok(BigDecimal.ZERO);
        }
        return ResponseEntity.ok(amountYear);
    }

    @GetMapping(path = "custom/{id}/{beg}/{end}")
    public ResponseEntity<BigDecimal> getAmountCustom(@PathVariable Long id,
                                      @PathVariable String beg,
                                      @PathVariable String end){
        if(!workerService.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        BigDecimal amountCustom;
        try{
            amountCustom = salaryService.getAmountCustom(id, OffsetDateTime.parse(beg), OffsetDateTime.parse(end));

        }catch(NoSuchElementException ex){
            return ResponseEntity.ok(BigDecimal.ZERO);
        }
        return ResponseEntity.ok(amountCustom);
    }

    @GetMapping(path="search")
    public ResponseEntity<List<Salary>> getSalaries(@RequestParam(name = "id") List<Long> workerList, @RequestParam(defaultValue = "0") int page){
        List<Salary> salariesList = salaryService.getSalaries(workerList, page);
        if(salariesList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salariesList);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateSalary(@PathVariable Long id, @RequestBody @Valid Salary salary){
        if(salary.getWorker()==null){
            if(!workerService.existsById(salary.getWorkerid())){
                return ResponseEntity.badRequest().build();
            }
            salary.setWorker(workerService.getWorkerById(salary.getWorkerid()).get());
        }
        if(salaryService.updateSalary(id, salary)==0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable Long id){
        if(!salaryService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        salaryService.deleteSalary(id);
        return ResponseEntity.ok().build();
    }
}
