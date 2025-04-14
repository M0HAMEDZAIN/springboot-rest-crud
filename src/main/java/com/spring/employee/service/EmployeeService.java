package com.spring.employee.service;

import java.util.List;

import com.spring.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int id) throws Exception;

	public Employee save(Employee employee);

	public Employee update(Employee employee);

	public void delete(int id) throws Exception;
	
}
