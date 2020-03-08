package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@JsonAutoDetect
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workerid;

    @NotBlank
    private String firstname;

    @NotBlank
    private String surname;

    public Worker() {
    }

    public Worker(Long workerid, @NotBlank @JsonProperty("firstname") String firstname, @NotBlank @JsonProperty("surname") String surname) {
        this.workerid = workerid;
        this.firstname = firstname;
        this.surname = surname;
    }

    public Long getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Long workerid) {
        this.workerid = workerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
