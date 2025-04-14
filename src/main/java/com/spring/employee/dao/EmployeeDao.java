package com.spring.employee.dao;

import java.util.List;

import com.spring.employee.entity.Employee;

public interface EmployeeDao {
	public List<Employee> findAll();

	public Employee findById(int id);

	public Employee save(Employee employee);

	public Employee update(Employee employee);

	public void delete(Employee employee);
}
