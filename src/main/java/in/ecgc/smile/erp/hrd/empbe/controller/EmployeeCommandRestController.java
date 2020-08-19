package in.ecgc.smile.erp.hrd.empbe.controller;


import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.ecgc.smile.erp.hrd.empbe.model.*;
import in.ecgc.smile.erp.hrd.empbe.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 *Employee Command Rest Controller class
 *
 *@version 1.1 30-April-20
 *@Author Architecture Team C-DAC Mumbai
 **/
@Api(value = "Employee Command Rest Controller")//Swagger annotation
@RestController
@RequestMapping("/api")
public class EmployeeCommandRestController {

	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeCommandRestController.class);
	
	@Autowired
	private EmployeeCommandService empService;
	/**
	   * Save Employee Data into Employee Master
	   * 
	   * @param employee   Employee employee-object
	   * @return result    true or false
	*/
	@ApiOperation(value = "Save an employee")						//Swagger Annotation 
	@PostMapping("/employee")
	public ResponseEntity<Boolean> saveEmployeeData(@RequestBody @Valid Employee employee,Locale locale, Model model) {
		LOGGER.debug("-->savingEmployeeData:{}",employee);
		boolean result = empService.addEmployee(employee);
		
	    return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
    /**
     * Delete Employee with particular ID
     *
     * @param EmpID			Employee Employee Id
     * @return				ResponseDto object
     */
    @ApiOperation(value = "Delete an employee")								//Swagger Annotation
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@ApiParam(value = "Employee Id from which employee object will delete from database table", required = true)
	                          @PathVariable("id") String empId){
		LOGGER.debug("-->deleteEmployeeWithId:{}",empId);
		
        boolean result=empService.deleteEmployee(empId);
		return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
	}
	
    //Note Swagger Annotation @Api, @ApiOperation ,@ApiParam etc.
}
