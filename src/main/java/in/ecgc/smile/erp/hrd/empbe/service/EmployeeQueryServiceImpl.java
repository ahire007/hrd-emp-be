package in.ecgc.smile.erp.hrd.empbe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.ecgc.smile.erp.hrd.empbe.model.Employee;
import in.ecgc.smile.erp.hrd.empbe.repository.EmployeeDao;



/**
 * Employee Service implementation
 *  
 * @version 1.0 31-March-20
 * @Author Architecture Team C-DAC Mumbai
 */
@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {
	
	@Autowired(required = true)
	private EmployeeDao dao;
	
	public EmployeeDao getDao() {
		return dao;
	}

	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return getDao().allEmployeeData();	
		
	}

	@Override
	public boolean isEmployeeExist(String empId) {
		
		return getDao().isExist(empId)==1?true:false;
	}
}
