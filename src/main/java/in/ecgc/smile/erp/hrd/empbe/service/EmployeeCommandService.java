package in.ecgc.smile.erp.hrd.empbe.service;




import in.ecgc.smile.erp.hrd.empbe.model.Employee;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Employee Service interface
 * @version 1.1 30-April-20
 * @Author Architecture Team C-DAC Mumbai
 */

@EnableSwagger2
public interface EmployeeCommandService {
	
	/**
	   * Add new Employee to the Employee Master.
	   *
	   * @param employee      Employee employee
	   * @return result       which is boolean value
	*/	
	public boolean addEmployee(Employee employee);
	
	/**
	   * Delete particular Employee 
	   * 
	   * @param emp_id  Employee empId
	   * @return result true/false
	*/
	public boolean deleteEmployee(String empId);
}
