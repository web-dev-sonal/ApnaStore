package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class customerRepositoryJdbc implements customerRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<customer> findAll() {
        String sql = "SELECT * from customer";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(customer.class));
    }

    @Override
    public int add(customer customer) {
        String sql = "INSERT INTO customer (firstName, lastName, phone, total) VALUES(?,?,?,?)";
         return jdbcTemplate.update(sql,
                new Object[] { customer.getFirstName(), customer.getLastName(), customer.getPhone(),customer.getTotal() });
    }

    @Override
    public customer findById(long phone) {
        try {
            String sql = "SELECT * FROM customer WHERE phone=?";
            customer c = jdbcTemplate.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(customer.class), phone);

            return c;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int update(customer customer) {
        try{
            return jdbcTemplate.update("UPDATE customer SET firstName=?, lastName=?, total=? WHERE phone=?",
                    new Object[] { customer.getFirstName(), customer.getLastName(), customer.getTotal(), customer.getPhone() });
        }
        catch (Exception e){
            return -1;
        }
    }

    @Override
    public int deleteById(long phone) {
        return jdbcTemplate.update("DELETE FROM customer WHERE phone=?", phone);
    }


}
