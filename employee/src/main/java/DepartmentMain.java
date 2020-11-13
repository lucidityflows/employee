import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Department;
import com.tcs.employee.model.Employee;
import com.tcs.employee.service.DepartmentService;
import com.tcs.employee.service.DepartmentServiceImpl;


public class DepartmentMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int userChoice = 0;
		String result = null;
		Department dept = null;
		List<Department> deptList = null;
		Optional<Department> optDept = null;
		Optional<List<Department>> optDeptList = null;
		DepartmentService deptService = null;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(isr);
		System.out.println("Welcome to the Department Application! \n\n");
		while (userChoice != 7) {
			showMenu();
			try {
				userChoice = Integer.parseInt((bfr.readLine()));
				
				switch(userChoice) {
				
				case 1:
					System.out.println("Department Id: ");
					long deptId = Long.parseLong((bfr.readLine()));
					System.out.println("Organization Id: ");
					long orgId = Long.parseLong((bfr.readLine()));
					System.out.println("Department Name: ");
					String deptName = bfr.readLine();
					System.out.println("Employee Id List (empIds separated by ','):");
					String empListInput = bfr.readLine();
					String[] values = empListInput.split(",");
					List<Employee> empList = new ArrayList<Employee>();
					for (String empId: values) {
						Employee emp = new Employee();
						emp.setId(Long.parseLong(empId));
						empList.add(emp);
					}
					
					dept = new Department(deptId, orgId, deptName, empList);
					deptService = DepartmentServiceImpl.getInstance();
					result = deptService.addDepartment(dept);
					System.out.println(result);
					break;
					
				case 2:
					System.out.println("Enter the department id associated with the department you want to update"
							+ "\nDepartment Id: ");
					long deptIdForUpdate = Long.parseLong((bfr.readLine()));
					break;
					
				case 3:
					System.out.println("Enter the department id associated with the department you want to delete."
							+ "\nDepartment Id: ");
					long deptIdForDeletion = Long.parseLong((bfr.readLine()));
					deptService = DepartmentServiceImpl.getInstance();
					result = deptService.deleteDepartment(deptIdForDeletion);
					System.out.println(result);
					break;
					
				case 4:
					System.out.println("Enter the department id associated with the department you want to retrieve."
							+ "\nDepartment Id: ");
					long deptIdForSearch = Long.parseLong((bfr.readLine()));
					
					deptService = DepartmentServiceImpl.getInstance();
					optDept = deptService.findById(deptIdForSearch);
					if (optDept.isPresent()) {
						System.out.println("Department was retrieved!");
						dept = optDept.get();
						System.out.println(dept);
					} else {
						System.out.println("Department could not be retrieved.");
					}
					break;
					
				case 5:
					deptService = DepartmentServiceImpl.getInstance();
					optDeptList = deptService.getDepartments();
					if (optDeptList.isPresent()) {
						System.out.println("All Departments have been retrieved successfully "
								+ "\n");
						deptList = optDeptList.get();
						for (Department department: deptList) {
							System.out.println(department);
						}
						
					} else {
						System.out.println("A Department list could not be retrieved");
					}
					break;
					
				case 6:
					System.out.println("Enter the org id associated with the departments you want to retrieve."
							+ "\nEmployee Id: ");
					long orgIdForSearch = Long.parseLong((bfr.readLine()));
					
					deptService = DepartmentServiceImpl.getInstance();
					optDeptList = deptService.findByOrganizationId(orgIdForSearch);
					
					if (optDeptList.isPresent()) {
						System.out.println("All Departments in the organization have been retrieved successfully "
								+ "\n");
						deptList = optDeptList.get();
						for (Department department: deptList) {
							System.out.println(department);
						}
						
					} else {
						System.out.println("A Department list from that organization could not be retrieved");
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
				+ "\n 1 - Add a department"
				+ "\n 2 - Update a department "
				+ "\n 3 - Delete an department "
				+ "\n 4 - Find a Department by department id"
				+ "\n 5 - Get all departments"
				+ "\n 6 - Get all departments by organization id"
				+ "\n 7 - Exit"
				+ "\n\n Enter your choice as number 1 - 7: ");
	}
	
}
