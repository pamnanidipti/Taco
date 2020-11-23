package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CustomerDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Customer> list() {
		String sql = "SELECT * FROM CUSTOMER";
		List<Customer> listCustomer = jdbcTemplate.query(sql, 
				BeanPropertyRowMapper.newInstance(Customer.class));
		return listCustomer;
	}

	public void save(Customer customer) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("customer").usingColumns("firstname","lastname","address");
		
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		insertActor.execute(param);
	}

	public Customer get(int id) {
		String sql = "SELECT * FROM CUSTOMER WHERE Id = ?";
		Object[] args = {id};
		Customer customer = jdbcTemplate.queryForObject(sql, args,
				BeanPropertyRowMapper.newInstance(Customer.class));
		return customer;
	}

	public void update(Customer customer) {
		String sql = "UPDATE CUSTOMER SET firstname=:firstName, lastname=:lastName, address=:address WHERE id=:id";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		template.update(sql, param);
	}

	public void delete(int id) {
		String sql = "DELETE FROM CUSTOMER WHERE id = ?";
		jdbcTemplate.update(sql,id);
	}
}
