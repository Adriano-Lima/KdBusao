
package hello;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerPontosdeParada {
    
    	@Autowired
	private BusServicePontosdeParada busService;
        
        	private List<Bus> data = new ArrayList<Bus>();
              

    @RequestMapping(path = "/bus2", method = RequestMethod.POST)
    public List<Bus> T2(@RequestParam(value = "name", defaultValue = "World") String name) {
        data.add(new Bus(1, "T103", 180.0, 182.0));
        data.add(new Bus(2, "T104", 180.0, 182.0));
        System.out.println("Recebi em T2:>>>"+name);
        return data;
    }
}