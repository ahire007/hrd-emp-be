package in.ecgc.smile.erp.hrd.empbe.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import in.ecgc.smile.erp.hrd.empbe.model.Employee;



/**
 * Employee Repository implementation
 * @version 1.1 30-April-20
 * @Author Architecture Team C-DAC Mumbai
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	
	private JdbcOperations jdbcOperations;
	
	/* For parameterized query */
	private NamedParameterJdbcOperations namedParameterJdbcOperations;
	
	/*Constructor for autowire jdbc and datasource*/
	@Autowired
	public EmployeeDaoImpl(DataSource dataSource) {
		jdbcOperations = new JdbcTemplate(dataSource);
		namedParameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	/* Method for fetching all employee data */
	public List<Employee> allEmployeeData() {
		
		return jdbcOperations.query(EmployeeQueries.GET_ALL_EMPLOYEES,new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee=new Employee(); 
				employee.setEmpId(rs.getInt("emp_id"));
				employee.setFirstName(rs.getString("emp_fname"));
				employee.setLastName(rs.getString("emp_lname"));
				employee.setDob(rs.getDate("dob"));
				employee.setDoj(rs.getDate("doj"));
				return employee;
			}
			
		});
		
	}
	
	/* Method for saving employee data */
	public int saveEmployeeData(Employee employee) {
		
		  //Date dob=employee.getDob();
		  //Date doj=Date.valueOf(employee.getDoj());//converting string into sql date
		  Map<String, Object> namedParameters = new HashMap<String, Object>();
		  namedParameters.put("empId", employee.getEmpId());
		  namedParameters.put("firstName", employee.getFirstName());
		  namedParameters.put("midName", employee.getMidName());
		  namedParameters.put("lastName", employee.getLastName());
		  namedParameters.put("DOB", employee.getDob()); 
		  namedParameters.put("DOJ", employee.getDoj());
		  namedParameters.put("empType", employee.getEmpType());
		  namedParameters.put("designation", employee.getDesignation()); 
		  
		  return namedParameterJdbcOperations.update(EmployeeQueries.INSERT_EMPLOYEE_RECORD,namedParameters); 
	}

	/* Method for fetching all employee data */
	public List<Employee> viewEmployeeData(int emp_id){
		
		return jdbcOperations.query(EmployeeQueries.GET_EMPLOYEE_BY_ID,
				new Object[] { emp_id }, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setEmpId(rs.getInt("emp_id"));
				employee.setFirstName(rs.getString("emp_fname"));
				employee.setFirstName(rs.getString("emp_mname"));
				employee.setFirstName(rs.getString("emp_lname"));
				employee.setDob(rs.getDate("dob"));
				employee.setDoj(rs.getDate("doj"));
				employee.setDesignation(rs.getString("designation"));
				return employee;
			}
		});
		
	}
	
	/* Method for deleting employee with EMP_ID */
	public int deleteEmployee(String empId) {
		
		return jdbcOperations.update(EmployeeQueries.DELETE_EMPLOYEE,empId);
	}

	@Override
	public int isExist(String empId) {
		return jdbcOperations.queryForObject(EmployeeQueries.IS_EMPLOYEE_EXIST,new Object[] {empId},Integer.class);
	}
	
	

}
