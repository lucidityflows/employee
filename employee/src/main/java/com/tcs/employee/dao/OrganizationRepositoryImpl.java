package com.tcs.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;
import com.tcs.employee.utils.DBUtils;

public class OrganizationRepositoryImpl implements OrganizationRepository {

	public OrganizationRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static OrganizationRepository dao;
	
	public static OrganizationRepository getInstance() {
		if (dao == null) {
			dao = new OrganizationRepositoryImpl();
			return dao;
		}
		return dao;
	}
	
	@Override
	public String addOrganization(Organization organization) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		int result = 0;
		connection = DBUtils.getConnection();
		String addOrganizationSQL = "INSERT INTO ORGANIZATION (orgId, orgName, address, departments, employees) VALUES (?,?,?,?,?)";
		
		try {
			pst = connection.prepareStatement(addOrganizationSQL);
			pst.setLong(1, organization.getId());
			pst.setString(2, organization.getName());
			pst.setString(3,  organization.getAddress());
			String departmentsString = "";
			List<Department> depList = organization.getDepartments();
			for (Department dept: depList) {
				departmentsString += dept.getId() + ",";
			}
			pst.setString(4, departmentsString);
			String employeesString = "";
			List<Employee> empList = organization.getEmployees();
			for (Employee emp: empList) {
				employeesString += emp.getId() + ",";
			}
			pst.setString(5, employeesString); 
			
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
	public String updateOrganization(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrganization(long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		int result = 0;
		connection = DBUtils.getConnection();
		String deleteOrgSQL = "DELETE FROM ORGANIZATION WHERE orgId=?";
		
		try {
			pst = connection.prepareStatement(deleteOrgSQL);
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
	public Optional<Organization> findById(long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Organization organization = null;
		connection = DBUtils.getConnection();
		String findOrgSQL = "SELECT * FROM ORGANIZATION WHERE orgId=?";
		
		try {
			pst = connection.prepareStatement(findOrgSQL);
			pst.setLong(1, id);
			
			rs = pst.executeQuery();
			connection.commit();
			if (rs.next()) {
				organization = new Organization();
				organization.setId(rs.getLong("orgId"));
				organization.setName(rs.getString("orgName"));
				organization.setAddress(rs.getString("address"));
				String[] values = rs.getString("departments").split(",");
				List<Department> depList = new ArrayList<Department>();
				for (String deptId: values) {
					Department dep = new Department();
					dep.setId(Long.parseLong(deptId));
					depList.add(dep);
				}
				organization.setDepartments(depList);
				String[] values2 = rs.getString("employees").split(",");
				List<Employee> empList = new ArrayList<Employee>();
				for (String empId: values2) {
					Employee emp = new Employee();
					emp.setId(Long.parseLong(empId));
					empList.add(emp);
				}
				organization.setEmployees(empList);
				return Optional.of(organization);
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
	public Optional<List<Organization>> getOrganizations() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Organization org = null;
		List<Organization> orgList = null;
		connection = DBUtils.getConnection();
		String getAllOrgSQL = "SELECT * FROM ORGANIZATION";
		
		try {
			pst = connection.prepareStatement(getAllOrgSQL);
			rs = pst.executeQuery();
			connection.commit();
			
			if (rs.next()) {
				orgList = new ArrayList<Organization>();
				
				do {
					org = new Organization();
					org.setId(rs.getLong("orgId"));
					org.setName(rs.getString("orgName"));
					org.setAddress(rs.getString("address"));
					String[] values = rs.getString("departments").split(",");
					List<Department> depList = new ArrayList<Department>();
					for (String deptId: values) {
						Department dep = new Department();
						dep.setId(Long.parseLong(deptId));
						depList.add(dep);
					}
					org.setDepartments(depList);
					String[] values2 = rs.getString("employees").split(",");
					List<Employee> empList = new ArrayList<Employee>();
					for (String empId: values2) {
						Employee emp = new Employee();
						emp.setId(Long.parseLong(empId));
						empList.add(emp);
					}
					org.setEmployees(empList);
					orgList.add(org);
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
		return Optional.of(orgList);
	}

	@Override
	public Optional<List<Organization>> findByDepartmentId(long id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Organization org = null;
		List<Organization> orgList = null;
		connection = DBUtils.getConnection();
		String getAllOrgSQL = "SELECT * FROM ORGANIZATION WHERE orgId=?";
		
		try {
			pst = connection.prepareStatement(getAllOrgSQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			connection.commit();
			
			if (rs.next()) {
				orgList = new ArrayList<Organization>();
				
				do {
					org = new Organization();
					org.setId(rs.getLong("orgId"));
					org.setName(rs.getString("orgName"));
					org.setAddress(rs.getString("address"));
					String[] values = rs.getString("departments").split(",");
					List<Department> depList = new ArrayList<Department>();
					for (String deptId: values) {
						Department dep = new Department();
						dep.setId(Long.parseLong(deptId));
						depList.add(dep);
					}
					org.setDepartments(depList);
					String[] values2 = rs.getString("employees").split(",");
					List<Employee> empList = new ArrayList<Employee>();
					for (String empId: values2) {
						Employee emp = new Employee();
						emp.setId(Long.parseLong(empId));
						empList.add(emp);
					}
					org.setEmployees(empList);
					orgList.add(org);
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
		return Optional.of(orgList);
	}

}
