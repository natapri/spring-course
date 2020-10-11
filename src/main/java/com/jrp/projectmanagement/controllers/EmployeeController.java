package com.jrp.projectmanagement.controllers;


import com.jrp.projectmanagement.dao.EmployeeRepository;
import com.jrp.projectmanagement.entities.Employee;
import com.jrp.projectmanagement.entities.Project;
import com.jrp.projectmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayEmployees(Model model){

        Iterable<Employee> employees=empService.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);
        return "employees/new-employee";
    }
    @PostMapping("/save")
    public String createEmployee(Employee employee, Model model){
        // this should handle saving to the database
        empService.save(employee);
        //use a redirect to prevent a duplicate submission
        return "redirect:/employee/new";
    }
}
