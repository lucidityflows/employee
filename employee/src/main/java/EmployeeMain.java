import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import com.tcs.employee.model.Employee;
import com.tcs.employee.service.EmployeeService;
import com.tcs.employee.service.EmployeeServiceImpl;

public class EmployeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int userChoice = 0;
		String result = null;
		Employee employee = null;
		List<Employee> empList = null;
		Optional<Employee> optEmployee = null;
		Optional<List<Employee>> optEmpList = null;
		EmployeeService employeeService = null;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(isr);
		System.out.println("Welcome to the Employee Application! \n\n");
		while (userChoice != 7) {
			showMenu();
			try {
				userChoice = Integer.parseInt((bfr.readLine()));
				
				switch(userChoice) {
				
				case 1:
					System.out.println("Employee Id: ");
					long empId = Long.parseLong((bfr.readLine()));
					System.out.println("Organization Id: ");
					long orgId = Long.parseLong((bfr.readLine()));
					System.out.println("Department Id: ");
					long deptId = Long.parseLong((bfr.readLine()));
					System.out.println("Name: ");
					String name = bfr.readLine();
					System.out.println("Age: ");
					int age = Integer.parseInt((bfr.readLine()));
					System.out.println("Position: ");
					String position = bfr.readLine();
					
					employee = new Employee(empId, orgId, deptId, name, age, position);
					employeeService = EmployeeServiceImpl.getInstance();
					result = employeeService.addEmployee(employee);
					System.out.println(result);
					break;
					
				case 2:
					System.out.println("Enter the employee id associated with the employee you want to update"
							+ "\nEmployee Id: ");
					long empIdForUpdate = Long.parseLong((bfr.readLine()));
					break;
					
				case 3:
					System.out.println("Enter the employee id associated with the employee you want to delete."
							+ "\nEmployee Id: ");
					long empIdForDeletion = Long.parseLong((bfr.readLine()));
					employeeService = EmployeeServiceImpl.getInstance();
					result = employeeService.deleteEmployee(empIdForDeletion);
					System.out.println(result);
					break;
					
				case 4:
					System.out.println("Enter the employee id associated with the employee you want to retrieve."
							+ "\nEmployee Id: ");
					long empIdForSearch = Long.parseLong((bfr.readLine()));
					
					employeeService = EmployeeServiceImpl.getInstance();
					optEmployee = employeeService.findById(empIdForSearch);
					if (optEmployee.isPresent()) {
						System.out.println("Employee was retrieved!");
						employee = optEmployee.get();
						System.out.println(employee);
					} else {
						System.out.println("Employee could not be retrieved.");
					}
					break;
					
				case 5:
					employeeService = EmployeeServiceImpl.getInstance();
					optEmpList = employeeService.getEmployees();
					if (optEmpList.isPresent()) {
						System.out.println("All Employees have been retrieved successfully "
								+ "\n");
						empList = optEmpList.get();
						for (Employee emp: empList) {
							System.out.println(emp);
						}
						// optEmpList.stream().forEach(System.out::println);
					} else {
						System.out.println("An Employee list could not be retrieved");
					}
					break;
					
				case 6:
					System.out.println("Enter the employee id associated with the employee you want to retrieve."
							+ "\nEmployee Id: ");
					long orgIdForSearch = Long.parseLong((bfr.readLine()));
					
					employeeService = EmployeeServiceImpl.getInstance();
					optEmpList = employeeService.findByOrganizationId(orgIdForSearch);
					
					if (optEmpList.isPresent()) {
						System.out.println("All Employees in the organization have been retrieved successfully "
								+ "\n");
						empList = optEmpList.get();
						for (Employee emp: empList) {
							System.out.println(emp);
						}
						// optEmpList.stream().forEach(System.out::println);
					} else {
						System.out.println("An Employee list from that organization could not be retrieved");
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
				+ "\n 1 - Add an employee"
				+ "\n 2 - Update an employee "
				+ "\n 3 - Delete an employee "
				+ "\n 4 - Find an Employee by employee id"
				+ "\n 5 - Get all employees"
				+ "\n 6 - Get all employees by organization id"
				+ "\n 7 - Exit"
				+ "\n\n Enter your choice as number 1 - 7: ");
	}

}
