import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.model.Organization;
import com.tcs.employee.service.DepartmentService;
import com.tcs.employee.service.DepartmentServiceImpl;
import com.tcs.employee.service.OrganizationService;
import com.tcs.employee.service.OrganizationServiceImpl;

public class OrganizationMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int userChoice = 0;
		String result = null;
		Organization org = null;
		List<Organization> orgList = null;
		Optional<Organization> optOrg = null;
		Optional<List<Organization>> optOrgList = null;
		OrganizationService orgService = null;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(isr);
		System.out.println("Welcome to the Organization Application! \n\n");
		while (userChoice != 7) {
			showMenu();
			try {
				userChoice = Integer.parseInt((bfr.readLine()));
				
				switch(userChoice) {
				
				case 1:
					System.out.println("Organization Id: ");
					long orgId = Long.parseLong((bfr.readLine()));
					System.out.println("Organization Name: ");
					String orgName = bfr.readLine();
					System.out.println("Address: ");
					String address = bfr.readLine();
					System.out.println("Department Id List (deptIds separated by ','):");
					String deptListInput = bfr.readLine();
					String[] values = deptListInput.split(",");
					List<Department> deptList = new ArrayList<Department>();
					for (String deptId: values) {
						Department dept = new Department();
						dept.setId(Long.parseLong(deptId));
						deptList.add(dept);
					}
					System.out.println("Employee Id List (empIds separated by ','):");
					String empListInput = bfr.readLine();
					String[] values2 = empListInput.split(",");
					List<Employee> empList = new ArrayList<Employee>();
					for (String empId: values2) {
						Employee emp = new Employee();
						emp.setId(Long.parseLong(empId));
						empList.add(emp);
					}
					org = new Organization(orgId, orgName, address, deptList, empList);
					orgService = OrganizationServiceImpl.getInstance();
					result = orgService.addOrganization(org);
					System.out.println(result);
					break;
					
				case 2:
					System.out.println("Enter the organization id associated with the organization you want to update"
							+ "\nOrganization Id: ");
					long orgIdForUpdate = Long.parseLong((bfr.readLine()));
					break;
					
				case 3:
					System.out.println("Enter the organization id associated with the organization you want to delete."
							+ "\nOrganization Id: ");
					long orgIdForDeletion = Long.parseLong((bfr.readLine()));
					orgService = OrganizationServiceImpl.getInstance();
					result = orgService.deleteOrganization(orgIdForDeletion);
					System.out.println(result);
					break;
					
				case 4:
					System.out.println("Enter the organization id associated with the organization you want to retrieve."
							+ "\nOrganization Id: ");
					long orgIdForSearch = Long.parseLong((bfr.readLine()));
					
					orgService = OrganizationServiceImpl.getInstance();
					optOrg = orgService.findById(orgIdForSearch);
					if (optOrg.isPresent()) {
						System.out.println("Organization was retrieved!");
						org = optOrg.get();
						System.out.println(org);
					} else {
						System.out.println("Organization could not be retrieved.");
					}
					break;
					
				case 5:
					orgService = OrganizationServiceImpl.getInstance();
					optOrgList = orgService.getOrganizations();
					if (optOrgList.isPresent()) {
						System.out.println("All Organizations have been retrieved successfully "
								+ "\n");
						orgList = optOrgList.get();
						for (Organization organization: orgList) {
							System.out.println(organization);
						}
						
					} else {
						System.out.println("An Organization list could not be retrieved");
					}
					break;
					
				case 6:
					System.out.println("Enter the dept id associated with the organizations you want to retrieve."
							+ "\nDept Id: ");
					long deptIdForSearch = Long.parseLong((bfr.readLine()));
					
					orgService = OrganizationServiceImpl.getInstance();
					optOrgList = orgService.findByDepartmentId(deptIdForSearch);
					
					if (optOrgList.isPresent()) {
						System.out.println("All organizations in the department have been retrieved successfully "
								+ "\n");
						orgList = optOrgList.get();
						for (Organization organization: orgList) {
							System.out.println(organization);
						}
						
					} else {
						System.out.println("An Organization list from that department id could not be retrieved");
					}
					break;
					
				case 7:
					break;
				
				default:
					System.out.println("Invalid choice! Please select from 1-7 from the menu!");
					break;
				} // end of switch
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		System.out.println("Exiting program...");
	}
	
	public static void showMenu() {
		System.out.println("Choose an option from the following choices: \n"
				+ "\n 1 - Add an organization"
				+ "\n 2 - Update an organization "
				+ "\n 3 - Delete an organization "
				+ "\n 4 - Find an Organization by organization id"
				+ "\n 5 - Get all organizations"
				+ "\n 6 - Get all organizations by department id"
				+ "\n 7 - Exit"
				+ "\n\n Enter your choice as number 1 - 7: ");
	}

}
