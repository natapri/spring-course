package com.jrp.projectmanagement.services;

import com.jrp.projectmanagement.dao.ProjectRepository;
import com.jrp.projectmanagement.dto.ChartData;
import com.jrp.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository proRepo;


    public Project save(Project project) {
        return proRepo.save(project);
    }


    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<ChartData> getProjectStatus(){
        return proRepo.getProjectStatus();
    }


   /* public List<TimeChartData> getTimeData(){
        return proRepo.getTimeData();
    } */
}
