package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@JsonAutoDetect
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryid;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private OffsetDateTime time_stamp;

    @ManyToOne
    @JoinColumn(name = "workerid", updatable=false, insertable=false)
    private Worker worker;

    @Column(name = "workerid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long workerid;

    public Salary() {
    }

    public Salary(Long salaryid, @NotNull @JsonProperty BigDecimal amount,
                  @NotNull @JsonProperty OffsetDateTime time_stamp, @NotNull @JsonProperty Worker worker) {
        this.salaryid = salaryid;
        this.amount = amount;
        this.time_stamp = time_stamp;
        this.worker = worker;
        this.workerid = worker.getWorkerid();
    }

    public Long getSalaryid() {
        return salaryid;
    }

    public void setSalaryid(Long salaryid) {
        this.salaryid = salaryid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OffsetDateTime getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(OffsetDateTime time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Long getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Long workerid) {
        this.workerid = workerid;
    }
}
