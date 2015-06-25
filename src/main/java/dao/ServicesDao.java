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

public class ServicesDao {
	
	private DataSource datasource;
	
	public ServicesDao() {
		
		SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("clplus");
        ds.setPassword("CL++Team123");
        ds.setServerName("clplusplus2.cdqjwu6tacyy.us-east-1.rds.amazonaws.com");
        ds.setPortNumber(1433); 
        ds.setDatabaseName("clplusplus");
        this.datasource = ds;
        
	}
	
	public List<Service> getServicesByEnvironment(String environment) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query("GetServices_ByEnvironment ?", new Object[]{environment},new DataMapper());
	}
	
	public int updateServiceStatus(String serviceName, StatusCodes status) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.update("updateServiceStatus ?, ?", new Object[]{serviceName, status.toString()});
	}
	
	protected static final class DataMapper implements RowMapper<Service> {
	    @Override
	    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Service service = new Service();
	        service.setName(rs.getString("ServiceName"));
	        service.setDescription(rs.getString("ServiceDescription"));
	        service.setStatus(rs.getString("Status"));
	        return service;
	    }

	}


}
