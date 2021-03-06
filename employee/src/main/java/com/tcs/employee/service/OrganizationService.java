package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Organization;

public interface OrganizationService {

	public String addOrganization(Organization organization);
	public String updateOrganization(long id);
	public String deleteOrganization(long id);
	public Optional<Organization> findById(long id);
	public Optional<List<Organization>> getOrganizations();
	public Optional<List<Organization>> findByDepartmentId(long id);
}
