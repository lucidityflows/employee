package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.DepartmentRepository;
import com.tcs.employee.dao.DepartmentRepositoryImpl;
import com.tcs.employee.model.Department;

public class DepartmentServiceImpl implements DepartmentService {

	private static DepartmentService dao;
	
	public DepartmentServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static DepartmentService getInstance() {
		if (dao==null) {
			dao = new DepartmentServiceImpl();
			return dao;
		}
		return dao;
	}
	
	DepartmentRepository deptRep = DepartmentRepositoryImpl.getInstance();
	
	@Override
	public String addDepartment(Department department) {
		// TODO Auto-generated method stub
		return deptRep.addDepartment(department);
	}

	@Override
	public String updateDepartment(long id) {
		// TODO Auto-generated method stub
		return deptRep.updateDepartment(id);
	}

	@Override
	public String deleteDepartment(long id) {
		// TODO Auto-generated method stub
		return deptRep.deleteDepartment(id);
	}

	@Override
	public Optional<Department> findById(long id) {
		// TODO Auto-generated method stub
		return deptRep.findById(id);
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		// TODO Auto-generated method stub
		return deptRep.getDepartments();
	}

	@Override
	public Optional<List<Department>> findByOrganizationId(long id) {
		// TODO Auto-generated method stub
		return deptRep.findByOrganizationId(id);
	}

}
