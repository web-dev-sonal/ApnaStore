package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.order;
import com.subhash.ApnaStore.model.transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class transactionRepositoryJdbc implements transactionRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<transaction> findAll(long phone) {
        String sql = "SELECT * " +
                "FROM transactions " +
                "WHERE " +
                "customerId = ?";
        try{
            return jdbcTemplate.query(sql, new RowMapper<transaction>() {
                @Override
                public transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                    transaction trs = (new BeanPropertyRowMapper<>(transaction.class)).mapRow(rs, rowNum);
                    return trs;
                }
            },new Object[] {phone});
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public int addOrder(transaction transaction) {
        try{
            String sql = "INSERT INTO transactions (amount, customerId) VALUES(?,?)";
            System.out.println(transaction.toString());
            return jdbcTemplate.update(sql, transaction.getAmount(), transaction.getCustomerId());
        }
        catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }

    @Override
    public int deleteById(long phone) {
        try{
            return jdbcTemplate.update("DELETE FROM transactions WHERE customerId=?", phone);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }
}
