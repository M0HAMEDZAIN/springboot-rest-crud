package com.spring.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.employee.entity.Employee;

import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	EntityManager entity;

	@Autowired
	public EmployeeDaoImpl(EntityManager entityManager) {
		this.entity = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		return entity.createQuery("FROM Employee", Employee.class).getResultList();
	}

	@Override
	public Employee findById(int id) {
		return entity.find(Employee.class, id);
	}

	@Override
	public Employee save(Employee employee) {		
		return entity.merge(employee);
	}

	@Override
	public Employee update(Employee employee) {
		return entity.merge(employee);
	}

	@Override
	public void delete(Employee employee) {
		entity.remove(employee);
	}

}
