package hello;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dao.ServicesDao;
import entities.Service;
import entities.StatusCodes;

@Component
public class Poller {
	
	 private Log log = LogFactory.getLog(Poller.class);

    ServicesDao dao = new ServicesDao();

    @Scheduled(fixedRate = 10000)
    public void poll()  {
    	List<Service> services = dao.getServicesByEnvironment("prod");
        for (Service service : services) {
        	
        	int responseCode = 0;
        	
        	try {
	    		HttpURLConnection connection = (HttpURLConnection) new URL(service.getUrl()).openConnection();
	    		connection.setRequestMethod("HEAD");
	    		responseCode = connection.getResponseCode();
	    		log.info(service.getUrl() + " : " + responseCode);
        	} catch (Exception e){
        		responseCode = 0;
        		log.info(service.getUrl() + " : " + responseCode);
        	}
        	
        	StatusCodes code = (responseCode ==0) ? StatusCodes.UNPLANNED_OUTAGE : StatusCodes.OK;
        	dao.updateServiceStatus(service.getName(), code);
        	
        }
    }
}