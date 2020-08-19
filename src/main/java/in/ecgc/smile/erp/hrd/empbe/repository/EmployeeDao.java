package in.ecgc.smile.erp.hrd.empbe.repository;

import java.util.List;

import in.ecgc.smile.erp.hrd.empbe.model.Employee;

/**
 * Employee Repository interface
 * @version 1.1 30-April-20
 * @Author Architecture Team C-DAC Mumbai
 */
public interface EmployeeDao {

	/* Method for saving Employee Data */
	public int saveEmployeeData(Employee employee);
	
	/* Method for Fetching All Employee Data */
	public List<Employee> allEmployeeData();
	
	/* Method for delete particular Employee By Emp_ID */
	public int deleteEmployee(String emp_id);
	
	public int isExist(String empId);
}
