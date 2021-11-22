package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.beans.PropertyEditorSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class orderRepositoryJdbc implements orderRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<order> findAll(long phone) {

        String sql = "SELECT * " +
                "FROM orders " +
                "WHERE " +
                "customerId = ?";
        try{
            return jdbcTemplate.query(sql, new RowMapper<order>() {
                @Override
                public order mapRow(ResultSet rs, int rowNum) throws SQLException {
                    order Order = (new BeanPropertyRowMapper<>(order.class)).mapRow(rs, rowNum);
                    return Order;
                }
            },new Object[] {phone});
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public int addOrder(order odr) {
        try{
            String sql = "INSERT INTO orders (qty, productId, customerId) VALUES(?,?,?)";
            System.out.println(odr.toString());
            return jdbcTemplate.update(sql, odr.getQty(), odr.getProductId(), odr.getCustomerId());
        }
        catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }

    @Override
    public int deleteById(long customerId) {
        try{
            return jdbcTemplate.update("DELETE FROM orders WHERE customerId=?", customerId);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return -1;
        }
    }
}
