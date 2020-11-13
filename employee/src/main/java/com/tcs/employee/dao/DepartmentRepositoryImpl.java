package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.utils.DBUtils;

public class DepartmentRepositoryImpl implements DepartmentRepository {

	public DepartmentRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static DepartmentRepository dao;
	
	public static DepartmentRepository getInstance() {
		if (dao == null) {
			dao = new DepartmentRepositoryImpl();
			return dao;
		} 
		return dao;
	}
	
	@Override
	public String addDepartment(Department department) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		int result = 0;
		connection = DBUtils.getConnection();
		String addDepartmentSQL = "INSERT INTO DEPARTMENT (deptId, orgId, deptName, empList) VALUES (?,?,?,?)";
		
		try {
			pst = connection.prepareStatement(addDepartmentSQL);
			pst.setLong(1, department.getId());
			pst.setLong(2, department.getOrganizationId());
			pst.setString(3, department.getName());
			String empsString = "";
			List<Employee> empList = department.getEmployees();
			for (Employee emp: empList) {
				empsString += emp.getId() + ",";
			}
			pst.setString(4, empsString);
			
			result = pst.executeUpdate();
			connection.commit();
			if (result > 0) {
				return "success";
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		} finally {
			DBUtils.closeConnection(connection);
		}
	}

	@Override
	public String updateDepartment(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDepartment(long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		int result = 0;
		connection = DBUtils.getConnection();
		String deleteDeptSQL = "DELETE FROM DEPARTMENT WHERE deptId=?";
		
		try {
			pst = connection.prepareStatement(deleteDeptSQL);
			pst.setLong(1, id);
			result = pst.executeUpdate();
			connection.commit();
			
			if (result > 0) {
				return "success";
			} else {
				return "fail";
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "fail";
		} finally {
			DBUtils.closeConnection(connection);
		}
	}

	@Override
	public Optional<Department> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Department>> findByOrganizationId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
