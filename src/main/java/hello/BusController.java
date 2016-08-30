package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {

	@Autowired
	private BusService busService;

	@RequestMapping(path="/bus", method=RequestMethod.GET)
	public List<Bus> getAll(@RequestParam(value = "name", defaultValue = "World") String name) {
            System.out.println("Recebi:>>>"+name);
		return busService.getAll(); 
	}
	
//	@RequestMapping(name="/bus", method=RequestMethod.POST)
//	public String save(Bus bus) {
//		return Boolean.toString(busService.persist(bus)); 
//	}
        
        @RequestMapping(path="/buspost", method=RequestMethod.POST)
	public void T(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Recebi em T:>>>"+name);
	}
        
}
