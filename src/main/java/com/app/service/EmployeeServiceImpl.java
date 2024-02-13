package com.app.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.persistence.Entity;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.EmpRepo;
import com.app.dto.EmpDTO;
import com.app.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService  {
	@Autowired
    private EmpRepo empRepo;
	@Autowired
	ModelMapper mapper;
	
	public List<EmpDTO> getAllEmp() 
	{
		List<Employee> emps = empRepo.findAll();
		List<EmpDTO> dtos = emps.stream()
				.map(s->mapper.map(s,EmpDTO.class))
				.collect(Collectors.toList());
		return dtos;
	}
	
	public Employee saveEmp(Employee emp)
	{
		return empRepo.save(emp);
		
	}

	public EmpDTO findBY(long eid) 
	{
		Employee e = empRepo.findById(eid).get();
		return mapper.map(empRepo, EmpDTO.class);
	}

	public byte[] getImg(Integer eid) {
		Employee e = empRepo.findById((long) eid).get();
		return e.getProfilePic();
	}

	
}
