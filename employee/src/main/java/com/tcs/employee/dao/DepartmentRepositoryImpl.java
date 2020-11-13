package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;
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
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Department dept = null;
		connection = DBUtils.getConnection();
		String findDeptSQL = "SELECT * FROM DEPARTMENT WHERE deptId=?";
		
		try {
			pst = connection.prepareStatement(findDeptSQL);
			pst.setLong(1, id);
			
			rs = pst.executeQuery();
			connection.commit();
			if (rs.next()) {
				dept = new Department();
				dept.setId(rs.getLong("deptId"));
				dept.setOrganizationId(rs.getLong("orgId"));
				dept.setName(rs.getString("deptName"));
				String[] values = rs.getString("empList").split(",");
				List<Employee> empList = new ArrayList<Employee>();
				for (String empId: values) {
					Employee emp = new Employee();
					emp.setId(Long.parseLong(empId));
					empList.add(emp);
				}
				dept.setEmployees(empList);
				return Optional.of(dept);
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Department dept = null;
		List<Department> deptList = null;
		connection = DBUtils.getConnection();
		String getAllDeptSQL = "SELECT * FROM DEPARTMENT";
		
		try {
			pst = connection.prepareStatement(getAllDeptSQL);
			rs = pst.executeQuery();
			connection.commit();
			
			if (rs.next()) {
				deptList = new ArrayList<Department>();
				
				do {
					dept = new Department();
					dept.setId(rs.getLong("deptId"));
					dept.setOrganizationId(rs.getLong("orgId"));
					dept.setName(rs.getString("deptName"));
					String[] values = rs.getString("empList").split(",");
					List<Employee> empList = new ArrayList<Employee>();
					for (String empId: values) {
						Employee emp = new Employee();
						emp.setId(Long.parseLong(empId));
						empList.add(emp);
					}
					deptList.add(dept);
				} while (rs.next());
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		return Optional.of(deptList);
	}

	@Override
	public Optional<List<Department>> findByOrganizationId(long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Department dept = null;
		List<Department> deptList = null;
		connection = DBUtils.getConnection();
		String getAllDeptSQL = "SELECT * FROM DEPARTMENT WHERE orgId=?";
		
		try {
			pst = connection.prepareStatement(getAllDeptSQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			connection.commit();
			
			if (rs.next()) {
				deptList = new ArrayList<Department>();
				
				do {
					dept = new Department();
					dept.setId(rs.getLong("deptId"));
					dept.setOrganizationId(rs.getLong("orgId"));
					dept.setName(rs.getString("deptName"));
					String[] values = rs.getString("empList").split(",");
					List<Employee> empList = new ArrayList<Employee>();
					for (String empId: values) {
						Employee emp = new Employee();
						emp.setId(Long.parseLong(empId));
						empList.add(emp);
					}
					deptList.add(dept);
				} while (rs.next());
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return Optional.empty();
		} finally {
			DBUtils.closeConnection(connection);
		}
		return Optional.of(deptList);
	}

}
