package in.ecgc.smile.erp.hrd.empbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.smile.erp.hrd.empbe.model.Employee;
import in.ecgc.smile.erp.hrd.empbe.repository.EmployeeDao;



/**
 * Employee Service implementation
 *  
 * @version 1.1 31-March-20
 * @Author Architecture Team C-DAC Mumbai
 */
@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {
	
	@Autowired(required = true)
	private EmployeeDao dao;
	
	public EmployeeDao getDao() {
		return dao;
	}

	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}
	
	
	@Override
	public boolean addEmployee(Employee employee) {
		int result = getDao().saveEmployeeData(employee);
		if(result==1) {
		return true;
		}
		return false;
		
	}

	@Override
	public boolean deleteEmployee(String empId) {
		int result =getDao().deleteEmployee(empId);
		if(result==1) {
			return true;
		}
			return false;
	}
}
