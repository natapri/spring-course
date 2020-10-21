package com.jrp.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private long projectId;

    @NotBlank(message="*Must give a name")
    @Size(min=2, max=50)
    private String name;

    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
    private String description;


    //@FutureOrPresent(message="date can not be empty, date must be present or future")
    @NotNull(message = "Parameter Date can not be empty")
    @DateTimeFormat(pattern = "dd-mm-yy")
    private Date startDate;


   // @FutureOrPresent(message="date can not be empty")
    @NotNull(message = "Parameter Date can not be empty")
    @DateTimeFormat(pattern = "dd-mm-yy")
    private Date endDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
               joinColumns = @JoinColumn(name = "project_id"),
               inverseJoinColumns = @JoinColumn(name = "employee_id"))

    @JsonIgnore
    private List<Employee> employees;

    public Project() {
    }

    public Project(String name, String stage, String description, Date startDate, Date endDate ) {
        this.name = name;
        this.stage = stage;
        this.description = description;
        this.startDate = startDate;
        this.endDate =endDate;

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //convenience method
    public  void addEmployee(Employee em){
        if(employees==null){
            employees= new ArrayList<>();
        }
        employees.add(em);
    }
}
