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

    //retornar todos os onibus de uma linha
    @RequestMapping(path = "/onibus/{linha}", method = RequestMethod.GET)
    public List<Bus> getAll(@PathVariable("linha") String linha) {
       return busService.getLoc(linha);        
    }
    
    //atualizar a posicao de um onibus 
    @RequestMapping(path = "/onibus/posicao", method = RequestMethod.POST)
    public String setPosicao(@RequestParam("idOnibus") String idOnibus, @RequestParam("longitude") String longitude,
            @RequestParam("latitude") String latitude, @RequestParam("data") String data ) {
        return busService.setLocalizacao(idOnibus, longitude, latitude, data);   
    }
    
    //consulta de linhas disponiveis por cidade
    @RequestMapping(path = "/linhas/{cidade}", method = RequestMethod.GET)
    public List<String> getLinhas(@PathVariable("cidade") String cidade) {
       return busService.Linhas(cidade);        
    }
    

}
