package com.homsdev.app.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.homsdev.app.domain.Customer;
import com.homsdev.app.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Customer> getAllCustomers() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Customer> result = jdbcTemplate.query("SELECT * FROM customers", params, new CustomerMapper());
		return result;
	}

	
	//Add new Customer to DB
	public void addNewCustomer(Customer customer) {
		String SQL = "Insert into customers (" + "ID," + "NAME," + "ADDRESS," + "NO_OF_ORDERS)"
				+ "values (:id,:name,:address,:NoOrders)";
		HashMap<String, Object> newCustomer = new HashMap<String, Object>();

		newCustomer.put("id", customer.getCustomerID());
		newCustomer.put("name", customer.getName());
		newCustomer.put("address", customer.getAddress());
		newCustomer.put("NoOrders", customer.getNoOfOrders());

		jdbcTemplate.update(SQL, newCustomer);
	}

	private static final class CustomerMapper implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerID(rs.getString("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setAddress(rs.getString("ADDRESS"));
			customer.setNoOfOrders(rs.getLong("NO_OF_ORDERS"));
			return customer;
		}
	}

}
