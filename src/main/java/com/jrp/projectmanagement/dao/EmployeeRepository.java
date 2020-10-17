package com.jrp.projectmanagement.dao;


import com.jrp.projectmanagement.dto.EmployeeProject;
import com.jrp.projectmanagement.entities.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    @Query(nativeQuery = true, value = "SELECT e.first_name as firstName, e.last_name as lastName, " +
            "COUNT(pe.employee_id) as projectCount FROM employee e left join project_employee pe " +
            "ON pe.employee_id=e.employee_id GROUP BY e.first_name, e.last_name " +
            "ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

    public Employee findByEmail(String value);

    public Employee findByEmployeeId(long theId);
}
