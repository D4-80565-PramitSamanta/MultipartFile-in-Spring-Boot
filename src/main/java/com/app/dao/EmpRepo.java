package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Employee;
@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {
	
}
