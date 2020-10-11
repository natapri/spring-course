package com.jrp.projectmanagement.dao;

import com.jrp.projectmanagement.dto.ChartData;
import com.jrp.projectmanagement.dto.EmployeeProject;
import com.jrp.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    List<Project> findAll();

    @Query(nativeQuery = true, value ="SELECT stage as label, COUNT(*) as value " +
            "FROM project GROUP BY stage")
    public List<ChartData> getProjectStatus();
}
