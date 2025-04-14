package com.spring.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.employee.dao.EmployeeDao;
import com.spring.employee.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao dao;

	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.dao = employeeDao;
	}

	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}

	@Override
	public Employee findById(int id) throws Exception {
		Employee employee = dao.findById(id);
		if(employee == null)
			throw new Exception("Employee ["+id+"] Not Found!");
		return employee;
	}

	@Transactional
	@Override
	public Employee save(Employee employee) {
		employee.setId(0);
		return dao.save(employee);
	}

	@Transactional
	@Override
	public Employee update(Employee employee) {
		return dao.update(employee);
	}

	@Transactional
	@Override
	public void delete(int id) throws Exception {
		Employee employee = findById(id);
		dao.delete(employee);
	}

}
