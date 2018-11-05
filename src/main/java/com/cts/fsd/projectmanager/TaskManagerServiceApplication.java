package com.cts.fsd.projectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cts.fsd.projectmanager.controller.TaskController;
import com.cts.fsd.projectmanager.service.TaskManagerService;

@SpringBootApplication(scanBasePackages={"com.cts.fsd.taskmanager.service","com.cts.fsd.taskmanager.controller"})
@ComponentScan
@CrossOrigin
public class TaskManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerServiceApplication.class, args);
	}
}
