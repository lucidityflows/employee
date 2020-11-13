package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.EmployeeRepository;
import com.tcs.employee.dao.EmployeeRepositoryImpl;
import com.tcs.employee.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	private static EmployeeService dao;

	public EmployeeServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static EmployeeService getInstance() {
		if (dao==null) {
			dao = new EmployeeServiceImpl();
			//System.out.println("Creating a new EmployeeServiceImpl instance!");
			return dao;
		}
		
		//System.out.println("EmployeeServiceImpl already exists. Returning that instance!");
		return dao;
	}
	
	EmployeeRepository empRep = EmployeeRepositoryImpl.getInstance();
	
	@Override
	public String addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRep.addEmployee(employee);
	}

	@Override
	public String updateEmployee(long id) {
		// TODO Auto-generated method stub
		return empRep.updateEmployee(id);
	}

	@Override
	public String deleteEmployee(long id) {
		// TODO Auto-generated method stub
		return empRep.deleteEmployee(id);
	}

	@Override
	public Optional<Employee> findById(long id) {
		// TODO Auto-generated method stub
		return empRep.findById(id);
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		// TODO Auto-generated method stub
		return empRep.getEmployees();
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		// TODO Auto-generated method stub
		return empRep.findByOrganizationId(id);
	}

}
