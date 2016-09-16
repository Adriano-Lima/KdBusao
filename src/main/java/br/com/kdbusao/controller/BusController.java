package br.com.kdbusao.controller;

import br.com.kdbusao.model.Bus;
import br.com.kdbusao.service.BusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @RequestMapping(path = "/{linha}", method = RequestMethod.GET)
    public List<Bus> getAll(@PathVariable("linha") String linha) {
       return busService.getLoc(linha);
        
    }
//        @RequestMapping(path="/buspost", method=RequestMethod.POST)
//	public void T(@RequestParam(value = "name", defaultValue = "World") String name) {
//		System.out.println("Recebi em T:>>>"+name);
//	}
    
            @RequestMapping(path = "/posicao", method = RequestMethod.POST)
    public String setPosicao(@RequestParam("idOnibus") String idOnibus, @RequestParam("longitude") String longitude,
            @RequestParam("latitude") String latitude, @RequestParam("data") String data ) {
       return busService.setLocalizacao(idOnibus, longitude, latitude, data);
        
    }

}
