package net.codejava;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class CustomerDAOTest {
	private CustomerDAO dao;

	@BeforeEach
	void setUp() throws Exception {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=TacoLoco");
		dataSource.setUsername("tmds");
		dataSource.setPassword("tmds");
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		dao = new CustomerDAO(new JdbcTemplate(dataSource));
	}

	@Test
	void testList() {
		List<Customer> listCustomer = dao.list();
		assertFalse(listCustomer.isEmpty());
	}

	@Test
	void testSave() {
		Customer customer = new Customer("Nancy","Drew","Michigan");
		dao.save(customer);
	}

	@Test
	void testGet() {
		int id = 1;
		Customer customer = dao.get(id);
		assertNotNull(customer);
	}

	@Test
	void testUpdate() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setFirstName("Nancy M");
		customer.setLastName("Drew L");
		customer.setAddress("Texas");
		
		dao.update(customer);
	}

	@Test
	void testDelete() {
		int id = 1;
		dao.delete(id);
	}

}
