package com.app.service;

import java.util.List;

import com.app.dto.EmpDTO;
import com.app.entities.Employee;

public interface EmployeeService {

	List<EmpDTO> getAllEmp();

	byte[] getImg(Integer eid);

	Employee saveEmp(Employee emp);
	
}
