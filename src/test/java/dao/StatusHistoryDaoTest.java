package dao;

import org.junit.Test;

public class StatusHistoryDaoTest {

	@Test
	public void test() {
		StatusHistoryDao dao = new StatusHistoryDao();
		dao.addStatusHistory("CL PROD", "OK", "This is Unit Test..");
	}

}
