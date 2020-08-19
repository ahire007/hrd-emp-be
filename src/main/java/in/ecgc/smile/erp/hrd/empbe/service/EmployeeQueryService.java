package in.ecgc.smile.erp.hrd.empbe.service;

import java.util.List;

import in.ecgc.smile.erp.hrd.empbe.model.Employee;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Employee Query Service interface
 * @version 1.1 30-April-20
 * @Author Architecture Team C-DAC Mumbai
 */

@EnableSwagger2
public interface EmployeeQueryService {
	
	/**
	   * Request for fetch all Employees
	   * 
	   * @return A list of employees
	*/	
	public List<Employee> getAllEmployees();
	
	public boolean isEmployeeExist(String empId);

	
}
