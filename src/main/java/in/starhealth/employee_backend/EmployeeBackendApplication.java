package in.starhealth.employee_backend;

import in.starhealth.employee_backend.model.Employee;
import in.starhealth.employee_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class EmployeeBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeBackendApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {

		Employee employee1 = new Employee();
		employee1.setFirstName("Mahesh");
		employee1.setLastName("Motale");
		employee1.setEmailID("mahesh.motale@starhealth.in");
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Akash");
		employee2.setLastName("Padir");
		employee2.setEmailID("akash.padir@starhealth.in");
		employeeRepository.save(employee2);

		Employee employee3 = new Employee();
		employee3.setFirstName("Suvarna");
		employee3.setLastName("Bhide");
		employee3.setEmailID("suvarna.bhide@starhealth.in");
		employeeRepository.save(employee3);

	}

	@Bean
	public RestTemplate restTemplate () {
		return new RestTemplate();
	}
}
