package com.subhash.ApnaStore.repository;

import com.subhash.ApnaStore.model.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class productRepositoryJdbc implements productRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int add(product product) {
        try{
            String sql = "INSERT INTO product (id, price) VALUES(?,?)";
            return jdbcTemplate.update(sql,
                    new Object[] { product.getId(), product.getPrice() });
        }
        catch(Exception e){
            return -1;
        }
    }

    @Override
    public product findById(int id) {
        try{
            product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE id=?",
                    BeanPropertyRowMapper.newInstance(product.class), id);

            return product;
        }
        catch (Exception e){
            return null;
        }
    }
}
