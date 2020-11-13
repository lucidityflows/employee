package com.tcs.employee.service;

import java.util.List;
import java.util.Optional;

import com.tcs.employee.dao.OrganizationRepository;
import com.tcs.employee.dao.OrganizationRepositoryImpl;
import com.tcs.employee.model.Organization;

public class OrganizationServiceImpl implements OrganizationService {

	private static OrganizationService dao;
	
	public OrganizationServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static OrganizationService getInstance() {
		if (dao==null) {
			dao = new OrganizationServiceImpl();
			return dao;
		}
		return dao;
	}
	
	OrganizationRepository orgRep = OrganizationRepositoryImpl.getInstance();
	
	@Override
	public String addOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return orgRep.addOrganization(organization);
	}

	@Override
	public String updateOrganization(long id) {
		// TODO Auto-generated method stub
		return orgRep.updateOrganization(id);
	}

	@Override
	public String deleteOrganization(long id) {
		// TODO Auto-generated method stub
		return orgRep.deleteOrganization(id);
	}

	@Override
	public Optional<Organization> findById(long id) {
		// TODO Auto-generated method stub
		return orgRep.findById(id);
	}

	@Override
	public Optional<List<Organization>> getOrganizations() {
		// TODO Auto-generated method stub
		return orgRep.getOrganizations();
	}

	@Override
	public Optional<List<Organization>> findByDepartmentId(long id) {
		// TODO Auto-generated method stub
		return orgRep.findByDepartmentId(id);
	}

}
