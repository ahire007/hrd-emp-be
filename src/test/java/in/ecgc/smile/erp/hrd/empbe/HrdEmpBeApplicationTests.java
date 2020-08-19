package in.ecgc.smile.erp.hrd.empbe;

import static in.ecgc.smile.erp.hrd.empbe.JsonConverter.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.ecgc.smile.erp.hrd.empbe.HrdEmpBeApplication;
import in.ecgc.smile.erp.hrd.empbe.model.Employee;

/**
* Hrd-emp-be Test class
* 
* @version 1.1 30-April-20
* @Author Architecture Team C-DAC Mumbai
*/

/* Note: only business logic should be checked in this class not functional 
 * 		for example only we put the functional(adding,deleting,updating) testing but developer should not do this */

@SpringBootTest(classes = HrdEmpBeApplication.class)
public class HrdEmpBeApplicationTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	//@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
	
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test(priority = 1)
	public void addEmployee() throws Exception {
		Employee emp = new Employee();
		emp.setEmpId(123);
		emp.setFirstName("Vinayak");
		emp.setMidName("V.");
		emp.setLastName("Mahajan");
		emp.setDob(null);
		emp.setDoj(null);
		emp.setDesignation("Project Engineer");
		emp.setEmpType("Consoldated");
		
		System.out.println(asJsonString(emp));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.content(asJsonString(emp)))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
		
	}
	
	@Test(priority = 2)
	public void deleteEmployee() throws Exception {
		Employee emp = new Employee();
		emp.setEmpId(123);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{id}",emp.getEmpId())
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.content(asJsonString(emp)))
		.andExpect(status().isAccepted())
		.andExpect(MockMvcResultMatchers.jsonPath("$").value(true));
	}
	
	@Test(priority = 3)
	public void viewAllEmployees() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/api/employees").contentType(MediaType.APPLICATION_STREAM_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}