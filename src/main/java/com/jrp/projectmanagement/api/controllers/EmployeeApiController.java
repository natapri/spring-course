package com.jrp.projectmanagement.api.controllers;

import com.jrp.projectmanagement.dao.EmployeeRepository;
import com.jrp.projectmanagement.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public Iterable<Employee> getEmployees(){
        return empRepo.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return empRepo.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody  @Validated Employee employee) {
        return empRepo.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody  @Validated  Employee employee) {
        return empRepo.save(employee);
    }

    @PatchMapping(path="/{id}", consumes= "application/json")
    public Employee partialUpdate(@PathVariable("id") long id, @RequestBody  @Validated Employee patchEmployee) {
        Employee emp = empRepo.findById(id).get();

        if(patchEmployee.getEmail() != null) {
            emp.setEmail(patchEmployee.getEmail());
        }
        if(patchEmployee.getFirstName() != null) {
            emp.setFirstName(patchEmployee.getFirstName());
        }
        if(patchEmployee.getLastName() != null) {
            emp.setLastName(patchEmployee.getLastName());
        }

        return empRepo.save(emp);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            empRepo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {

        }
    }

    @GetMapping(params= {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page,
                                                     @RequestParam("size") int size){
        Pageable pageAndSize = PageRequest.of(page, size);

        return empRepo.findAll();
    }

}
