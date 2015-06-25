package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import entities.Service;

public class ServicesDaoTest {

	@Test
	public void test() {
		ServicesDao dao = new ServicesDao();
		List<Service> services = dao.getServicesByEnvironment("prod");
		assertNotNull(services);
	}

}
