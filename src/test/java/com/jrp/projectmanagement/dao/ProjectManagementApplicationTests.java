package com.jrp.projectmanagement.dao;

import com.jrp.projectmanagement.ProjectManagementApplication;
import com.jrp.projectmanagement.dao.ProjectRepository;
import com.jrp.projectmanagement.entities.Project;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;


import java.util.Date;

import static org.junit.Assert.assertEquals;

@SpringBootTest

@ContextConfiguration(classes= ProjectManagementApplication.class)
@RunWith(Runner.class)

@SqlGroup({@Sql(executionPhase= ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")

})
class ProjectManagementApplicationTests {
	@Autowired
	ProjectRepository proRepo;

	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description",
				new Date(), new Date());
		proRepo.save(newProject);

		assertEquals(5, proRepo.findAll().size());

	}

	/*@Test
	void contextLoads() {
	}*/

}
