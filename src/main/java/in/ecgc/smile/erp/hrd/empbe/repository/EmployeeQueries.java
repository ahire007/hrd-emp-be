package in.ecgc.smile.erp.hrd.empbe.repository;

/**
 * A util interface for working with Queries.
 * @version 1.0 31-March-20
 * @Author Architecture Team C-DAC Mumbai
 */
public interface EmployeeQueries {
	public static final String INSERT_EMPLOYEE_RECORD = "INSERT INTO public.\"Employee\" (emp_id,emp_fname, emp_mname, emp_lname, dob, doj,emp_type, designation) VALUES (:empId,:firstName, :midName, :lastName, :DOB, :DOJ, :empType, :designation)";
	public static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM public.\"Employee\" where emp_id=:emp_id";
	public static final String GET_ALL_EMPLOYEES = "SELECT emp_id, emp_fname, emp_mname, emp_lname, designation, dob, doj,emp_type\r\n" + 
			"	FROM public.\"Employee\"";
	public static final String UPDATE_EMPLOYEE = "UPDATE public.\"Employee\"\r\n" + 
			"	SET emp_fname=:firstName, emp_mname=:midName, emp_lname=:lastName, designation=:designation, dob=:DOB, doj=:DOJ, emp_type=:empType\r\n" + 
			"	WHERE emp_id=emp_id";
	public static final String DELETE_EMPLOYEE = "DELETE FROM public.\"Employee\"\r\n" + 
			"	WHERE emp_id=?";
	
	public static final String IS_EMPLOYEE_EXIST="SELECT count(emp_id) from public.\"Employee\" WHERE emp_id=?;";
	
}
