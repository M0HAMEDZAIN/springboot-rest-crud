package com.spring.employee.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.spring.employee.dao.EmployeeDao;
import com.spring.employee.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao dao;
	ObjectMapper mapper;

	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.dao = employeeDao;
		mapper = new ObjectMapper();
	}

	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}

	@Override
	public Employee findById(int id) throws Exception {
		Employee employee = dao.findById(id);
		if (employee == null)
			throw new Exception("Employee [" + id + "] Not Found!");
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

	@Transactional
	@Override
	public Employee patch(int id, Map<String, Object> patchPayload) throws Exception {
		if (patchPayload.containsKey("id"))
			throw new Exception("Employee id not allowed in request body");

		Employee source = findById(id);
		Employee patched = apply(source, patchPayload);

		return dao.update(patched);
	}

	private Employee apply(Employee source, Map<String, Object> patchPayload) throws Exception {
		ObjectReader updater = mapper.readerForUpdating(source);
		return updater.readValue(mapper.writeValueAsString(patchPayload));
	}

//  another way
//	private Employee apply2(Employee source, Map<String, Object> patchPayload) throws Exception {
//		ObjectNode node1 = mapper.convertValue(source, ObjectNode.class);
//		ObjectNode node2 = mapper.convertValue(patchPayload, ObjectNode.class);
//		node1.setAll(node2);
//		return mapper.convertValue(node1, Employee.class);
//	}

}
