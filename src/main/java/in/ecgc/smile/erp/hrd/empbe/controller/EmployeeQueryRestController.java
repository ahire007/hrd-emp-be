package in.ecgc.smile.erp.hrd.empbe.controller;


import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.converters.Auto;

import in.ecgc.smile.erp.hrd.empbe.model.Employee;
import in.ecgc.smile.erp.hrd.empbe.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 *Employee Controller class
 *
 *@version 1.1 30-April-20
 *@Author Architecture Team C-DAC Mumbai
 *
 **/
@Api(value = "Employee Query Controller")//Swagger annotation
@RestController
@RequestMapping("/api")
public class EmployeeQueryRestController {

	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeQueryRestController.class);

	@Autowired
	private EmployeeQueryService empService;
    
	@Autowired
	Environment env;
	/**
	 * Returns List of Employees
	 *
	 * @param EmpID			Employee Employee Id
	 * @param FirstName     Employee First Name
	 * @param Designation	Employee Designation
	 * @param Employee_type Employee Type
	 * @return
	 */
	@GetMapping("/serverStatus")
	public String serverStatus()
	{
		return "BE working on Port No" + env.getProperty("local.server.port");
	}
	@ApiOperation(value = "View a list of available employees", response = List.class)   	//Swagger Annotation
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> employeeList=empService.getAllEmployees();
		LOGGER.debug("-->listAllEmployeess:{}",employeeList);
		
		return new ResponseEntity<>(employeeList,HttpStatus.OK); 
	}

	@ApiOperation(value = "To check Employee is exist or not", response = Boolean.class)   
	@GetMapping("/employee/{id}")
	public ResponseEntity<Boolean> isEmployeeExist(@PathVariable("id") String empId){
		
		boolean result=empService.isEmployeeExist(empId);
		LOGGER.debug("-->employeeExist:{}",result);
		return new ResponseEntity<>(result,HttpStatus.OK); 
	}
	
	//Note Swagger Annotation @Api, @ApiOperation ,@ApiParam etc.
}
