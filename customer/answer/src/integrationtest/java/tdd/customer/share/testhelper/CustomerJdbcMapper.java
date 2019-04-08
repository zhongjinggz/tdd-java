package tdd.customer.share.testhelper;

import org.springframework.jdbc.core.RowMapper;
import tdd.customer.domain.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerJdbcMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int i) throws SQLException {
        return new Customer(rs.getString("first_name")
                , rs.getString("last_name"));
    }
}
