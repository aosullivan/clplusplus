package hello;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dao.ServicesDao;
import entities.Service;

@Component
public class Poller {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    ServicesDao dao = new ServicesDao();

    @Scheduled(fixedRate = 10000)
    public void poll() throws MalformedURLException, IOException {
    	List<Service> services = dao.getServicesByEnvironment("prod");
        for (Service service : services) {
    		HttpURLConnection connection = (HttpURLConnection) new URL(service.getUrl()).openConnection();
    		
    		connection.setRequestMethod("HEAD");
    		int responseCode = connection.getResponseCode();
    		System.out.println(service + " : " + responseCode);
        }
    }
}