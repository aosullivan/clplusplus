package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class HelloControllerTest {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}

	@Test
	public void addDbConnection() {
		
		SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("clplus");
        ds.setPassword("CL++Team123");
        ds.setServerName("clplusplus2.cdqjwu6tacyy.us-east-1.rds.amazonaws.com");
        ds.setPortNumber(1433); 
        ds.setDatabaseName("clplusplus");
        
        JdbcTemplate jdbc = new JdbcTemplate(ds);
        
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc)
        	.withProcedureName("addEnvironment");
       
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("EnvironmentName", "prod");
        inParamMap.put("Description", "production");
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        
        Map<String, Object> result = jdbcCall.execute(in);
        System.out.println(result);
	}
	
	@Test
	public void pingCouncelLink() throws UnknownHostException, IOException {
	
		String url = "https://www.counsellink.net/login/login.jsp?force";
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		
		connection.setRequestMethod("HEAD");
		int responseCode = connection.getResponseCode();
		
		assertEquals(200, responseCode);
		
	}
	
	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings CL++ from Spring Boot!")));
	}
}
