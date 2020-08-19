package in.ecgc.smile.erp.hrd.empbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * HRD-BE-Service
 * 
 * @version 1.1 30-April-20
 * @Author Architecture Team C-DAC Mumbai
 * 
 * */

@SpringBootApplication(scanBasePackages = "in")
@EnableDiscoveryClient  // To register with Eureka Server

//@RibbonClient(name = "hrd-emp-be")
public class HrdEmpBeApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(HrdEmpBeApplication.class, args);
	}

	

}
