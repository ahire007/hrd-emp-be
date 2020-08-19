package in.ecgc.smile.erp.hrd.empbe.exception;

public class EmployeeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -3226630364916511527L;
	private Object[] args; 
	
	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public EmployeeNotFoundException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
