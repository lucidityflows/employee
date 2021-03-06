package com.tcs.employee.dao;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Employee;

public interface EmployeeRepository {
	
	public String addEmployee(Employee employee);
	public String updateEmployee(long id);
	public String deleteEmployee(long id);
	public Optional<Employee> findById(long id);
	public Optional<List<Employee>> getEmployees();
	public Optional<List<Employee>> findByOrganizationId(long id);
}
