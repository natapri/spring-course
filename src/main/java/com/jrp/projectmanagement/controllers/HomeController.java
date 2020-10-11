package com.jrp.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.projectmanagement.dao.EmployeeRepository;
import com.jrp.projectmanagement.dao.ProjectRepository;
import com.jrp.projectmanagement.dto.ChartData;
import com.jrp.projectmanagement.dto.EmployeeProject;
import com.jrp.projectmanagement.entities.Employee;
import com.jrp.projectmanagement.entities.Project;
import com.jrp.projectmanagement.services.EmployeeService;
import com.jrp.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${version}")
    private String ver;

    @Autowired
    ProjectService proService;

    @Autowired
    EmployeeService empService;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        model.addAttribute("versionNumber", ver);

        Map<String, Object> map = new HashMap<>();

       List<Project> projects=proService.getAll();
       model.addAttribute("projects", projects);

       List<ChartData> projectData = proService.getProjectStatus();
       //convert projectData into a json for use in javascript
        ObjectMapper objectMapper =new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        model.addAttribute("projectStatus", jsonString);

        List<EmployeeProject> employeeProjectsCnt=empService.employeeProjects();
        model.addAttribute("employeeListProjectsCnt", employeeProjectsCnt);
        return "main/home";
    }
}
