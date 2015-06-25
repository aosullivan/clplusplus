package hello;

import dao.ServicesDao;
import entities.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class GreetingController {


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="servicelist", required=false) String name, Model model) {

        ServicesDao servicesDao = new ServicesDao();
        Date date = new Date();
        
        	
        List<Service> services = servicesDao.getServicesByEnvironment("prod");

        model.addAttribute("servicelist", services);
        model.addAttribute("currentdate",date);
        return "greeting";
    }



}