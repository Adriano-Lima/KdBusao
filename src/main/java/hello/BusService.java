package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class BusService {

	private static final AtomicLong counter = new AtomicLong();
	
	private static final List<Bus> data = new ArrayList<Bus>(Arrays.asList(new Bus(counter.incrementAndGet(), "T102", 120.0, 122.0),
				new Bus(counter.incrementAndGet(), "T103", 180.0, 182.0)));
        
		
		
	public List<Bus> getAll(){
		return data;		
	}


	public boolean persist(Bus bus) {
		data.add(bus);
		return true;
	}
}
