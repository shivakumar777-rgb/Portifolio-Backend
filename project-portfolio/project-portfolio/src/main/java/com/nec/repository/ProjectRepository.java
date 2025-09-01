package com.nec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nec.model.Project;

public interface ProjectRepository  extends JpaRepository<Project, Long>{

}
