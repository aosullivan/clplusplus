package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import entities.Service;
import entities.StatusCodes;

public class StatusHistoryDao {
	
	private DataSource datasource;
	
	public StatusHistoryDao() {
		
		SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("clplus");
        ds.setPassword("CL++Team123");
        ds.setServerName("clplusplus2.cdqjwu6tacyy.us-east-1.rds.amazonaws.com");
        ds.setPortNumber(1433); 
        ds.setDatabaseName("clplusplus");
        this.datasource = ds;
        
	}

	public void addStatusHistory(String serviceName, String statusName, String result) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("AddStatusHistory ?, ?, ?", new Object[]{serviceName, statusName, result});
	}


}
