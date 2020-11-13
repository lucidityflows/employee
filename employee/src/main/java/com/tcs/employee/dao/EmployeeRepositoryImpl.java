package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Employee;
import com.tcs.employee.utils.DBUtils;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	public EmployeeRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static EmployeeRepository dao;
	
	public static EmployeeRepository getInstance() {
		if (dao == null) {
			dao = new EmployeeRepositoryImpl();
			//System.out.println("Creating new EmployeeRepositoryImpl instance!");
			return dao;
		}
		
		//System.out.println("EmployeeRepositoryImpl already exists. Returning that instance.");
		return dao;
	}
	
	@Override
	public String addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		int result = 0;
		connection = DBUtils.getConnection();
		String addEmployeeSQL = "INSERT INTO EMPLOYEE (empId, orgId, deptId, name, age, position) VALUES (?,?,?,?,?,?)";
		
		try {
			pst = connection.prepareStatement(addEmployeeSQL);
			pst.setLong(1, employee.getId());
			pst.setLong(2, employee.getOrganizationId());
			pst.setLong(3,  employee.getDepartmentId());
			pst.setString(4, employee.getName());
			pst.setInt(5, employee.getAge());
			pst.setString(6, employee.getPosition());
			
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
	public String updateEmployee(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEmployee(long id) {
		Connection connection = null;
		PreparedStatement pst = null;
		int result = 0;
		connection = DBUtils.getConnection();
		String deleteEmployeeSQL = "DELETE FROM EMPLOYEE WHERE empId=?";
		
		try {
			pst = connection.prepareStatement(deleteEmployeeSQL);
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
	public Optional<Employee> findById(long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Employee employee = null;
		connection = DBUtils.getConnection();
		String findEmployeeSQL = "SELECT * FROM EMPLOYEE WHERE empId=?";
		
		try {
			pst = connection.prepareStatement(findEmployeeSQL);
			pst.setLong(1, id);
			
			rs = pst.executeQuery();
			connection.commit();
			if (rs.next()) {
				employee = new Employee();
				employee.setId(rs.getLong("empId"));
				employee.setOrganizationId(rs.getLong("orgId"));
				employee.setDepartmentId(rs.getLong("deptId"));
				employee.setName(rs.getString("name"));
				employee.setAge(rs.getInt("age"));
				employee.setPosition(rs.getString("position"));
				return Optional.of(employee);
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
		//return Optional.of(employee);
		
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Employee employee = null;
		List<Employee> empList = null;
		connection = DBUtils.getConnection();
		String getAllEmployeeSQL = "SELECT * FROM EMPLOYEE";
		
		try {
			pst = connection.prepareStatement(getAllEmployeeSQL);
			rs = pst.executeQuery();
			connection.commit();
			
			if (rs.next()) {
				empList = new ArrayList<Employee>();
				
				do {
					employee = new Employee();
					employee.setId(rs.getLong("empId"));
					employee.setOrganizationId(rs.getLong("orgId"));
					employee.setDepartmentId(rs.getLong("deptId"));
					employee.setName(rs.getString("name"));
					employee.setAge(rs.getInt("age"));
					employee.setPosition(rs.getString("position"));
					empList.add(employee);
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
		return Optional.of(empList);
	}

	@Override
	public Optional<List<Employee>> findByOrganizationId(long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Employee employee = null;
		List<Employee> empList = null;
		connection = DBUtils.getConnection();
		String getAllEmployeeSQL = "SELECT * FROM EMPLOYEE WHERE orgId=?";
		
		try {
			pst = connection.prepareStatement(getAllEmployeeSQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			connection.commit();
			
			if (rs.next()) {
				empList = new ArrayList<Employee>();
				
				do {
					employee = new Employee();
					employee.setId(rs.getLong("empId"));
					employee.setOrganizationId(rs.getLong("orgId"));
					employee.setDepartmentId(rs.getLong("deptId"));
					employee.setName(rs.getString("name"));
					employee.setAge(rs.getInt("age"));
					employee.setPosition(rs.getString("position"));
					empList.add(employee);
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
		return Optional.of(empList);
	}

}
