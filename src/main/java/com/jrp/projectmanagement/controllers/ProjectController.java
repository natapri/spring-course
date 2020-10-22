package com.jrp.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.projectmanagement.dao.EmployeeRepository;
import com.jrp.projectmanagement.dao.ProjectRepository;
import com.jrp.projectmanagement.dto.TimeChartData;
import com.jrp.projectmanagement.entities.Employee;
import com.jrp.projectmanagement.entities.Project;
import com.jrp.projectmanagement.services.EmployeeService;
import com.jrp.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {


    @Autowired
    ProjectService proService;

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayProjects(Model model){
        List<Project> projects=proService.getAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model){
        Project aProject = new Project();
        Iterable<Employee> employees=empService.getAll();
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);
       return "projects/new-project";
    }
    @PostMapping("/save")
    public String createProject(@Validated Project project, Errors errors, Model model){
        if(errors.hasErrors()) {
            return "projects/new-project";
        }

       // this should handle saving to the database
        proService.save(project);

        //use a redirect to prevent a duplicate submission
        return "redirect:/projects";
    }

    @GetMapping("/timelines")
    public String displayProjectTimelines(Model model) throws JsonProcessingException {
        List<TimeChartData> timelineData = proService.getTimeData();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

        System.out.println("---------- project timelines ----------");
        System.out.println(jsonTimelineString);

        model.addAttribute("projectTimeList", jsonTimelineString);
        return "projects/project-timelines";
    }
}
