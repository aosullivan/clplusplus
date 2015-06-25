package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entities.Service;
import entities.StatusCodes;

public class ServicesDaoTest {
	
	@Test
	public void testGet() {
		ServicesDao dao = new ServicesDao();
		List<Service> services = dao.getServicesByEnvironment("prod");
		assertNotNull(services);
	}
	
	@Test
	public void testUpdate() {
		ServicesDao dao = new ServicesDao();
		int updated = dao.updateServiceStatus("CL PROD", StatusCodes.UNPLANNED_OUTAGE);
		assertEquals(1, updated);
	}

}
