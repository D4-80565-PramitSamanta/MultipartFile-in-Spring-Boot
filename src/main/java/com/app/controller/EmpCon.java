package com.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.EmpDTO;
import com.app.entities.Employee;
import com.app.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmpCon {
	@Autowired
	EmployeeService eService;
	
	@GetMapping()
    public ResponseEntity<?> getAllEmployees() {
        List<EmpDTO> employees = eService.getAllEmp();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
	
	 @GetMapping(value = "/{eid}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
		public ResponseEntity<?> serveMovieImage(@PathVariable Integer eid) throws IOException {
			return ResponseEntity.ok(eService.getImg(eid));
		}



	@PostMapping
	(consumes = "multipart/form-data")
	public ResponseEntity<Employee> savEmployee(@RequestParam("name") String name,
			@RequestParam("email") String email,
            @RequestParam("profilePic") MultipartFile profilePic) throws IOException
	{
		
		Employee emp = new Employee();
		emp.setName(name);
		emp.setEmail(email);
		emp.setProfilePic(profilePic.getBytes());
		return new ResponseEntity<>(eService.saveEmp(emp), HttpStatus.CREATED);
	}
}
