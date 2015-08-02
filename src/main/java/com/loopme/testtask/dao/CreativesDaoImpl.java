package com.loopme.testtask.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.loopme.testtask.pojo.AdRequest;
import com.loopme.testtask.pojo.Creative;

@Repository
public class CreativesDaoImpl extends JdbcDaoSupport implements CreativesDao {

    @Autowired
    public CreativesDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Creative> getCreatives(AdRequest adRequest) {
        String sql  = "select id, description, url from Creatives where os like ? and excluded_os not like ? and countries like ? and excluded_countries not like ? order by rand() limit ?";
        Object[] params = new Object[5];
        params[0] = "%" + adRequest.getOs() + "%";
        params[1] = "%" + adRequest.getOs() + "%";
        params[2] = "%" + adRequest.getCountry() + "%";
        params[3] = "%" + adRequest.getCountry() + "%";
        params[4] = adRequest.getLimit();
        
        List<Creative> creatives = this.getJdbcTemplate().query(sql, params, new CreativesMapper());
        
        return creatives;
    }
    
    private static class CreativesMapper implements RowMapper<Creative>{

        @Override
        public Creative mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Creative creative = new Creative();
            creative.setId(resultSet.getInt("id"));
            creative.setDescription(resultSet.getString("description"));
            creative.setUrl(resultSet.getString("url"));
            return creative;
        }
        
    }
}
